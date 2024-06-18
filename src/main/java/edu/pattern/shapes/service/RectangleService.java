package edu.pattern.shapes.service;

import edu.pattern.shapes.model.Point;
import edu.pattern.shapes.model.Rectangle;
import edu.pattern.shapes.observer.Observable;
import edu.pattern.shapes.observer.Observer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class RectangleService implements Observable {
    private static final Logger logger = LoggerFactory.getLogger(RectangleService.class);
    private static RectangleService instance;
    private final List<Observer> observers;

    private RectangleService() {
        observers = new ArrayList<>();
    }

    public static synchronized RectangleService getInstance() {
        if (instance == null) {
            instance = new RectangleService();
        }
        return instance;
    }

    public double calculatePerimeter(Rectangle rectangle) {
        List<Point> points = rectangle.getPoints();
        double perimeter = 0;
        for (int i = 0; i < points.size(); i++) {
            Point p1 = points.get(i);
            Point p2 = points.get((i + 1) % points.size());
            perimeter += distance(p1, p2);
        }
        logger.info("Calculated perimeter for {}: {}", rectangle.getName(), perimeter);
        notifyObservers(perimeter);
        return perimeter;
    }

    public boolean isConvex(Rectangle rectangle) {
        List<Point> points = rectangle.getPoints();
        if (points.size() != 4) {
            return false;
        }

        int n = points.size();
        boolean hasPositive = false;
        boolean hasNegative = false;

        for (int i = 0; i < n; i++) {
            double dx1 = points.get((i + 1) % n).getX() - points.get(i).getX();
            double dy1 = points.get((i + 1) % n).getY() - points.get(i).getY();
            double dx2 = points.get((i + 2) % n).getX() - points.get((i + 1) % n).getX();
            double dy2 = points.get((i + 2) % n).getY() - points.get((i + 1) % n).getY();
            double zcrossproduct = dx1 * dy2 - dy1 * dx2;

            if (zcrossproduct > 0) {
                hasPositive = true;
            } else if (zcrossproduct < 0) {
                hasNegative = true;
            }

            // Если есть и положительные и отрицательные произведения, то четырехугольник не выпуклый
            if (hasPositive && hasNegative) {
                return false;
            }
        }
        return true;
    }



    private double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p2.getX() - p1.getX(), 2) + Math.pow(p2.getY() - p1.getY(), 2));
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(double perimeter) {
        for (Observer observer : observers) {
            observer.update(perimeter);
        }
    }
}
