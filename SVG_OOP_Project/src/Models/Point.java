package Models;
/**
 * Represents a mutable point in a two-dimensional coordinate system.
 *
 * <p>This class is used as the anchor position of shapes and as the endpoint of
 * line segments.</p>
 */

/**
 * Represents a 2D point with mutable {@code x} and {@code y} coordinates.
 * Used as the anchor point of every {@link Shape} and as the second
 * endpoint of a {@link Line}.
 */

public class Point
{

    private double x;
    private double y;

    /**
     * Creates a point at the given coordinates.
     *
     * @param x X coordinate
     * @param y Y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /** @return the X coordinate of this point */
    public double getX() {
        return x;
    }

    /**
     * Sets the X coordinate of this point.
     * @param x the new X coordinate
     */
    public void setX(double x) {
        this.x = x;
    }

    /** @return the Y coordinate of this point */
    public double getY() {
        return y;
    }

    /**
     * Sets the Y coordinate of this point.
     *
     * @param y the new Y coordinate
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Moves this point by the given offsets.
     *
     * @param dx how far to move along the X axis (positive = right)
     * @param dy how far to move along the Y axis (positive = down)
     */
    public void translate(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }
}
