package org.spring2023.algo.optimization.strategic;

import org.spring2023.util.Node;
import org.spring2023.util.NodeUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TSPSimulatedAnnealing {
    private List<Node> nodes;
    private double initialTemperature;
    private double coolingRate;
    private Random random = new Random();

    public TSPSimulatedAnnealing(List<Node> nodes, double initialTemperature, double coolingRate) {
        this.nodes = nodes;
        this.initialTemperature = initialTemperature;
        this.coolingRate = coolingRate;
    }

    public List<Node> run() {
        double temperature = initialTemperature;
        List<Node> currentTour = new ArrayList<>(nodes);
        List<Node> bestTour = new ArrayList<>(nodes);
        System.out.println("Baseline: " + getTotalDistance(currentTour));
        int times = 0;

        while (temperature > 1) {
            times++;
            List<Node> newTour = generate2OptNeighbour(currentTour);

            double currentTourDistance = getTotalDistance(currentTour);
            double newTourDistance = getTotalDistance(newTour);

            if (acceptanceProbability(currentTourDistance, newTourDistance, temperature) > random.nextDouble()) {
                currentTour = newTour;
            }

            if (getTotalDistance(currentTour) < getTotalDistance(bestTour)) {
                bestTour = currentTour;
            }

            temperature *= coolingRate;
        }
        System.out.println("iterate times: " + times);

        return bestTour;
    }

    public double getTotalDistance(List<Node> tour) {
        double distance = 0;
        for (int i = 0; i < tour.size() - 1; i++) {
            distance += NodeUtil.distance(tour.get(i), tour.get(i + 1));
        }
        distance += NodeUtil.distance(tour.get(tour.size() - 1), tour.get(0));
        return distance;
    }

    private List<Node> generate2OptNeighbour(List<Node> tour) {
        List<Node> newTour = new ArrayList<>(tour);
        int size = tour.size();
        int i = random.nextInt(size);
        int j = random.nextInt(size);
        int min = Math.min(i, j);
        int max = Math.max(i, j);
        // Reverse the nodes between min and max index
        for (int k = 0; k < (max - min + 1) / 2; k++) {
            Collections.swap(newTour, min + k, max - k);
        }
        return newTour;
    }


    private double acceptanceProbability(double currentDistance, double newDistance, double temperature) {
        if (newDistance < currentDistance) {
            return 1.0;
        }
        return Math.exp((currentDistance - newDistance) / temperature);
    }
}
