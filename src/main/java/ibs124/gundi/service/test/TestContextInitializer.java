package ibs124.gundi.service.test;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import ibs124.gundi.model.domain.User;
import ibs124.gundi.service.test.seed.EmailSeeder;
import ibs124.gundi.service.test.seed.UserSeeder;
import ibs124.gundi.service.test.verification.VerificationEmailSender;

@Component
@Order(2)
class TestContextInitializer implements CommandLineRunner {

    private final VerificationEmailSender verificationEmailSender;
    private final UserSeeder userSeeder;
    private final EmailSeeder emailSeeder;

    public TestContextInitializer(
            UserSeeder userSeeder,
            EmailSeeder emailSeeder,
            VerificationEmailSender verificationEmailSender) {
        this.userSeeder = userSeeder;
        this.emailSeeder = emailSeeder;
        this.verificationEmailSender = verificationEmailSender;
    }

    @Override
    public void run(String... args) throws Exception {

        List<User> users = this.userSeeder.seedUsers();

        this.emailSeeder.seedPrimaryEmails(users);

        this.verificationEmailSender.run();

    }
}
