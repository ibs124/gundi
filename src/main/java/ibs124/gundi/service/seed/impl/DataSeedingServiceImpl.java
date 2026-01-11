package ibs124.gundi.service.seed.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import ibs124.gundi.model.domain.User;
import ibs124.gundi.service.seed.DataSeedingService;

@Service
class DataSeedingServiceImpl implements DataSeedingService {

    private final UserSeeder userSeeder;
    private final EmailSeeder emailSeeder;

    public DataSeedingServiceImpl(
            UserSeeder userSeeder,
            EmailSeeder emailSeeder) {
        this.userSeeder = userSeeder;
        this.emailSeeder = emailSeeder;
    }

    @Override
    public void seedTestData() {
        List<User> users = this.userSeeder.seedUsers();

        this.emailSeeder.seedPrimaryEmails(users);

    }

}
