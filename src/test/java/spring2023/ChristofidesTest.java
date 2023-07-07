package spring2023;

import org.junit.Test;
import org.spring2023.algo.ChristofidesTSP;
import org.spring2023.io.ReadCSV;
import org.spring2023.util.ComputeCycleCost;
import org.spring2023.util.Node;
import org.spring2023.util.NodeUtil;
import spring2023.util.TestUtil;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class ChristofidesTest {

    public List<Node> nodes;

    public List<Node> tourFromC;

    public double costOfC;

    public ChristofidesTest () {
        nodes = (new ReadCSV()).getPointsFromCSV("src/main/resources/info6205.spring2023.teamproject.csv");
        ChristofidesTSP christofidesTSP = new ChristofidesTSP();
        tourFromC = christofidesTSP.run(nodes);
        costOfC = ComputeCycleCost.getTotalDistance(tourFromC);
    }

    @Test
    public void ChristofidesTestTotalDistance () {
        System.out.println("Total cost: " + costOfC);
        System.out.println("Tour generated by Christofides is");
        NodeUtil.displayTour(tourFromC);
    }

    @Test
    public void ChristofidesTestCorrectness () {
        System.out.println("Check if the two lists contain the same set of Node objects, regardless of their order");
        int unmatch = 0;
        for (int i = 0; i < tourFromC.size(); i++) {
            if (!nodes.contains(tourFromC.get(i))) {
                unmatch++;
            }
        }
        assertTrue(unmatch == 0);
        assertTrue(tourFromC.size() == nodes.size());
        assertTrue(TestUtil.isSameNodes(nodes, tourFromC));
    }

}