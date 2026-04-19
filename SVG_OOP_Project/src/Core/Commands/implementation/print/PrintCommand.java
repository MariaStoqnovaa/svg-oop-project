package Core.Commands.implementation.print;

import Core.Commands.base.Command;
import Core.ShapeRepository;
import Core.Messages;
import Models.Shape;
import java.util.List;

public class PrintCommand extends Command {

    private ShapeRepository repository;

    public PrintCommand(ShapeRepository repository) {
        this.repository = repository;
    }

    @Override
    public String execute() {
        List<Shape> shapes = repository.getAll();

        if (shapes.isEmpty()) {
            return Messages.NO_SHAPES_LOADED;
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < shapes.size(); i++) {
            result.append(i + 1).append(". ").append(shapes.get(i).toString()).append("\n");
        }

        result.setLength(result.length() - 1);
        return result.toString();
    }
}
