package Commands.implementation.save;

import Commands.base.Command;
import Core.ShapeRepository;
import Models.Shape;
import constants.Messages;
import java.util.List;
/**
 * Command that saves the current repository content to the currently opened
 * file.
 */
public class SaveCommand extends Command {

    private ShapeRepository repository;
    private Messages messages = new Messages();
    /**
     * Creates a save command.
     *
     * @param repository active shape repository
     */
    public SaveCommand(ShapeRepository repository) {
        this.repository = repository;
    }
    /**
     * Serializes all stored shapes and writes them to the active file.
     *
     * @return success or error message
     */
    @Override
    public String execute() {
        if (!repository.isFileOpened()) {
            return messages.NO_FILE_OPENED_MESSAGE;
        }

        try {
            String svgContent = repository.getSvgService().serialize(repository);
            repository.getFileService().writeFile(repository.getCurrentFilePath(), svgContent);
            return messages.FILE_SAVED_SUCCESSFULLY + repository.getCurrentFileName();

        } catch (Exception e) {
            return messages.FILE_SAVE_ERROR + e.getMessage();
        }
    }
}