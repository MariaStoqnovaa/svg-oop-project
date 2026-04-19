package Core.Commands.base;

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

    private final String value;

    Commands(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
