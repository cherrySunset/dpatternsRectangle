package edu.pattern.shapes;

import edu.pattern.shapes.model.Point;
import edu.pattern.shapes.model.Rectangle;
import edu.pattern.shapes.observer.RectangleObserver;
import edu.pattern.shapes.pool.PoolObject;
import edu.pattern.shapes.service.RectangleService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;

public class RectangleServiceTest {

    private RectangleService service;
    private RectangleObserver observer;

    @BeforeClass
    public void setUp() {
        service = RectangleService.getInstance();
        observer = new RectangleObserver();
        service.addObserver(observer);
    }

    @Test
    public void testCalculatePerimeter_ValidRectangle() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 2);
        Point p3 = new Point(2, 2);
        Point p4 = new Point(2, 0);


        Rectangle rectangle = new Rectangle("TestRectangle", Arrays.asList(p1, p2, p3, p4));

        observer.updateRectangle(rectangle);

        double expectedPerimeter = 8.0;

        double actualPerimeter = service.calculatePerimeter(rectangle);

        Assert.assertEquals(actualPerimeter, expectedPerimeter, "Perimeter calculation is incorrect");

        double storedPerimeter = PoolObject.getInstance().getPerimeter("TestRectangle");
        Assert.assertEquals(storedPerimeter, expectedPerimeter, "Stored perimeter is incorrect");

        boolean expectedConvexity = true;
        boolean actualConvexity = service.isConvex(rectangle);
        Assert.assertEquals(actualConvexity, expectedConvexity, "Rectangle should be convex");
    }

    @Test
    public void testCalculatePerimeter_InvalidRectangle() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 2);
        Point p3 = new Point(1, 1);
        Point p4 = new Point(2, 0);

        Rectangle rectangle = new Rectangle("InvalidRectangle", Arrays.asList(p1, p2, p3, p4));

        observer.updateRectangle(rectangle);

        double expectedPerimeter = calculateExpectedPerimeter(p1, p2, p3, p4);

        double actualPerimeter = service.calculatePerimeter(rectangle);

        Assert.assertEquals(actualPerimeter, expectedPerimeter, "Perimeter calculation is incorrect for invalid rectangle");

        double storedPerimeter = PoolObject.getInstance().getPerimeter("InvalidRectangle");
        Assert.assertEquals(storedPerimeter, expectedPerimeter, "Stored perimeter is incorrect");

        boolean expectedConvexity = false;
        boolean actualConvexity = service.isConvex(rectangle);
        Assert.assertEquals(actualConvexity, expectedConvexity, "Rectangle should not be convex");
    }

    private double calculateExpectedPerimeter(Point... points) {
        double perimeter = 0;
        for (int i = 0; i < points.length; i++) {
            Point p1 = points[i];
            Point p2 = points[(i + 1) % points.length];
            perimeter += distance(p1, p2);
        }
        return perimeter;
    }

    private double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p2.getX() - p1.getX(), 2) + Math.pow(p2.getY() - p1.getY(), 2));
    }
}
