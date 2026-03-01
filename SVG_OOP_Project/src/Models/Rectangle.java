package Models;

public class Rectangle extends Shape {

    private int a;
    private int b;

    public Rectangle(){}

    public Rectangle(int x, int y, int a, int b) {
        this.x = x;
        this.y = y;
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    @Override
    boolean isInRegion(Shape region) {
        if(region instanceof Rectangle){

        }

        return true;
    }

    @Override
    String toSVG() {
        return "";
    }
}
