package edu.pattern.shapes;

import edu.pattern.shapes.model.Point;
import edu.pattern.shapes.model.Rectangle;
import edu.pattern.shapes.observer.RectangleObserver;
import edu.pattern.shapes.service.RectangleService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;

public class RectangleObserverTest {
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

        double expectedPerimeter = 8.0;

        double actualPerimeter = service.calculatePerimeter(rectangle);

        Assert.assertEquals(actualPerimeter, expectedPerimeter, "Perimeter calculation is incorrect");
    }

    @Test
    public void testCalculatePerimeter_InvalidRectangle() {

        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 2);
        Point p3 = new Point(0, 4);
        Point p4 = new Point(2, 0);

        Rectangle rectangle = new Rectangle("InvalidRectangle", Arrays.asList(p1, p2, p3, p4));

        double expectedPerimeter = 10.47213595499958;

        double actualPerimeter = service.calculatePerimeter(rectangle);

        Assert.assertEquals(actualPerimeter, expectedPerimeter, "Perimeter calculation is incorrect for invalid rectangle");
    }
}
