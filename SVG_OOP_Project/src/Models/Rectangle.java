package Models;

public class Rectangle extends Shape {

    private int a;
    private int b;
    private String colorFill;

    public Rectangle(double x, double y, int a, int b, String colorFill) {
        super(x, y);
        this.a = a;
        this.b = b;
        this.colorFill = colorFill;
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
    boolean isInRegion(Shape region)
    {
        if(region instanceof Rectangle rect)
        {
            return this.x >= rect.getX() &&
                    this.y >= rect.getY() &&
                    (this.x + this.a) <= (rect.getX() + rect.getA()) &&
                    (this.y + this.b) <= (rect.getY() + rect.getB());
        }
        else if(!(region instanceof Circle circ))
        {
            return false;
        }
        else
        {
            return this.circleIsPoint(this.x, this.y, circ) &&
                    this.circleIsPoint(this.x + this.a, this.y, circ) &&
                    this.circleIsPoint(this.x, this.y + this.b, circ) &&
                    this.circleIsPoint(this.x + this.a, this.y + this.b, circ);
        }
    }

    private boolean circleIsPoint(double px,double py,Circle c)
    {
        double dx = px - c.getX();
        double dy = py - c.getY();
        return dx * dx + dy * dy <= c.getRadius() * c.getRadius();
    }
    @Override
    String toSVG() {
        return "<rectangle center X= " + this.x + ",center Y= " + this.y + ",width= " + this.a +",height: "+ this.b+ ",color fill = " + colorFill;

    }
}
