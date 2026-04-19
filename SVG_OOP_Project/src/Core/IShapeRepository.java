package Core;

import Models.Shape;
import java.util.List;

public interface IShapeRepository {
    void addShape(Shape shape);
    Shape remove(int index);
    List<Shape> getAll();
    void clear();
}
