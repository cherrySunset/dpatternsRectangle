package edu.pattern.shapes;

import edu.pattern.shapes.model.Point;
import edu.pattern.shapes.model.Rectangle;
import edu.pattern.shapes.util.RectangleReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class RectangleReaderTest {
    @Test
    public void testReadRectangles() throws IOException {
        String filePath = "src/main/resources/test-data.txt";

        List<Rectangle> rectangles = RectangleReader.readRectangles(filePath);

        int expectedSize = 3;

        Assert.assertEquals(rectangles.size(), expectedSize);

        Rectangle rectangle = rectangles.getFirst();
        Assert.assertEquals(rectangle.getName(), "Rectangle1");

        List<Point> points = rectangle.getPoints();
        Assert.assertEquals(points.size(), 4);
        Assert.assertEquals(points.get(0), new Point(0, 0));
        Assert.assertEquals(points.get(1), new Point(0, 2));
        Assert.assertEquals(points.get(2), new Point(2, 2));
        Assert.assertEquals(points.get(3), new Point(2, 0));
    }
}
