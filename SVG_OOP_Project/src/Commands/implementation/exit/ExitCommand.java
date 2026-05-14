package Commands.implementation.exit;

import Commands.base.Command;
/**
 * Command that signals the application to terminate.
 */
public class ExitCommand extends Command {
    /**
     * Returns the exit marker recognized by the application loop.
     *
     * @return the string {@code "exit"}
     */
    @Override
    public String execute() {
        return "exit";
    }
}
