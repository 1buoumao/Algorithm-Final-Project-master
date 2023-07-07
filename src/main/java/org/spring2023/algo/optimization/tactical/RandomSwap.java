package org.spring2023.algo.optimization.tactical;

import org.spring2023.util.Edge;
import org.spring2023.util.Node;
import org.spring2023.util.NodeUtil;

import java.util.*;

public class RandomSwap {
    /**
     * @param  tour a tour
     * @return a tour
     * */
    public static List<Node> randomSwap (List<Node> tour) {
        List<Node> newTour = new ArrayList<>(tour);
        Random random = new Random();
        int i = random.nextInt(tour.size());
        int j = random.nextInt(tour.size());
        Collections.swap(newTour, i, j);
        return newTour;
    }

    /**/
    public static double randomSwapOptimization (List<Node> nodes, int max) {
        List<Node> bestTour = new ArrayList<>(nodes);
        double bestCost = getTotalDistance(bestTour);
        while (max-- > 0) {
            List<Node> newTour = randomSwap(bestTour);
            double newCost = getTotalDistance(newTour);
            if (newCost < bestCost) {
                bestCost = newCost;
                bestTour = newTour;
            }
        }
        return bestCost;
    }


    public static List<Node> randomSwapOptimizationReturnTour (List<Node> nodes, int max) {
        List<Node> bestTour = new ArrayList<>(nodes);
        double bestCost = getTotalDistance(bestTour);
        while (max-- > 0) {
            List<Node> newTour = randomSwap(bestTour);
            double newCost = getTotalDistance(newTour);
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
