package Commands.implementation.edit;

import Commands.base.Command;
import Core.ShapeRepository;
import constants.Messages;
import Models.Shape;

public class WithinCommand extends Command {

    private Messages messages = new Messages();
    private ShapeRepository repository;
    private Shape region;

    public WithinCommand(ShapeRepository repository, Shape region) {
        this.repository = repository;
        this.region = region;
    }

    @Override
    public String execute() {
        StringBuilder result = new StringBuilder();
        int count = 1;

        for (Shape shape : repository.getAll()) {
            if (shape.isInRegion(region)) {
                result.append(count).append(". ").append(shape.toString()).append("\n");
                count++;
            }
        }

        if (result.length() == 0) {
            return messages.NO_SHAPES_IN_REGION + region.toString();
        }

        result.setLength(result.length() - 1);
        return result.toString();
    }
}
