package ibs124.gundi.service.auth.impl;

import org.springframework.stereotype.Service;

import ibs124.gundi.repository.EmailRepository;
import ibs124.gundi.repository.UserRepository;
import ibs124.gundi.service.auth.ValidationService;

@Service
class ValidationServiceImpl implements ValidationService {

    private final UserRepository userRepository;
    private final EmailRepository emailRepository;

    public ValidationServiceImpl(
            UserRepository userRepository,
            EmailRepository emailRepository) {
        this.userRepository = userRepository;
        this.emailRepository = emailRepository;
    }

    @Override
    public boolean isEmailUnique(String name) {
        return !this.emailRepository.existsByEmailAddress(name);
    }

    @Override
    public boolean isUsernameUnique(String username) {
        return !this.userRepository.existsByUsername(username);
    }

}
