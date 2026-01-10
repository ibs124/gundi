package ibs124.gundi.service.seed.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;

import ibs124.gundi.model.domain.Role;
import ibs124.gundi.model.domain.User;
import ibs124.gundi.model.enumm.RoleName;
import ibs124.gundi.repository.RoleRepository;
import ibs124.gundi.repository.UserRepository;

@Component
class UserSeeder {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserSeeder(
            PasswordEncoder passwordEncoder,
            UserRepository userRepository,
            RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<User> seedUsers(JsonNode rootNode) {
        String password = rootNode.at("/password").asText();
        String encodedPassword = this.passwordEncoder.encode(password);

        List<User> users = rootNode
                .at("/names")
                .valueStream()
                .map(x -> x.asText())
                .distinct()
                .map(x -> this.createByFullNameAndPassword(x, encodedPassword))
                .toList();

        users = this.createRolesByUsers(users, rootNode);

        return this.userRepository.saveAll(users);
    }

    private List<User> createRolesByUsers(List<User> users, JsonNode node) {
        HashSet<Role> rootRoles = new HashSet<>(
                this.roleRepository.findAll());

        Set<Role> adminRoles = rootRoles
                .stream()
                .filter(x -> x.getName() != RoleName.ROOT)
                .collect(Collectors.toSet());

        Set<Role> userRoles = adminRoles
                .stream()
                .filter(x -> x.getName() != RoleName.ADMIN)
                .collect(Collectors.toSet());

        users.get(0).setRoles(rootRoles);

        int adminEnd = node.at("/adminCount").asInt() + 1;
        User currentUser;

        for (int i = 1; i < users.size(); i++) {
            currentUser = users.get(i);

            if (i < adminEnd) {
                currentUser.setRoles(adminRoles);
                continue;
            }

            currentUser.setRoles(userRoles);
        }

        return users;
    }

    private User createByFullNameAndPassword(String fullName, String password) {
        String username = fullName.toLowerCase().replaceAll(" ", "_");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setFullName(fullName);
        user.setEnabled(true);
        return user;
    }
}