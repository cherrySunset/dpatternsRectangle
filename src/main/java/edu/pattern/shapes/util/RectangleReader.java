package edu.pattern.shapes.util;

import edu.pattern.shapes.factory.RectangleFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import edu.pattern.shapes.model.Point;
import edu.pattern.shapes.model.Rectangle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RectangleReader {
    private static final Logger logger = LoggerFactory.getLogger(RectangleReader.class);

    public static List<Rectangle> readRectangles(String filePath) throws IOException {
        List<Rectangle> rectangles = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get(filePath));

        for (String line : lines) {
            try {
                String[] parts = line.split(";");
                String name = parts[0];
                List<Point> points = new ArrayList<>();

                for (int i = 1; i < parts.length; i++) {
                    String[] coordinates = parts[i].split(",");
                    double x = Double.parseDouble(coordinates[0]);
                    double y = Double.parseDouble(coordinates[1]);
                    points.add(new Point(x, y));
                }

                if (points.size() == 4) {
                    Rectangle rectangle = RectangleFactory.createRectangle(name, points);
                    rectangles.add(rectangle);
                    logger.info("Added rectangle: {}", rectangle);
                } else {
                    logger.warn("Skipping invalid line (incorrect number of points): {}", line);
                }
            } catch (Exception e) {
                logger.error("Skipping invalid line: {}", line, e);
            }
        }

        return rectangles;
    }
}
