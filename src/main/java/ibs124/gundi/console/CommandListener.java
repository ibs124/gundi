package ibs124.gundi.console;

import static ibs124.gundi.console.Config.*;

import java.util.Scanner;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import ibs124.gundi.console.test.seed.SeedCommandRunner;
import ibs124.gundi.console.test.verification.VerificationCommandRunner;

@Component
public class CommandListener {

    private final SeedCommandRunner seedeer;
    private final VerificationCommandRunner sender;

    public CommandListener(
            SeedCommandRunner seedCommandRunner,
            VerificationCommandRunner verificationCommandRunner) {
        this.seedeer = seedCommandRunner;
        this.sender = verificationCommandRunner;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void startConsole() {
        new Thread(this::listen).start();
    }

    private void listen() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println(WELCOME);
            System.out.println(HELP);

            while (true) {
                System.out.print(PROMPT);

                String command = scanner.nextLine().trim();

                String message = String.format(ERROR_FORAMT, command);

                switch (command) {
                    case EXIT_COMMAND -> System.exit(0);
                    case HELP_COMMAND -> System.out.println(HELP);
                    case SEED_COMMAND -> message = this.seedeer.run();
                    case SEND_COMMAND -> message = this.sender.run();
                }

                System.out.println(message);
            }
        }
    }

}
