package Core;

import Models.Shape;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ShapeRepository implements IShapeRepository {

    private final List<Shape> shapes = new ArrayList<Shape>();
    private final FileService fileService = new FileService();
    private final SvgService svgService = new SvgService();
    private String currentFilePath = null;

    public ShapeRepository() {
    }


    public boolean isFileOpened() {
        return currentFilePath != null;
    }

    public String getCurrentFilePath() {
        return currentFilePath;
    }

    public String getCurrentFileName() {
        if (currentFilePath == null) return null;
        return new File(currentFilePath).getName();
    }

    public void setCurrentFilePath(String path) {
        this.currentFilePath = path;
    }

    public void closeFile() {
        shapes.clear();
        currentFilePath = null;
    }

    public FileService getFileService() {
        return fileService;
    }

    public SvgService getSvgService() {
        return svgService;
    }

    @Override
    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    @Override
    public Shape remove(int index) {
        return shapes.remove(index);
    }

    @Override
    public List<Shape> getAll() {
        return shapes;
    }

    @Override
    public void clear() {
        shapes.clear();
    }
}
