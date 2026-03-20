package ibs124.gundi.service.sample.impl;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import ibs124.gundi.config.AuthorityConfig;
import ibs124.gundi.model.persistence.AuthorityEntity;
import ibs124.gundi.model.persistence.UserEntity;
import ibs124.gundi.repository.AuthorityRepository;
import ibs124.gundi.repository.UserRepository;
import jakarta.validation.constraints.NotBlank;

@Component
public class UserSeeder {

    private final String USERNAME_DELIMITER = "_";

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    public UserSeeder(PasswordEncoder passwordEncoder, UserRepository userRepository,
            AuthorityRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.authorityRepository = roleRepository;
    }

    public List<UserEntity> seedUsers() {
        if (this.userRepository.count() > 0) {
            return new ArrayList<>();
        }

        String password = this.passwordEncoder.encode(Config.DEFAULT_USER_PASSWORD);

        List<UserEntity> users = Arrays
                .stream(Config.USER_NAMES)
                .map(x -> this.createByFullNameAndPassword(x, password))
                .toList();

        users = this.createRolesByUsers(users);

        return this.userRepository.saveAll(users);

    }

    private List<UserEntity> createRolesByUsers(List<UserEntity> users) {
        Map<String, AuthorityEntity> authorities = this.loadDefaultAuthorities();

        AuthorityEntity rootAuth = authorities
                .get(AuthorityConfig.ROLE_ROOT.getAuthority());

        users.get(0).addAuthority(rootAuth);

        AuthorityEntity adminAuth = authorities
                .get(AuthorityConfig.ROLE_ADMIN.getAuthority());

        for (int i = 0; i < Config.ADMINS_COUNT; i++) {
            users.get(i).addAuthority(adminAuth);
        }

        AuthorityEntity userAuth = authorities.get(AuthorityConfig.ROLE_USER.getAuthority());
        AuthorityEntity sampleAuth = authorities.get(Config.SAMPLE_FACTOR.getAuthority());

        for (int i = 0; i < users.size(); i++) {
            users.get(i).addAuthority(userAuth);
            users.get(i).addAuthority(sampleAuth);
        }

        return users;
    }

    private UserEntity createByFullNameAndPassword(String fullName, String password) {
        Instant now = Instant.now();
        String username = fullName.toLowerCase().replaceAll(" ", USERNAME_DELIMITER);
        String primaryEmail = username.concat(Config.PRIMARY_EMAIL_SUFFIX);

        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(password);
        user.setFullName(fullName);
        user.setPrimaryEmail(primaryEmail);

        user.setLastVerifiedAt(now);
        user.setEnabled(true);
        user.setAccountExpiresAt(
                now.plus(Config.ACCOUNT_EXPIRATION_DAYS, ChronoUnit.DAYS));
        user.setMfaEnabledAt(now);
        return user;
    }

    private Map<String, AuthorityEntity> loadDefaultAuthorities() {

        Map<@NotBlank String, AuthorityEntity> authorities = this.authorityRepository
                .findByNameIn(
                        List.of(
                                AuthorityConfig.ROLE_ROOT.getAuthority(),
                                AuthorityConfig.ROLE_ADMIN.getAuthority(),
                                AuthorityConfig.ROLE_USER.getAuthority()))
                .stream()
                .collect(Collectors.toMap(AuthorityEntity::getName, Function.identity()));

        AuthorityEntity sampleFactorAuthority = new AuthorityEntity(
                Config.SAMPLE_FACTOR.getAuthority());

        sampleFactorAuthority = this.authorityRepository.save(sampleFactorAuthority);

        authorities.put(Config.SAMPLE_FACTOR.getAuthority(), sampleFactorAuthority);

        return authorities;
    }

}