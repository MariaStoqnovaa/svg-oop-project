package Core;

import Models.Shape;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Stores the current application state related to shapes and file handling.
 *
 * <p>The repository keeps the list of loaded shapes, the path of the currently
 * opened file, and helper services for file and SVG processing.</p>
 */

public class ShapeRepository implements IShapeRepository {

    private final List<Shape> shapes = new ArrayList<Shape>();
    private final FileService fileService = new FileService();
    private final SvgService svgService = new SvgService();
    private String currentFilePath = null;

    /**
     * Creates an empty shape constructor.
     */
    public ShapeRepository() {
    }

    /**
     * Checks whether a file is currently opened.
     *
     * @return {@code true} if a file path is set, otherwise {@code false}
     */
    public boolean isFileOpened() {
        return currentFilePath != null;
    }

    /**
     * Returns the full path of the currently opened file.
     *
     * @return current file path, or {@code null} if no file is open
     */
    public String getCurrentFilePath() {
        return currentFilePath;
    }

    /**
     * Returns only the file name of the currently opened file.
     *
     * @return current file name, or {@code null} if no file is open
     */
    public String getCurrentFileName() {
        if (currentFilePath == null) return null;
        return new File(currentFilePath).getName();
    }
    /**
     * Sets the current file path.
     *
     * @param path full path to the active file
     */
    public void setCurrentFilePath(String path) {
        this.currentFilePath = path;
    }

    /**
     * Closes the current file by clearing all loaded shapes and resetting the
     * current file path.
     */
    public void closeFile() {
        shapes.clear();
        currentFilePath = null;
    }

    /**
     * Returns the file service used by the repository.
     *
     * @return file service instance
     */
    public FileService getFileService() {
        return fileService;
    }
    /**
     * Returns the SVG service used by the repository.
     *
     * @return SVG service instance
     */

    public SvgService getSvgService() {
        return svgService;
    }

    /**
     * Adds a shape to the repository.
     *
     * @param shape the shape to add
     */

    @Override
    public void addShape(Shape shape) {
        shapes.add(shape);
    }
    /**
     * Removes a shape by index.
     *
     * @param index zero-based shape index
     * @return the removed shape
     */

    @Override
    public Shape remove(int index) {
        return shapes.remove(index);
    }
    /**
     * Returns the full list of stored shapes.
     *
     * @return list of shapes
     */

    @Override
    public List<Shape> getAll() {
        return shapes;
    }
    /**
     * Clears all shapes from the repository.
     */

    @Override
    public void clear() {
        shapes.clear();
    }
}
