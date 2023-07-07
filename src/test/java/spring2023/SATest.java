package spring2023;

import org.junit.Test;
import org.spring2023.algo.ChristofidesTSP;
import org.spring2023.algo.optimization.strategic.TSPSimulatedAnnealing;
import org.spring2023.io.ReadCSV;
import org.spring2023.util.ComputeCycleCost;
import org.spring2023.util.Node;
import org.spring2023.util.NodeUtil;
import spring2023.util.TestUtil;

import java.util.List;


import static org.junit.Assert.assertTrue;

public class SATest {
    public List<Node> tourFromC;

    public double costOfC;
    public SATest () {
        List<Node> nodes = (new ReadCSV()).getPointsFromCSV("src/main/resources/info6205.spring2023.teamproject.csv");
        ChristofidesTSP christofidesTSP = new ChristofidesTSP();
        tourFromC = christofidesTSP.run(nodes);
        costOfC = ComputeCycleCost.getTotalDistance(tourFromC);
    }

    @Test
    public void SATestCorrectness() {
        double initialTemperature = 1000000;
        double coolingRate = 0.99;


        TSPSimulatedAnnealing sa = new TSPSimulatedAnnealing(tourFromC, initialTemperature, coolingRate);
        List<Node> optimizedTour = sa.run();
        assertTrue(TestUtil.isSameNodes(tourFromC, optimizedTour));
    }

    @Test
    public void SATest0995 () {
        double initialTemperature = 1000000;
        double coolingRate = 0.995;

        TSPSimulatedAnnealing sa = new TSPSimulatedAnnealing(tourFromC, initialTemperature, coolingRate);
        List<Node> optimizedTour = sa.run();
        System.out.println("After Optimization: " + sa.getTotalDistance(optimizedTour));
        System.out.println(TestUtil.getOptimizationRate(sa.getTotalDistance(tourFromC), sa.getTotalDistance(optimizedTour)));
        System.out.println("Optimized tour is");
        NodeUtil.displayTour(optimizedTour);
    }

    @Test
    public void SATest0998 () {
        double initialTemperature = 1000000;
        double coolingRate = 0.998;

        TSPSimulatedAnnealing sa = new TSPSimulatedAnnealing(tourFromC, initialTemperature, coolingRate);
        List<Node> optimizedTour = sa.run();
        System.out.println("After Optimization: " + sa.getTotalDistance(optimizedTour));
        System.out.println(TestUtil.getOptimizationRate(sa.getTotalDistance(tourFromC), sa.getTotalDistance(optimizedTour)));
        System.out.println("Optimized tour is");
        NodeUtil.displayTour(optimizedTour);
    }

    @Test
    public void SATest0999 () {
        double initialTemperature = 1000000;
        double coolingRate = 0.999;

        TSPSimulatedAnnealing sa = new TSPSimulatedAnnealing(tourFromC, initialTemperature, coolingRate);
        List<Node> optimizedTour = sa.run();
        System.out.println("After Optimization: " + sa.getTotalDistance(optimizedTour));
        System.out.println(TestUtil.getOptimizationRate(sa.getTotalDistance(tourFromC), sa.getTotalDistance(optimizedTour)));
        System.out.println("Optimized tour is");
        NodeUtil.displayTour(optimizedTour);
    }

    @Test
    public void SATest09995 () {
        double initialTemperature = 1000000;
        double coolingRate = 0.9995;

        TSPSimulatedAnnealing sa = new TSPSimulatedAnnealing(tourFromC, initialTemperature, coolingRate);
        List<Node> optimizedTour = sa.run();
        System.out.println("After Optimization: " + sa.getTotalDistance(optimizedTour));
        System.out.println(TestUtil.getOptimizationRate(sa.getTotalDistance(tourFromC), sa.getTotalDistance(optimizedTour)));
        System.out.println("Optimized tour is");
        NodeUtil.displayTour(optimizedTour);
    }

    @Test
    public void SATest09999 () {
        double initialTemperature = 1000000;
        double coolingRate = 0.9999;

        TSPSimulatedAnnealing sa = new TSPSimulatedAnnealing(tourFromC, initialTemperature, coolingRate);
        List<Node> optimizedTour = sa.run();
        System.out.println("After Optimization: " + sa.getTotalDistance(optimizedTour));
        System.out.println(TestUtil.getOptimizationRate(sa.getTotalDistance(tourFromC), sa.getTotalDistance(optimizedTour)));
        System.out.println("Optimized tour is");
        NodeUtil.displayTour(optimizedTour);
    }

    @Test
    public void SATest099999 () {
        double initialTemperature = 1000000;
        double coolingRate = 0.99999;

        TSPSimulatedAnnealing sa = new TSPSimulatedAnnealing(tourFromC, initialTemperature, coolingRate);
        List<Node> optimizedTour = sa.run();
        System.out.println("After Optimization: " + sa.getTotalDistance(optimizedTour));
        System.out.println(TestUtil.getOptimizationRate(sa.getTotalDistance(tourFromC), sa.getTotalDistance(optimizedTour)));
        System.out.println("Optimized tour is");
        NodeUtil.displayTour(optimizedTour);
    }
}
