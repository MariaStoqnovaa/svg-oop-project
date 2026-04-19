package Core.Commands.implementation.edit;

import Core.Commands.base.Command;
import Core.ShapeRepository;
import Core.Messages;
import Models.Shape;

public class WithinCommand extends Command {

    private ShapeRepository repository;
    private Shape region;

    public WithinCommand(ShapeRepository repository, Shape region) {
        this.repository = repository;
        this.region = region;
    }

    @Override
    public String execute() {
        StringBuilder result = new StringBuilder();
        
        for (Shape shape : repository.getAll()) {
            if (shape.isInRegion(region)) {
                result.append(shape.toString()).append("\n");
            }
        }

        String regionType = region.getClass().getSimpleName().toLowerCase();

        if (result.length() == 0) {
            return Messages.NO_SHAPES_IN_REGION + regionType;
        }

        result.setLength(result.length() - 1);
        return result.toString();
    }
}
