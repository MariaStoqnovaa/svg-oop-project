package constants;

/**
 * Stores reusable user-facing messages returned by commands and services.
 *
 * <p>This class centralizes common text constants such as success messages,
 * error messages, and hints shown to the user.</p>
 */

public class Messages {

    public String NO_FILE_OPENED_MESSAGE = "No file is currently open.";
    public String FILE_OPENED_SUCCESSFULLY = "Successfully opened ";
    public String FILE_CREATED_SUCCESSFULLY = "Successfully opened ";
    public String FILE_CLOSED_SUCCESSFULLY = "Successfully closed ";
    public String FILE_SAVED_SUCCESSFULLY = "Successfully saved the changes to ";
    public String FILE_LOAD_ERROR = "Error loading file: ";
    public String FILE_SAVE_ERROR = "Error saving file: ";

    public String NO_SHAPES_LOADED = "No shapes loaded.";
    public String SHAPE_CREATED_SUCCESSFULLY = "Successfully created ";
    public String SHAPE_ERASED_SUCCESSFULLY = "Erased a ";
    public String NO_SHAPES_IN_REGION = "No figures are located within ";
    public String NO_FIGURE_WITH_NUMBER = "There is no figure number ";
    public String TRANSLATED_ALL_FIGURES = "Translated all figures";
    public String TRANSLATED_FIGURE = "Translated figure ";

    public String EXITING_MESSAGE = "Exit";
    public String OPEN_FILE_FIRST = "No file is open. Use 'open <file>' first.";
    public String UNKNOWN_COMMAND = "Unknown command. Type 'help' to see all commands.";
    public String UNKNOWN_SHAPE_TYPE = "Unknown shape type. Supported: rectangle, circle, line";
}
