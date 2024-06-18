package edu.pattern.shapes.model;

import java.util.List;
import java.util.Objects;

public class Rectangle {

    private String name;
    private List<Point> points;

    public Rectangle(String name, List<Point> points) {
        this.name = name;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "name='" + name + '\'' +
                ", points=" + points +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rectangle rectangle = (Rectangle) o;

        if (!Objects.equals(name, rectangle.name)) return false;
        return Objects.equals(points, rectangle.points);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (points != null ? points.hashCode() : 0);
        return result;
    }
}
