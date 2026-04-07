package Core;

import Models.Shape;

import java.util.List;

public interface IShape {

    void addShape(Shape shape);
    Shape remove(int shape);
    List<Shape> printAll();
    void clear();
}
