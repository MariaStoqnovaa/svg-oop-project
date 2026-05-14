package Commands.implementation.close;

import Commands.base.Command;
import Core.ShapeRepository;
import constants.Messages;
/**
 * Command that closes the currently opened file and clears loaded shapes.
 */
public class CloseCommand extends Command {

    private ShapeRepository repository;
    private Messages messages = new Messages();

    /**
     * Creates a close command for the given repository.
     *
     * @param repository active shape repository
     */
    public CloseCommand(ShapeRepository repository) {
        this.repository = repository;
    }

    /**
     * Closes the current file if one is open.
     *
     * @return success or error message
     */
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
