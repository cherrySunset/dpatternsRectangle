package edu.pattern.shapes.observer;

import edu.pattern.shapes.model.Rectangle;
import edu.pattern.shapes.pool.PoolObject;
public class RectangleObserver implements Observer {
    private final PoolObject poolObject = PoolObject.getInstance();

    @Override
    public void update(double perimeter) {
        System.out.println("Rectangle perimeter calculated: " + perimeter);
    }
    public void updateRectangle(Rectangle rectangle) {
        poolObject.updateRectangleData(rectangle);
    }
}
