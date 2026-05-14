package Commands.implementation.edit;

import Commands.base.Command;
import Core.ShapeRepository;
import constants.Messages;
import Models.Shape;
/**
 * Command that lists all shapes fully contained within a given region.
 */
public class WithinCommand extends Command {

    private Messages messages = new Messages();
    private ShapeRepository repository;
    private Shape region;
    /**
     * Creates a within command.
     *
     * @param repository active shape repository
     * @param region region used for containment checks
     */
    public WithinCommand(ShapeRepository repository, Shape region) {
        this.repository = repository;
        this.region = region;
    }
    /**
     * Finds and returns all shapes that lie inside the given region.
     *
     * @return matching shapes or a message that none were found
     */
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
