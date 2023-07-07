package spring2023;

import org.junit.Test;
import org.spring2023.algo.ChristofidesTSP;
import org.spring2023.algo.optimization.tactical.RandomSwap;
import org.spring2023.io.ReadCSV;
import org.spring2023.util.ComputeCycleCost;
import org.spring2023.util.Node;
import org.spring2023.util.NodeUtil;
import spring2023.util.TestUtil;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RandomSwapTest {

    public List<Node> tourFromC;

    public double costOfC;

    public RandomSwapTest() {
        List<Node> nodes = (new ReadCSV()).getPointsFromCSV("src/main/resources/info6205.spring2023.teamproject.csv");
        ChristofidesTSP christofidesTSP = new ChristofidesTSP();
        List<Node> cycle = christofidesTSP.run(nodes);
        costOfC = ComputeCycleCost.getTotalDistance(cycle);
        System.out.println(costOfC);
        tourFromC = cycle;
    }
    @Test
    public void randomSwap100 () {
        List<Node> newTour = RandomSwap.randomSwapOptimizationReturnTour(tourFromC, 100);
        System.out.println("Total cost: " + costOfC);
        double newCost = ComputeCycleCost.getTotalDistance(newTour);
        System.out.println("New cost: " + newCost);
        System.out.println((costOfC - newCost) / costOfC);
        System.out.println(TestUtil.getOptimizationRate(costOfC, newCost));
        System.out.println("The optimized tour is");
        NodeUtil.displayTour(newTour);
        assertTrue(newCost <= costOfC);
    }

    @Test
    public void randomSwap500 () {
        List<Node> newTour = RandomSwap.randomSwapOptimizationReturnTour(tourFromC, 500);
        System.out.println("Total cost: " + costOfC);
        double newCost = ComputeCycleCost.getTotalDistance(newTour);
        System.out.println("New cost: " + newCost);
        System.out.println((costOfC - newCost) / costOfC);
        System.out.println(TestUtil.getOptimizationRate(costOfC, newCost));
        System.out.println("The optimized tour is");
        NodeUtil.displayTour(newTour);
        assertTrue(newCost <= costOfC);
    }

    @Test
    public void randomSwap10000 () {
        List<Node> newTour = RandomSwap.randomSwapOptimizationReturnTour(tourFromC, 10000);
        System.out.println("Total cost: " + costOfC);
        double newCost = ComputeCycleCost.getTotalDistance(newTour);
        System.out.println("New cost: " + newCost);
        System.out.println((costOfC - newCost) / costOfC);
        System.out.println(TestUtil.getOptimizationRate(costOfC, newCost));
        System.out.println("The optimized tour is");
        NodeUtil.displayTour(newTour);
        assertTrue(newCost <= costOfC);
    }

    @Test
    public void randomSwap1000000 () {
        List<Node> newTour = RandomSwap.randomSwapOptimizationReturnTour(tourFromC, 1000000);
        System.out.println("Total cost: " + costOfC);
        double newCost = ComputeCycleCost.getTotalDistance(newTour);
        System.out.println("New cost: " + newCost);
        System.out.println((costOfC - newCost) / costOfC);
        System.out.println(TestUtil.getOptimizationRate(costOfC, newCost));
        System.out.println("The optimized tour is");
        NodeUtil.displayTour(newTour);
        System.out.println("Total cost: " + costOfC);
        System.out.println("New cost: " + newCost);
        System.out.println(TestUtil.getOptimizationRate(costOfC, newCost));
        assertTrue(newCost <= costOfC);
    }

    @Test
    public void totaldistance10(){
        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node("A", 0, 0));
        nodes.add(new Node("B", 0, 1));
        assertEquals(111000 * 2, RandomSwap.getTotalDistance(nodes), 2000);
    }

    @Test
    public void randomSwap5(){
        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node("A", 0, 0));
        nodes.add(new Node("B", 2, 1));
        nodes.add(new Node("E", 5, 3));
        nodes.add(new Node("D", 4, 4));
        nodes.add(new Node("C", 1, 2));
        List<Node> newNodes = RandomSwap.randomSwap(nodes);
        int unmatch = 0;
        for (int i = 0; i < nodes.size(); i++) {
            if (!nodes.get(i).equals(newNodes.get(i))){
                unmatch ++;
            }
        }
        assertEquals(nodes.size(), newNodes.size());
        assertTrue(unmatch == 0 || unmatch == 2);
        assertTrue(TestUtil.isSameNodes(nodes, newNodes));
    }
    @Test
    public void randomSwapCSV(){
        List<Node> newNodes = RandomSwap.randomSwap(tourFromC);
        int unmatch = 0;
        for (int i = 0; i < tourFromC.size(); i++) {
            if (!tourFromC.get(i).equals(newNodes.get(i))){
                unmatch ++;
            }
        }
        assertEquals(tourFromC.size(), newNodes.size());
        assertTrue(unmatch == 0 || unmatch == 2);
        assertTrue(TestUtil.isSameNodes(tourFromC, newNodes));
    }
}
