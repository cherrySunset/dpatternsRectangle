package edu.pattern.shapes.factory;
import edu.pattern.shapes.model.Rectangle;
import edu.pattern.shapes.model.Point;

import java.util.List;
public class RectangleFactory {

    public static Rectangle createRectangle(String name, List<Point> points) {
        if (points.size() != 4) {
            throw new IllegalArgumentException("A rectangle must have 4 points");
        }
        return new Rectangle(name, points);
    }
}
