package ibs124.gundi.console.test.seed;

import java.util.List;

import org.springframework.stereotype.Component;

import ibs124.gundi.console.CommandRunner;
import ibs124.gundi.model.domain.User;

@Component
public class SeedCommandRunner implements CommandRunner {

    private final UserSeeder userSeeder;
    private final EmailSeeder emailSeeder;

    public SeedCommandRunner(
            UserSeeder userSeeder,
            EmailSeeder emailSeeder) {
        this.userSeeder = userSeeder;
        this.emailSeeder = emailSeeder;
    }

    @Override
    public String run(String... args) {
        List<User> users = userSeeder.seedUsers();

        if (users.isEmpty()) {
            return "Users already seeded.";
        }

        this.emailSeeder.seedPrimaryEmails(users);

        return "New users seeded.";

    }

}
