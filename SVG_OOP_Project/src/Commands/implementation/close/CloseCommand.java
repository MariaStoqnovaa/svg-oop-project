package Commands.implementation.close;

import Commands.base.Command;
import Core.ShapeRepository;
import constants.Messages;

public class CloseCommand extends Command {

    private ShapeRepository repository;
    private Messages messages = new Messages();

    public CloseCommand(ShapeRepository repository) {
        this.repository = repository;
    }

    @Override
    public String execute() {
        if (!repository.isFileOpened()) {
            return messages.NO_FILE_OPENED_MESSAGE;
        }

        String fileName = repository.getCurrentFileName();
        repository.closeFile();
        return messages.FILE_CLOSED_SUCCESSFULLY + fileName;
    }
}
