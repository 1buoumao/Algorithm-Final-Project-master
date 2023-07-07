package org.spring2023.algo.optimization.tactical;

import org.spring2023.util.Node;
import org.spring2023.util.NodeUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TwoOpt {
    public static List<Node> generate2OptNeighbour(List<Node> tour) {
        List<Node> newTour = new ArrayList<>(tour);
        Random random = new Random();
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


    public static double towOptOptimization (List<Node> nodes, int max) {
        List<Node> bestTour = new ArrayList<>(nodes);
        double bestCost = getTotalDistance(bestTour);
        while (max-- > 0) {
            List<Node> newTour = generate2OptNeighbour(bestTour);
            double newCost = getTotalDistance(newTour);
            //System.out.println(newCost);
            if (newCost < bestCost) {
                bestCost = newCost;
                bestTour = newTour;
            }
        }
        return bestCost;
    }


    public static List<Node> towOptOptimizationReturnTore (List<Node> nodes, int max) {
        List<Node> bestTour = new ArrayList<>(nodes);
        double bestCost = getTotalDistance(bestTour);
        while (max-- > 0) {
            List<Node> newTour = generate2OptNeighbour(bestTour);
            double newCost = getTotalDistance(newTour);
            //System.out.println(newCost);
            if (newCost < bestCost) {
                bestCost = newCost;
                bestTour = newTour;
            }
        }
        return bestTour;
    }



    public static double getTotalDistance(List<Node> tour) {
        double distance = 0;
        for (int i = 0; i < tour.size() - 1; i++) {
            distance += NodeUtil.distance(tour.get(i), tour.get(i + 1));
        }
        distance += NodeUtil.distance(tour.get(tour.size() - 1), tour.get(0));
        return distance;
    }
}
