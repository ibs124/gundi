package ibs124.gundi.service.sample.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import ibs124.gundi.config.PropertyConfig;
import ibs124.gundi.model.domain.User;
import ibs124.gundi.service.sample.SampleDataSeedingService;

@Service
class SampleDataSeedingServiceImpl implements SampleDataSeedingService {

    private final PropertyConfig propertyConfig;
    private final UserSeeder userSeeder;
    private final EmailSeeder emailSeeder;

    public SampleDataSeedingServiceImpl(
            PropertyConfig propertyConfig,
            UserSeeder userSeeder,
            EmailSeeder emailSeeder) {
        this.propertyConfig = propertyConfig;
        this.userSeeder = userSeeder;
        this.emailSeeder = emailSeeder;
    }

    @Override
    public void seedSampleData() {
        if (this.propertyConfig.seedSamples() == false) {
            return;
        }

        List<User> users = this.userSeeder.seedUsers();

        this.emailSeeder.seedPrimaryEmails(users);
    }

}
