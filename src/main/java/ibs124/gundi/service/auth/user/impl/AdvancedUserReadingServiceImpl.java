package ibs124.gundi.service.auth.user.impl;

import org.springframework.stereotype.Service;

import ibs124.gundi.mapper.UserMapper;
import ibs124.gundi.model.dto.auth.UserLoginDetailsDto;
import ibs124.gundi.repository.EmailRepository;
import ibs124.gundi.repository.UserRepository;
import ibs124.gundi.service.auth.user.LoginDetailsReadingService;
import ibs124.gundi.service.auth.user.ValidationService;

@Service
class AdvancedUserReadingServiceImpl implements ValidationService, LoginDetailsReadingService {

    private final UserRepository userRepository;
    private final EmailRepository emailRepository;
    private final UserMapper userMapper;

    public AdvancedUserReadingServiceImpl(
            UserRepository userRepository,
            EmailRepository emailRepository,
            UserMapper userMapper) {
        this.userRepository = userRepository;
        this.emailRepository = emailRepository;
        this.userMapper = userMapper;
    }

    @Override
    public boolean isEmailUnique(String name) {
        return !this.emailRepository.existsByEmailAddress(name);
    }

    @Override
    public boolean isUsernameUnique(String username) {
        return !this.userRepository.existsByUsername(username);
    }

    @Override
    public UserLoginDetailsDto findByUsernameOrEmail(String request) {
        return this.userRepository
                .findByUsernameOrPrimaryEmail(request, request)
                .map(x -> this.userMapper.mapToLoginDetailsDto(x))
                .orElse(null);
    }

}
