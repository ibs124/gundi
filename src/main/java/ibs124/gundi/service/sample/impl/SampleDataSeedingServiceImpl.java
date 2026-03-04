package ibs124.gundi.service.sample.impl;

import java.util.List;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import ibs124.gundi.model.domain.User;
import ibs124.gundi.service.sample.SampleDataSeedingService;

@Service
class SampleDataSeedingServiceImpl implements SampleDataSeedingService {

    private static final String SEED_ENABLED_PROPERTY = "app.sample.seed";
    private static final String HIBERNATE_DDL = "spring.jpa.hibernate.ddl-auto";
    private static final String HIBERNATRE_DDL_CREATE_DROP = "create-drop";

    private final UserSeeder userSeeder;
    private final EmailSeeder emailSeeder;
    private final Environment env;

    public SampleDataSeedingServiceImpl(
            UserSeeder userSeeder,
            EmailSeeder emailSeeder,
            Environment env) {
        this.userSeeder = userSeeder;
        this.emailSeeder = emailSeeder;
        this.env = env;
    }

    @Override
    public void seedSampleData() {
        if (!this.isSeedingEnabled()) {
            return;
        }

        List<User> users = this.userSeeder.seedUsers();

        this.emailSeeder.seedPrimaryEmails(users);
    }

    private boolean isSeedingEnabled() {
        String hibernateDdlAuto = this.env
                .getProperty(HIBERNATE_DDL);

        if (hibernateDdlAuto.equals(HIBERNATRE_DDL_CREATE_DROP)) {
            return true;
        }

        Boolean isEnabled = this.env
                .getProperty(SEED_ENABLED_PROPERTY, Boolean.class);

        return isEnabled == null ? false : isEnabled;
    }

}
