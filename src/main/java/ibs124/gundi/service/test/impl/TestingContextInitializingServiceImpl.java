package ibs124.gundi.service.test.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import ibs124.gundi.model.domain.User;
import ibs124.gundi.service.test.TestingContextInitializingService;

@Service
class TestingContextInitializingServiceImpl implements TestingContextInitializingService {

    private final UserSeeder userSeeder;
    private final EmailSeeder emailSeeder;

    public TestingContextInitializingServiceImpl(
            UserSeeder userSeeder,
            EmailSeeder emailSeeder) {
        this.userSeeder = userSeeder;
        this.emailSeeder = emailSeeder;
    }

    @Override
    public void initializeTestingContext() {
        List<User> users = this.userSeeder.seedUsers();

        this.emailSeeder.seedPrimaryEmails(users);

    }

}
