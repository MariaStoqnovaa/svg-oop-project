package Commands.implementation.open;

import Commands.base.Command;
import Core.ShapeRepository;
import constants.Messages;
import java.io.File;
/**
 * Command that opens an SVG file and loads its supported shapes into the
 * repository.
 */
public class OpenCommand extends Command {

    private ShapeRepository repository;
    private String filePath;
    private Messages messages = new Messages();

    /**
     * Creates an open command.
     *
     * @param repository active shape repository
     * @param filePath path to the file to open
     */
    public OpenCommand(ShapeRepository repository, String filePath) {
        this.repository = repository;
        this.filePath = filePath;
    }
    /**
     * Reads the file, updates the current file path, parses supported shapes,
     * and returns the execution result.
     *
     * @return success or fatal error message
     */
    @Override
    public String execute() {
        String fileName = new File(filePath).getName();

        try {
            String content = repository.getFileService().readFile(filePath);
            repository.setCurrentFilePath(filePath);

            if (!content.isEmpty()) {
                repository.getSvgService().parse(content, repository);
            }

            return messages.FILE_OPENED_SUCCESSFULLY + fileName;
        } catch (Exception e) {
            return "fatal:" + messages.FILE_LOAD_ERROR + e.getMessage();
        }
    }
}