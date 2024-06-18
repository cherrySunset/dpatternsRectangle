package edu.pattern.shapes;

import edu.pattern.shapes.model.Point;
import edu.pattern.shapes.model.Rectangle;
import edu.pattern.shapes.service.RectangleService;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 2);
        Point p3 = new Point(2, 2);
        Point p4 = new Point(2, 0);

        Rectangle rectangle = new Rectangle("TestRectangle", Arrays.asList(p1, p2, p3, p4));

        RectangleService service = RectangleService.getInstance();

        double perimeter = service.calculatePerimeter(rectangle);
        System.out.println("Perimeter of " + rectangle.getName() + ": " + perimeter);

        boolean isConvex = service.isConvex(rectangle);
        System.out.println("Is " + rectangle.getName() + " convex? " + isConvex);

        Point p5 = new Point(0, 0);
        Point p6 = new Point(0, 2);
        Point p7 = new Point(1, 1);
        Point p8 = new Point(2, 0);

        Rectangle invalidRectangle = new Rectangle("InvalidRectangle", Arrays.asList(p5, p6, p7, p8));
        double invalidPerimeter = service.calculatePerimeter(invalidRectangle);
        System.out.println("Perimeter of " + invalidRectangle.getName() + ": " + invalidPerimeter);

        boolean isInvalidConvex = service.isConvex(invalidRectangle);
        System.out.println("Is " + invalidRectangle.getName() + " convex? " + isInvalidConvex);
    }

}