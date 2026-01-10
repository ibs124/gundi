package ibs124.gundi.service.seed.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;

import ibs124.gundi.model.domain.User;
import ibs124.gundi.service.seed.DataSeedingService;

@Service
class DataSeedingServiceImpl implements DataSeedingService {

    private final JsonReader jsonReader;
    private final UserSeeder userSeeder;
    private final EmailSeeder emailSeeder;

    public DataSeedingServiceImpl(
            UserSeeder userSeeder,
            EmailSeeder emailSeeder,
            JsonReader jsonReader) {
        this.jsonReader = jsonReader;
        this.userSeeder = userSeeder;
        this.emailSeeder = emailSeeder;
    }

    @Override
    public void seedTestData() {
        JsonNode rootNode = this.jsonReader.readByClassPath("json/users.json");

        List<User> users = this.userSeeder.seedUsers(rootNode);

        this.emailSeeder.seedPrimaryEmails(users, rootNode);

    }

}
