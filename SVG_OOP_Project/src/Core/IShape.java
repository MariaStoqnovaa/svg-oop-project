package Core;

import Models.Shape;

import java.util.List;

public interface IShape {

    //boolean open(String filePath);
    //void close();
    void addShape(Shape shape);
    Shape remove(int shape);
    List<Shape> printAll();
    void clear();
   // void save();
   // void saveAs();
}
