package org.spring2023.algo;

import org.spring2023.util.Edge;
import org.spring2023.util.Node;
import org.spring2023.util.NodeUtil;

import java.util.*;

public class ChristofidesTSP {

    // Find the minimum spanning tree of a graph using Prim's algorithm
    public static List<Edge> findMST(List<Node> nodes) {
        List<Edge> mst = new ArrayList<>();
        Set<Node> visited = new HashSet<>();
        visited.add(nodes.get(0));

        while (visited.size() < nodes.size()) {
            Edge minEdge = null;
            for (Node node : visited) {
                for (Node neighbor : nodes) {
                    if (!visited.contains(neighbor)) {
                        Edge edge = new Edge(node, neighbor);
                        if (minEdge == null || edge.weight < minEdge.weight) {
                            minEdge = edge;
                        }
                    }
                }
            }
            mst.add(minEdge);
            visited.add(minEdge.destination);
        }
        return mst;
    }

    // Find the odd-degree nodes in a graph (i.e. nodes with an odd number of edges)
    private static Set<Node> findOddNodes(List<Node> nodes, List<Edge> edges) {
        Map<Node, Integer> degree = new HashMap<>();
        for (Node node : nodes) {
            degree.put(node, 0);
        }
        for (Edge edge : edges) {
            degree.put(edge.source, degree.get(edge.source) + 1);
            degree.put(edge.destination, degree.get(edge.destination) + 1);
        }
        Set<Node> oddNodes = new HashSet<>();
        for (Node node : degree.keySet()) {
            if (degree.get(node) % 2 == 1) {
                oddNodes.add(node);
            }
        }
        return oddNodes;
    }


    // Find the minimum-weight perfect matching between the odd-degree nodes in a graph using the Blossom algorithm
    private static List<Edge> findPerfectMatching(Set<Node> oddNodes) {
        List<Node> nodeList = new ArrayList<>(oddNodes);
        int n = nodeList.size();
        double[][] cost = new double[n][n];
        Map<Node, Integer> nodeIndex = new HashMap<>();
        for (int i = 0; i < n; i++) {
            nodeIndex.put(nodeList.get(i), i);
        }
        for (int i = 0; i < n; i++) {
            Node node1 = nodeList.get(i);
            for (int j = 0; j < n; j++) {
                Node node2 = nodeList.get(j);
                cost[i][j] = NodeUtil.distance(node1, node2);
            }
        }
        int[] match = new int[n];
        Arrays.fill(match, -1);
        for (int i = 0; i < n; i++) {
            augment(i, nodeList, cost, match, nodeIndex);
        }
        List<Edge> matching = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (match[i] != -1) {
                matching.add(new Edge(nodeList.get(i), nodeList.get(match[i])));
            }
        }
        return matching;
    }

    private static boolean augment(int u, List<Node> nodeList, double[][] cost, int[] match, Map<Node, Integer> nodeIndex) {
        int n = nodeList.size();
        boolean[] visited = new boolean[n];
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(u);
        visited[u] = true;
        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int w = 0; w < n; w++) {
                if (!visited[w]) {
                    visited[w] = true;
                    parent[w] = v;
                    if (match[w] == -1) {
                        // Found an augmenting path
                        while (w != -1) {
                            int p = parent[w];
                            int i = nodeIndex.get(nodeList.get(w));
                            match[w] = i;
                            match[i] = w;
                            w = p;
                        }
                        return true;
                    } else {
                        int x = match[w];
                        visited[x] = true;
                        parent[x] = w;
                        queue.offer(x);
                    }
                }
            }
        }
        return false;
    }


    // Connect the minimum-weight perfect matching to the minimum spanning tree to form a eulerian graph
    // Combine
    private static List<Edge> connectMatchingToMST(List<Edge> mst, List<Edge> matching) {
        List<Edge> combined = new ArrayList<>(mst);
        combined.addAll(matching);
        return combined;
    }

    // Find an eulerian tour of a graph (i.e. a tour that visits every edge exactly once)
    // Hierholzer's algorithm
    private static List<Node> findEulerianTour(List<Edge> edges) {
        Map<Node, List<Node>> graph = new HashMap<>();
        for (Edge edge : edges) {
            if (!graph.containsKey(edge.source)) {
                graph.put(edge.source, new ArrayList<>());
            }
            graph.get(edge.source).add(edge.destination);
            if (!graph.containsKey(edge.destination)) {
                graph.put(edge.destination, new ArrayList<>());
            }
            graph.get(edge.destination).add(edge.source);
        }

        Node start = edges.get(0).source;
        List<Node> tour = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            Node current = stack.peek();
            List<Node> neighbors = new ArrayList<>(graph.get(current));
            if (neighbors != null && !neighbors.isEmpty()) {
                Node neighbor = neighbors.get(0);
                stack.push(neighbor);
                graph.get(current).remove(neighbor);
                graph.get(neighbor).remove(current);
            } else {
                stack.pop();
                tour.add(current);
            }
        }

        Collections.reverse(tour);
        return tour;
    }

    // Run the ChristofidesTSP algorithm on a list of nodes
    public static List<Node> run(List<Node> nodes) {
        // Step 1: Find the minimum spanning tree
        List<Edge> mst = findMST(nodes);

        // Step 2: Find the set of odd-degree nodes
        Set<Node> oddNodes = findOddNodes(nodes, mst);

        // Step 3: Find the minimum-weight perfect matching between the odd-degree nodes
        List<Edge> matching = findPerfectMatching(oddNodes);

        // Step 4: Connect the minimum-weight perfect matching to the minimum spanning tree
        List<Edge> combined = connectMatchingToMST(mst, matching);

        // Step 5: Find an eulerian tour of the combined graph
        List<Node> tour = findEulerianTour(combined);

        // Step 6: Remove duplicate nodes from the tour to get a Hamiltonian cycle
        Set<Node> visited = new HashSet<>();
        List<Node> hamiltonianCycle = new ArrayList<>();
        for (Node node : tour) {
            if (!visited.contains(node)) { // skip repeated nodes
                hamiltonianCycle.add(node);
                visited.add(node);
            }
        }
        
        //hamiltonianCycle.add(hamiltonianCycle.get(0)); // Add the starting node to the end to complete the cycle

        return hamiltonianCycle;
    }
}

