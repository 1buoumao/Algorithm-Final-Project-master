package org.spring2023.util;
public class Node {
    public String name;
    public double x;
    public double y;

    public Node(String name, double x, double y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void copy(Node other){
        this.name = other.name;
        this.x = other.x;
        this.y = other.y;
    }

    public void swap(Node other){
        double doubleX = other.x;
        other.x = this.x;
        this.x = doubleX;
        double doubleY = other.y;
        other.y = this.y;
        this.y= doubleY;
        String name = this.name;
        this.name = other.name;
        other.name = name;
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
