package spring2023;

import org.junit.Test;
import org.spring2023.algo.ChristofidesTSP;
import org.spring2023.algo.optimization.tactical.TwoOpt;
import org.spring2023.io.ReadCSV;
import org.spring2023.util.ComputeCycleCost;
import org.spring2023.util.Node;
import org.spring2023.util.NodeUtil;
import spring2023.util.TestUtil;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TwoOptTest {
    public List<Node> tourFromC;

    public double costOfC;

    public TwoOptTest() {
        List<Node> nodes = (new ReadCSV()).getPointsFromCSV("src/main/resources/info6205.spring2023.teamproject.csv");

        ChristofidesTSP christofidesTSP = new ChristofidesTSP();
        List<Node> cycle = christofidesTSP.run(nodes);

        costOfC = ComputeCycleCost.getTotalDistance(cycle);

        tourFromC = cycle;

    }



    @Test
    public void totaldistance10(){
        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node("A", 0, 0));
        nodes.add(new Node("B", 0, 1));
        assertEquals(111000 * 2, TwoOpt.getTotalDistance(nodes), 2000);
    }

    @Test
    public void TwoOpt5(){
        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node("A", 0, 0));
        nodes.add(new Node("B", 2, 1));
        nodes.add(new Node("E", 5, 3));
        nodes.add(new Node("D", 4, 4));
        nodes.add(new Node("C", 1, 2));
        List<Node> newNodes = TwoOpt.generate2OptNeighbour(nodes);
        assertTrue(TestUtil.isSameNodes(nodes, newNodes));
        assertEquals(nodes.size(), newNodes.size());
    }
    @Test
    public void TwoOptCSV(){
        List<Node> newNodes = TwoOpt.generate2OptNeighbour(tourFromC);
        assertTrue(TestUtil.isSameNodes(tourFromC, newNodes));
        assertEquals(tourFromC.size(), newNodes.size());
    }
    @Test
    public void TwoOpt10 () {
        System.out.println("Total cost: " + costOfC);
        List<Node> newTour = TwoOpt.towOptOptimizationReturnTore(tourFromC, 10);
        double newCost = ComputeCycleCost.getTotalDistance(newTour);
        System.out.println("New cost: " + newCost);
        System.out.println((costOfC - newCost) / costOfC);
        System.out.println(TestUtil.getOptimizationRate(costOfC, newCost));
        System.out.println("Optimized Tour is");
        NodeUtil.displayTour(newTour);
        assertTrue(newCost <= costOfC);
    }

    @Test
    public void TwoOpt100 () {
        System.out.println("Total cost: " + costOfC);
        List<Node> newTour = TwoOpt.towOptOptimizationReturnTore(tourFromC, 100);
        double newCost = ComputeCycleCost.getTotalDistance(newTour);
        System.out.println("New cost: " + newCost);
        System.out.println((costOfC - newCost) / costOfC);
        System.out.println(TestUtil.getOptimizationRate(costOfC, newCost));
        System.out.println("Optimized Tour is");
        NodeUtil.displayTour(newTour);
        assertTrue(newCost <= costOfC);
    }

    @Test
    public void TwoOpt10000 () {
        System.out.println("Total cost: " + costOfC);
        List<Node> newTour = TwoOpt.towOptOptimizationReturnTore(tourFromC, 10000);
        double newCost = ComputeCycleCost.getTotalDistance(newTour);
        System.out.println("New cost: " + newCost);
        System.out.println((costOfC - newCost) / costOfC);
        System.out.println(TestUtil.getOptimizationRate(costOfC, newCost));
        System.out.println("Optimized Tour is");
        NodeUtil.displayTour(newTour);
        assertTrue(newCost <= costOfC);
    }

    @Test
    public void TwoOpt1000000 () {
        System.out.println("Total cost: " + costOfC);
        List<Node> newTour = TwoOpt.towOptOptimizationReturnTore(tourFromC, 1000000);
        double newCost = ComputeCycleCost.getTotalDistance(newTour);
        System.out.println("New cost: " + newCost);
        System.out.println((costOfC - newCost) / costOfC);
        System.out.println(TestUtil.getOptimizationRate(costOfC, newCost));
        System.out.println("Optimized Tour is");
        NodeUtil.displayTour(newTour);
        assertTrue(newCost <= costOfC);
    }

}
