package ibs124.gundi.service.sample.impl;

import static ibs124.gundi.service.sample.impl.Config.USERNAME_DELIMITER;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import ibs124.gundi.model.entity.UserEntity;
import ibs124.gundi.repository.UserRepository;

@Component
public class UserSeeder {

    private final PasswordEncoder passwordEncoder;
    private final AuthoritySeeder authoritySeeder;
    private final UserRepository userRepository;
    private final EmailSeeder emailSeeder;

    public UserSeeder(
            PasswordEncoder passwordEncoder,
            AuthoritySeeder authoritySeeder,
            UserRepository userRepository,
            EmailSeeder emailSeeder) {
        this.passwordEncoder = passwordEncoder;
        this.authoritySeeder = authoritySeeder;
        this.userRepository = userRepository;
        this.emailSeeder = emailSeeder;
    }

    public List<UserEntity> seedUsers() {
        List<UserEntity> users = this.userRepository.findAll();

        if (!users.isEmpty()) {
            return users;
        }

        users = this.createUsers();

        this.authoritySeeder.seedAuthorities(users);

        users = this.userRepository.saveAll(users);

        this.emailSeeder.seedPrimaryEmails(users);

        return users;
    }

    private List<UserEntity> createUsers() {
        String password = this.passwordEncoder
                .encode(Config.DEFAULT_USER_PASSWORD);

        return Arrays
                .stream(Config.USER_NAMES)
                .map(x -> this.createByFullNameAndPassword(x, password))
                .toList();
    }

    private UserEntity createByFullNameAndPassword(String fullName, String password) {
        String username = fullName.toLowerCase().replaceAll(" ", USERNAME_DELIMITER);
        String primaryEmail = username.concat(Config.PRIMARY_EMAIL_SUFFIX);

        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(password);
        user.setFullName(fullName);
        user.setPrimaryEmail(primaryEmail);
        user.setLastMfaVerifiedAt(Instant.now());

        return user;
    }

}