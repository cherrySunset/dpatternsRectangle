package edu.pattern.shapes.pool;

import edu.pattern.shapes.model.Point;
import edu.pattern.shapes.model.Rectangle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PoolObject {
    private static PoolObject instance;
    private List<Rectangle> rectangles;
    private Map<String, Double> perimeters;
    private Map<String, Double> areas;

    private PoolObject() {
        rectangles = new ArrayList<>();
        perimeters = new HashMap<>();
        areas = new HashMap<>();
    }

    public static synchronized PoolObject getInstance() {
        if (instance == null) {
            instance = new PoolObject();
        }
        return instance;
    }

    public void addRectangle(Rectangle rectangle) {
        rectangles.add(rectangle);
        updateRectangleData(rectangle);
    }

    public void updateRectangleData(Rectangle rectangle) {
        double perimeter = calculatePerimeter(rectangle);
        double area = calculateArea(rectangle);
        perimeters.put(rectangle.getName(), perimeter);
        areas.put(rectangle.getName(), area);
    }

    public double getPerimeter(String name) {
        return perimeters.get(name);
    }

    public double getArea(String name) {
        return areas.get(name);
    }

    private double calculatePerimeter(Rectangle rectangle) {
        List<Point> points = rectangle.getPoints();
        double perimeter = 0;
        for (int i = 0; i < points.size(); i++) {
            Point p1 = points.get(i);
            Point p2 = points.get((i + 1) % points.size());
            perimeter += distance(p1, p2);
        }
        return perimeter;
    }

    private double calculateArea(Rectangle rectangle) {
        List<Point> points = rectangle.getPoints();
        double width = distance(points.get(0), points.get(1));
        double height = distance(points.get(1), points.get(2));
        return width * height;
    }

    private double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p2.getX() - p1.getX(), 2) + Math.pow(p2.getY() - p1.getY(), 2));
    }
}
