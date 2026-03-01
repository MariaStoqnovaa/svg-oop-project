package Models;

public class Circle extends Shape{

    private double radius;

    @Override
    boolean isInRegion(Shape region) {
        return false;
    }

    @Override
    String toSVG() {
        return "";
    }
}
