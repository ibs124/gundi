package ibs124.gundi.service.application.seed;

import java.util.List;

import org.springframework.stereotype.Service;

import ibs124.gundi.model.domain.User;
import ibs124.gundi.service.domain.seed.EmailSeedDomainService;
import ibs124.gundi.service.domain.seed.UserSeedDomainService;

@Service
class DataSeedingServiceImpl implements DataSeedingService {

    private final UserSeedDomainService userSeeder;
    private final EmailSeedDomainService emailSeeder;

    public DataSeedingServiceImpl(
            UserSeedDomainService userSeeder,
            EmailSeedDomainService emailSeeder) {
        this.userSeeder = userSeeder;
        this.emailSeeder = emailSeeder;
    }

    @Override
    public void seedTestData() {
        List<User> users = this.userSeeder.seedUsers();

        this.emailSeeder.seedPrimaryEmails(users);

    }

}
