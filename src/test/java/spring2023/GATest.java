package spring2023;

import org.junit.Test;
import org.spring2023.algo.ChristofidesTSP;
import org.spring2023.algo.optimization.strategic.Chromosome;
import org.spring2023.algo.optimization.strategic.TSPGeneticAlgorithm;
import org.spring2023.io.ReadCSV;
import org.spring2023.util.ComputeCycleCost;
import org.spring2023.util.Node;
import org.spring2023.util.NodeUtil;
import spring2023.util.TestUtil;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class GATest {
    public List<Node> tourFromC;

    public double costOfC;

    public GATest () {
        List<Node> nodes = (new ReadCSV()).getPointsFromCSV("src/main/resources/info6205.spring2023.teamproject.csv");
        ChristofidesTSP christofidesTSP = new ChristofidesTSP();
        tourFromC = christofidesTSP.run(nodes);
        costOfC = ComputeCycleCost.getTotalDistance(tourFromC);
    }

    @Test
    public void GATestCorrectness () {
        int populationSize = 30;
        double mutationRate = 0.05;
        int maxGenerations = 5;
        TSPGeneticAlgorithm ga = new TSPGeneticAlgorithm(tourFromC, populationSize, mutationRate);
        Chromosome bestChromosome = ga.run(maxGenerations);
        assertTrue(TestUtil.isSameNodes(tourFromC, bestChromosome.nodes));
    }

    @Test
    public void GATest0() {
        int populationSize = 100;
        double mutationRate = 0.05;
        int maxGenerations = 50;
        TSPGeneticAlgorithm ga = new TSPGeneticAlgorithm(tourFromC, populationSize, mutationRate);
        System.out.println("Baseline: " + costOfC);
        Chromosome bestChromosome = ga.run(maxGenerations);
        System.out.println("Best solution found:");
        System.out.println(bestChromosome.getTotalDistance());
        System.out.println(TestUtil.getOptimizationRate(costOfC, bestChromosome.getTotalDistance()));
        System.out.println("Optimized tour is");
        NodeUtil.displayTour(bestChromosome.nodes);
    }

    @Test
    public void GATest1() {
        int populationSize = 200;
        double mutationRate = 0.05;
        int maxGenerations = 100;
        TSPGeneticAlgorithm ga = new TSPGeneticAlgorithm(tourFromC, populationSize, mutationRate);
        System.out.println("Baseline: " + costOfC);
        Chromosome bestChromosome = ga.run(maxGenerations);
        System.out.println("Best solution found:");
        System.out.println(bestChromosome.getTotalDistance());
        System.out.println(TestUtil.getOptimizationRate(costOfC, bestChromosome.getTotalDistance()));
        System.out.println("Optimized tour is");
        NodeUtil.displayTour(bestChromosome.nodes);
    }

    @Test
    public void GATest2() {
        int populationSize = 200;
        double mutationRate = 0.05;
        int maxGenerations = 500;
        TSPGeneticAlgorithm ga = new TSPGeneticAlgorithm(tourFromC, populationSize, mutationRate);
        System.out.println("Baseline: " + costOfC);
        Chromosome bestChromosome = ga.run(maxGenerations);
        System.out.println("Best solution found:");
        System.out.println(bestChromosome.getTotalDistance());
        System.out.println(TestUtil.getOptimizationRate(costOfC, bestChromosome.getTotalDistance()));
        System.out.println("Optimized tour is");
        NodeUtil.displayTour(bestChromosome.nodes);
    }

    @Test
    public void GATest3() {
        int populationSize = 200;
        double mutationRate = 0.05;
        int maxGenerations = 1000;
        TSPGeneticAlgorithm ga = new TSPGeneticAlgorithm(tourFromC, populationSize, mutationRate);
        System.out.println("Baseline: " + costOfC);
        Chromosome bestChromosome = ga.run(maxGenerations);
        System.out.println("Best solution found:");
        System.out.println(bestChromosome.getTotalDistance());
        System.out.println(TestUtil.getOptimizationRate(costOfC, bestChromosome.getTotalDistance()));
        System.out.println("Optimized tour is");
        NodeUtil.displayTour(bestChromosome.nodes);
    }

    @Test
    public void GATest4() {
        int populationSize = 300;
        double mutationRate = 0.05;
        int maxGenerations = 1000;
        TSPGeneticAlgorithm ga = new TSPGeneticAlgorithm(tourFromC, populationSize, mutationRate);
        System.out.println("Baseline: " + costOfC);
        Chromosome bestChromosome = ga.run(maxGenerations);
        System.out.println("Best solution found:");
        System.out.println(bestChromosome.getTotalDistance());
        System.out.println(TestUtil.getOptimizationRate(costOfC, bestChromosome.getTotalDistance()));
        System.out.println("Optimized tour is");
        NodeUtil.displayTour(bestChromosome.nodes);
    }

    @Test
    public void GATest5() {
        int populationSize = 300;
        double mutationRate = 0.05;
        int maxGenerations = 3000;
        TSPGeneticAlgorithm ga = new TSPGeneticAlgorithm(tourFromC, populationSize, mutationRate);
        System.out.println("Baseline: " + costOfC);
        Chromosome bestChromosome = ga.run(maxGenerations);
        System.out.println("Best solution found:");
        System.out.println(bestChromosome.getTotalDistance());
        System.out.println(TestUtil.getOptimizationRate(costOfC, bestChromosome.getTotalDistance()));
        System.out.println("Optimized tour is");
        NodeUtil.displayTour(bestChromosome.nodes);
    }
}
