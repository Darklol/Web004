package ru.itmo.lab4.model;

public class Graph {
    private static boolean isInArea(double x, double y, double r) {
        boolean inTriangle = x <= 0 && y >= 0 && y <= r + 2*x;
        boolean inRectangle = x <= 0 && y <= 0 && x >= -r  && y >= -r;
        boolean inCircle = x >= 0 && y >= 0 && x*x + y*y <= r * r;
        return inTriangle || inRectangle || inCircle;
    }

    public static boolean isInArea(Point point) {
        return isInArea(point.getX(), point.getY(), point.getR());
    }
}
