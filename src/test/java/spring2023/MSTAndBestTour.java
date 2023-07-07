package spring2023;

import io.cucumber.java.mk_latn.No;
import org.junit.Test;
import org.spring2023.algo.ChristofidesTSP;
import org.spring2023.algo.optimization.tactical.TwoOpt;
import org.spring2023.io.ReadCSV;
import org.spring2023.util.ComputeCycleCost;
import org.spring2023.util.Edge;
import org.spring2023.util.Node;
import org.spring2023.util.NodeUtil;

import java.util.List;

public class MSTAndBestTour {
    public List<Node> tourFromC;

    public double costOfC;

    public double costOfTSP;

    public MSTAndBestTour () {

        List<Node> nodes = (new ReadCSV()).getPointsFromCSV("src/main/resources/info6205.spring2023.teamproject.csv");



        ChristofidesTSP christofidesTSP = new ChristofidesTSP();

        List<Edge> edges = ChristofidesTSP.findMST(nodes);

        costOfTSP = 0;
        for (Edge e: edges) costOfTSP += e.weight;

        List<Node> cycle = christofidesTSP.run(nodes);

        costOfC = ComputeCycleCost.getTotalDistance(cycle);

        tourFromC = cycle;

    }

    @Test
    public void compareOurBestTourWithTSP () {
        List<Node> newTour = TwoOpt.towOptOptimizationReturnTore(tourFromC, 3000000);
        double newCost = ComputeCycleCost.getTotalDistance(newTour);
        System.out.println("Cost of MST: " + costOfTSP);
        System.out.println("Cost of our best tour: " + newCost);
        System.out.println(String.format("Our tour is %.2f%% longer than the MST", ((newCost - costOfTSP) / costOfTSP) * 100));
        System.out.println("Our optimal tour is");
        NodeUtil.displayTour(newTour);
    }
}
