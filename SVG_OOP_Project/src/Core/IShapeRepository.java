package Core;

import Models.Shape;
import java.util.List;

/**
 * Defines the basic operations for working with a shape repository.
 *
 * <p>Implementations of this interface provide storage and access methods for
 * the collection of shapes currently managed by the application.</p>
 */

public interface IShapeRepository {
    /**
     * Adds a shape to the repository.
     *
     * @param shape the shape to add
     */
    void addShape(Shape shape);

    /**
     * Removes a shape by index.
     *
     * @param index zero-based index of the shape to remove
     * @return the removed shape
     */
    Shape remove(int index);

    /**
     * Returns all stored shapes.
     *
     * @return a list containing all shapes in the repository
     */
    List<Shape> getAll();

    /**
     * Removes all shapes from the repository.
     */
    void clear();
}
