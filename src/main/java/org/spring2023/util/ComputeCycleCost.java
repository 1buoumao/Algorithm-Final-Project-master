package org.spring2023.util;

import java.util.List;

public class ComputeCycleCost {
    public static double getTotalDistance(List<Node> tour) {
        double distance = 0;
        for (int i = 0; i < tour.size() - 1; i++) {
            distance += NodeUtil.distance(tour.get(i), tour.get(i + 1));
        }
        distance += NodeUtil.distance(tour.get(tour.size() - 1), tour.get(0));
        return distance;
    }

    public static String getOptimizationRate(double baseline, double optimized) {
        return String.format("Optimization Rate is %.6f%%", ((baseline - optimized) / baseline) * 100);
    }
}
