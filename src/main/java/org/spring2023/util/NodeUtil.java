package org.spring2023.util;

import java.util.List;

public class NodeUtil {
    private static final double EARTH_RADIUS = 6371000; // Earth radius in meters

    public static double distance(Node node1, Node node2) {
        double lat1 = Math.toRadians(node1.getY());
        double lat2 = Math.toRadians(node2.getY());
        double deltaLat = Math.toRadians(node2.getY() - node1.getY());
        double deltaLon = Math.toRadians(node2.getX() - node1.getX());

        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }

    public static void displayTour (List<Node> tour) {
        for (Node n: tour) {
            System.out.println(n.getName().substring(n.getName().length() - 5));
        }
    }
}