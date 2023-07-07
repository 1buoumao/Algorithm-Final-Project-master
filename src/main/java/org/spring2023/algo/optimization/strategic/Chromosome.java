package org.spring2023.algo.optimization.strategic;


import org.spring2023.util.Node;
import org.spring2023.util.NodeUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Chromosome implements Comparable<Chromosome> {
    public List<Node> nodes;
    private double totalDistance;
    private static Random random = new Random();

    public Chromosome(List<Node> nodes) {
        this(nodes, true);
    }

    public Chromosome(List<Node> nodes, boolean shuffle) {
        this.nodes = new ArrayList<>(nodes);
        if (shuffle) {
            Collections.shuffle(this.nodes);
        }
        updateTotalDistance();
    }

    public Chromosome(Chromosome other) {
        this.nodes = new ArrayList<>(other.nodes);
        this.totalDistance = other.totalDistance;
    }

    public void mutate() {
        int index1 = random.nextInt(nodes.size());
        int index2 = random.nextInt(nodes.size());
        nodes.get(index1).swap(nodes.get(index2));
        updateTotalDistance();
    }

    // order cross
    public Chromosome crossover(Chromosome other) {
        int index1 = random.nextInt(nodes.size());
        int index2 = random.nextInt(nodes.size());
        int start = Math.min(index1, index2);
        int end = Math.max(index1, index2);
        List<Node> offspringNodes = new ArrayList<>(nodes.subList(start, end));
        for (Node node : other.nodes) {
            if (!offspringNodes.contains(node)) {
                offspringNodes.add(node);
            }
        }
        Chromosome offspring = new Chromosome(offspringNodes, false);
        offspring.updateTotalDistance();
        return offspring;
    }

    private void updateTotalDistance() {
        double distance = 0;
        for (int i = 0; i < nodes.size() - 1; i++) {
            distance += NodeUtil.distance(nodes.get(i), nodes.get(i + 1));
        }
        distance += NodeUtil.distance(nodes.get(nodes.size() - 1), nodes.get(0));
        this.totalDistance = distance;
    }

    @Override
    public int compareTo(Chromosome other) {
        return Double.compare(this.totalDistance, other.totalDistance);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Chromosome{totalDistance=").append(totalDistance).append(", nodes=[");
        for (Node node : nodes) {
            sb.append(node.getName()).append(", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append("]}");
        return sb.toString();
    }

    public double getTotalDistance() {
        return totalDistance;
    }
}
