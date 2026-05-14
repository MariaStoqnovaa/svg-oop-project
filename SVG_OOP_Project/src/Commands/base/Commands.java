package Commands.base;
/**
 * Enumerates the basic command keywords supported by the application.
 */
public enum Commands
{
    OPEN("open"),
    PRINT("print"),
    EDIT("edit"),
    CLOSE("close"),
    SAVE("save"),
    SAVEAS("saveas"),
    HELP("help"),
    EXIT("exit");

    private String value;

    Commands(String value) {
        this.value = value;
    }
    /**
     * Returns the string value associated with the enum constant.
     *
     * @return command keyword value
     */
    public String getValue() {
        return value;
    }
}
