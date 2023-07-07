package org.spring2023.algo.optimization.strategic;

import org.spring2023.util.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TSPGeneticAlgorithm {
    private List<Chromosome> population;
    private int populationSize;
    private double mutationRate;
    private Random random = new Random();
    private List<Node> nodes;

    public TSPGeneticAlgorithm(List<Node> nodes, int populationSize, double mutationRate) {
        this.nodes = nodes;
        this.populationSize = populationSize;
        this.mutationRate = mutationRate;
        initializePopulation();
    }

    private void initializePopulation() {
        population = new ArrayList<>();
        population.add(new Chromosome(nodes, false)); // First chromosome with the original order of nodes
        for (int i = 1; i < populationSize; i++) {
            population.add(new Chromosome(nodes)); // Remaining chromosomes with shuffled nodes
        }
    }



    public Chromosome run(int maxGenerations) {
        for (int generation = 0; generation < maxGenerations; generation++) {
            Collections.sort(population);
            List<Chromosome> newPopulation = new ArrayList<>();
            int eliteCount = populationSize / 10;
            for (int i = 0; i < eliteCount; i++) {
                newPopulation.add(population.get(i));
            }

            for (int i = eliteCount; i < populationSize; i++) {
                Chromosome parent1 = population.get((int) (Math.random() * eliteCount));
                Chromosome parent2 = population.get((int) (Math.random() * eliteCount));
                Chromosome offspring = parent1.crossover(parent2);
                if (Math.random() < mutationRate) {
                    offspring.mutate();
                }
                newPopulation.add(offspring);
            }
            population = newPopulation;
        }

        Collections.sort(population);
        return population.get(0);
    }
}

