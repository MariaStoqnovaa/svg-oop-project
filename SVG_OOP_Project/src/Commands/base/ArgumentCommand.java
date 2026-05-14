package Commands.base;

import java.util.List;
/**
 * Abstract command that stores a list of string arguments.
 *
 * <p>This class extends {@link Command} and serves as a reusable base for
 * commands that need additional input parameters.</p>
 */
public abstract class ArgumentCommand extends  Command
{
    private final List<String> arguments;

    /**
     * Creates a command with the given argument list.
     *
     * @param arguments command arguments
     */
    public ArgumentCommand(List<String> arguments) {
        this.arguments = arguments;
    }
    /**
     * Returns the stored command arguments.
     *
     * @return list of arguments
     */
    protected List<String> getArguments() {
        return arguments;
    }
}
