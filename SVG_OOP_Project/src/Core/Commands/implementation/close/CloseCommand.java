package Core.Commands.implementation.close;

import Core.Commands.base.Command;
import Core.ShapeRepository;
import Core.Messages;

public class CloseCommand extends Command {

    private ShapeRepository repository;

    public CloseCommand(ShapeRepository repository) {
        this.repository = repository;
    }

    @Override
    public String execute() {
        if (!repository.isFileOpened()) {
            return Messages.NO_FILE_OPENED_MESSAGE;
        }

        String fileName = repository.getCurrentFileName();
        repository.closeFile();
        return Messages.FILE_CLOSED_SUCCESSFULLY + fileName;
    }
}
