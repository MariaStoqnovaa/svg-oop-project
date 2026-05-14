package Models;

/**
 * Base class for all SVG shapes supported by the application.
 * Each shape has a position (anchor point) and knows how to render
 * itself as SVG, format itself as a string for display, and test
 * whether it lies fully within a given region.
 *
 * Subclasses must implement {@link #isInRegion(Shape)},
 * {@link #toSVG()}, and {@link #toString()}.
 */


/**
 * Abstract base class for all supported SVG shapes.
 *
 * <p>Every shape has a position and must be able to translate itself, check
 * whether it lies in a region, return an SVG representation, and return a
 * human-readable description.</p>
 */

public abstract class Shape {

    protected Point position;
    /**
     * Creates a shape at the given position.
     *
     * @param x x-coordinate of the shape anchor point
     * @param y y-coordinate of the shape anchor point
     */
    public Shape(double x, double y) {
        //this.position = new Point(Math.abs(x), Math.abs(y));
        this.position = new Point(x, y);
    }


    /**
     * Returns the x-coordinate of the shape position.
     *
     * @return x-coordinate
     */
    public double getX() {
        return position.getX();
    }

    /**
     * Returns the y-coordinate of the shape position.
     *
     * @return y-coordinate
     */
    public double getY() {
        return position.getY();
    }

    /**
     * Moves this shape by the given offsets.
     *
     * @param horizontal how far to move along the X axis (positive = right)
     * @param vertical   how far to move along the Y axis (positive = down)
     */
    public void translate(double horizontal, double vertical) {
        position.translate(horizontal, vertical);
    }

    /**
     * Tests whether this shape lies fully within the given region.
     *
     * @param region a Rectangle or Circle representing the region
     * @return true if every point of this shape is inside the region
     */
    public abstract boolean isInRegion(Shape region);

    public abstract boolean isContainsRec(Rectangle rectangle);
    public abstract boolean isContainsLine(Line line);
    public abstract boolean isContainsCircle(Circle circle);
    /**
     * Returns the SVG representation of this shape, e.g.
     * {@code <rect x="5" y="5" width="10" height="10" fill="green" />}.
     *
     * @return a valid SVG element string
     */
    public abstract String toSVG();

    /**
     * Returns a human-readable description of this shape, e.g.
     * {@code rectangle 5 5 10 10 green}.
     *
     * @return the formatted shape description
     */
    public abstract String toString();

    protected static String formatDisplayNumber(double value)
    {
        if (value == (int) value)
        {
            return String.valueOf((int) value);
        }
        return String.valueOf(value);
    }

    protected static String formatSVGNumber(double value)
    {
        if (value == (int) value)
        {
            return String.valueOf((int) value);
        }
        return String.valueOf(value);
    }
}
