package ibs124.gundi.service.user;

import org.springframework.stereotype.Service;

import ibs124.gundi.constant.Messages;
import ibs124.gundi.exception.ResourceReadingException;
import ibs124.gundi.mapper.UserMapper;
import ibs124.gundi.model.dto.user.ProfileDto;
import ibs124.gundi.repository.UserRepository;

@Service
class AbstractProfileServiceImpl implements ProfileReadingService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public AbstractProfileServiceImpl(
            UserRepository userRepository,
            UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public ProfileDto findById(Long id) {
        return this.userRepository
                .findById(id)
                .map(x -> this.userMapper.mapToProfileDto(x))
                .orElseThrow(() -> new ResourceReadingException(Messages.USER_NOT_FOUND));
    }
}
