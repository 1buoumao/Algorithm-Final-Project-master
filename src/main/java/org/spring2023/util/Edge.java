package org.spring2023.util;
public class Edge implements Comparable<Edge> {
    public Node source;
    public Node destination;
    public double weight;

    public Edge(Node source, Node destination) {
        this.source = source;
        this.destination = destination;
        this.weight = NodeUtil.distance(this.source, this.destination);
    }

    public int compareTo(Edge other) {
        return Double.compare(this.weight, other.weight);
    }
}