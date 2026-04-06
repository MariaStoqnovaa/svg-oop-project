package Core;

import Models.Shape;

import java.util.ArrayList;
import java.util.List;

public class ShapeRepository implements IShape
{
    private List<Shape> shapes = new ArrayList<>();

    @Override
    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    @Override
    public Shape remove(int shape) {
        return shapes.remove(shape);
    }

    @Override
    public List<Shape> printAll() {
        return shapes;
    }

    @Override
    public void clear() {
        shapes.clear();
    }
}
