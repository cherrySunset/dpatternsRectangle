package edu.pattern.shapes.validator;
import edu.pattern.shapes.model.Point;
import edu.pattern.shapes.model.Rectangle;

import java.util.List;

public class RectangleValidator {
    public boolean isValid(Rectangle rectangle) {
        List<Point> points = rectangle.getPoints();

        if (points.size() != 4) {
            return false;
        }

        for (int i = 0; i < points.size(); i++) {
            Point p1 = points.get(i);
            Point p2 = points.get((i + 1) % points.size());
            Point p3 = points.get((i + 2) % points.size());
            if (areCollinear(p1, p2, p3)) {
                return false;
            }
        }

        return isRectangle(points);
    }

    private boolean areCollinear(Point p1, Point p2, Point p3) {
        return (p3.getY() - p1.getY()) * (p2.getX() - p1.getX()) == (p2.getY() - p1.getY()) * (p3.getX() - p1.getX());
    }

    private boolean isRectangle(List<Point> points) {
        Point p1 = points.get(0);
        Point p2 = points.get(1);
        Point p3 = points.get(2);
        Point p4 = points.get(3);

        return isPerpendicular(p1, p2, p3) &&
                isPerpendicular(p2, p3, p4) &&
                isPerpendicular(p3, p4, p1);
    }

    private boolean isPerpendicular(Point p1, Point p2, Point p3) {
        double dx1 = p2.getX() - p1.getX();
        double dy1 = p2.getY() - p1.getY();
        double dx2 = p3.getX() - p2.getX();
        double dy2 = p3.getY() - p2.getY();
        return dx1 * dx2 + dy1 * dy2 == 0;
    }

    public boolean isSquare(Rectangle rectangle) {
        List<Point> points = rectangle.getPoints();
        if (!isRectangle(points)) {
            return false;
        }
        double side1 = distance(points.get(0), points.get(1));
        double side2 = distance(points.get(1), points.get(2));
        double side3 = distance(points.get(2), points.get(3));
        double side4 = distance(points.get(3), points.get(0));
        return side1 == side2 && side2 == side3 && side3 == side4;
    }

    public boolean isRhombus(Rectangle rectangle) {
        List<Point> points = rectangle.getPoints();
        double side1 = distance(points.get(0), points.get(1));
        double side2 = distance(points.get(1), points.get(2));
        double side3 = distance(points.get(2), points.get(3));
        double side4 = distance(points.get(3), points.get(0));
        return side1 == side2 && side2 == side3 && side3 == side4;
    }

    public boolean isTrapezoid(Rectangle rectangle) {
        List<Point> points = rectangle.getPoints();
        Point p1 = points.get(0);
        Point p2 = points.get(1);
        Point p3 = points.get(2);
        Point p4 = points.get(3);
        return areParallel(p1, p2, p3, p4) || areParallel(p2, p3, p4, p1);
    }

    private boolean areParallel(Point p1, Point p2, Point p3, Point p4) {
        double dx1 = p2.getX() - p1.getX();
        double dy1 = p2.getY() - p1.getY();
        double dx2 = p4.getX() - p3.getX();
        double dy2 = p4.getY() - p3.getY();
        return dx1 * dy2 == dx2 * dy1;
    }

    private double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p2.getX() - p1.getX(), 2) + Math.pow(p2.getY() - p1.getY(), 2));
    }
}