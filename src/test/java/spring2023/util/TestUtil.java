package spring2023.util;

import org.spring2023.util.Node;

import java.util.*;

public class TestUtil {
    public static boolean isSameNodes(List<Node> A, List<Node> B) {
        if (A.size() != B.size()) {
            return false;
        }
        Set<Node> setA = new HashSet<>(A);
        Set<Node> setB = new HashSet<>(B);
        return setA.equals(setB);
    }

    public static String getOptimizationRate(double baseline, double optimized) {
        return String.format("Optimization Rate is %.6f%%", ((baseline - optimized) / baseline) * 100);
    }
}
