package edu.pattern.shapes;
import edu.pattern.shapes.model.Point;
import edu.pattern.shapes.model.Rectangle;

import edu.pattern.shapes.validator.RectangleValidator;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
    public class RectangleValidatorTest {
        @Test
        public void testIsValid() {
            Point p1 = new Point(0, 0);
            Point p2 = new Point(0, 2);
            Point p3 = new Point(2, 2);
            Point p4 = new Point(2, 0);

            Rectangle rectangle = new Rectangle("TestRectangle", Arrays.asList(p1, p2, p3, p4));
            RectangleValidator validator = new RectangleValidator();

            boolean isValid = validator.isValid(rectangle);

            Assert.assertTrue(isValid);
        }

        @Test
        public void testIsSquare() {
            Point p1 = new Point(0, 0);
            Point p2 = new Point(0, 2);
            Point p3 = new Point(2, 2);
            Point p4 = new Point(2, 0);

            Rectangle rectangle = new Rectangle("TestRectangle", Arrays.asList(p1, p2, p3, p4));
            RectangleValidator validator = new RectangleValidator();

            boolean isSquare = validator.isSquare(rectangle);

            Assert.assertTrue(isSquare);
        }

        @Test
        public void testIsRhombus() {
            Point p1 = new Point(0, 0);
            Point p2 = new Point(1, 1);
            Point p3 = new Point(2, 0);
            Point p4 = new Point(1, -1);

            Rectangle rectangle = new Rectangle("TestRectangle", Arrays.asList(p1, p2, p3, p4));
            RectangleValidator validator = new RectangleValidator();

            boolean isRhombus = validator.isRhombus(rectangle);

            Assert.assertTrue(isRhombus);
        }

        @Test
        public void testIsTrapezoid() {
            Point p1 = new Point(0, 0);
            Point p2 = new Point(1, 2);
            Point p3 = new Point(3, 2);
            Point p4 = new Point(4, 0);

            Rectangle rectangle = new Rectangle("TestRectangle", Arrays.asList(p1, p2, p3, p4));
            RectangleValidator validator = new RectangleValidator();

            boolean isTrapezoid = validator.isTrapezoid(rectangle);

            Assert.assertTrue(isTrapezoid);
        }
    }

