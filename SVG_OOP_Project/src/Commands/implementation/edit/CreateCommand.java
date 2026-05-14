package Commands.implementation.edit;

import Commands.base.Command;
import Core.ShapeRepository;
import constants.Messages;
import Models.Shape;
/**
 * Command that adds a newly created shape to the repository.
 */
public class CreateCommand extends Command {

    private Messages messages = new Messages();
    private ShapeRepository repository;
    private Shape shape;
    /**
     * Creates a command that will add the given shape to the repository.
     *
     * @param repository active shape repository
     * @param shape shape to add
     */
    public CreateCommand(ShapeRepository repository, Shape shape) {
        this.repository = repository;
        this.shape = shape;
    }
    /**
     * Adds the shape and returns a creation message.
     *
     * @return success message containing the shape type and count
     */
    @Override
    public String execute() {
        repository.addShape(shape);

        String type = shape.getClass().getSimpleName().toLowerCase();
        int total = repository.getAll().size();
        
        return messages.SHAPE_CREATED_SUCCESSFULLY + type + " (" + total + ")";
    }
}
