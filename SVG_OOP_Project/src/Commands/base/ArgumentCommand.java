package Commands.base;

import java.util.List;

public abstract class ArgumentCommand extends  Command
{
    private final List<String> arguments;

    public ArgumentCommand(List<String> arguments) {
        this.arguments = arguments;
    }

    protected List<String> getArguments() {
        return arguments;
    }
}
