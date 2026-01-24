package ibs124.gundi.console;

public abstract class Config {

    public static final String WELCOME = "\n[ Gundi command line interface ]\n";

    public static final String PROMPT = "> ";

    public static final String HELP_COMMAND = "h";

    public static final String EXIT_COMMAND = "q";

    public static final String SEED_COMMAND = "s";

    public static final String SEND_COMMAND = "v";

    public static final String ERROR_FORAMT = "Unknown command: \"%s\". Enter \"" + HELP_COMMAND
            + "\" for help.";

    public static final String HELP = String.format("""

            Enter one of the following commands.
            Commands are consider valid only if spelled correctly.

            %s - Print this message.
            %s - Exit program.
            %s - Seed test data.
            %s - Send verification email.

            """, HELP_COMMAND, EXIT_COMMAND, SEED_COMMAND, SEND_COMMAND);
}
