package Commands.base;
/**
 * Abstract base class for all executable commands in the application.
 *
 * <p>Every command must implement the {@link #execute()} method and return a
 * textual result describing the outcome.</p>
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @return command result message
     */
    public abstract String execute();
}
