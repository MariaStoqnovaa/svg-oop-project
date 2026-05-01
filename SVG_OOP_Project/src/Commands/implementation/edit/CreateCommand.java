package Commands.implementation.edit;

import Commands.base.Command;
import Core.ShapeRepository;
import constants.Messages;
import Models.Shape;

public class CreateCommand extends Command {

    private Messages messages = new Messages();
    private ShapeRepository repository;
    private Shape shape;

    public CreateCommand(ShapeRepository repository, Shape shape) {
        this.repository = repository;
        this.shape = shape;
    }

    @Override
    public String execute() {
        repository.addShape(shape);

        String type = shape.getClass().getSimpleName().toLowerCase();
        int total = repository.getAll().size();
        
        return messages.SHAPE_CREATED_SUCCESSFULLY + type + " (" + total + ")";
    }
}
