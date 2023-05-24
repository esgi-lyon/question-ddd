package org.contextmapper.generated.usermanagementcontext.service;

import org.contextmapper.generated.usermanagementcontext.domain.ValidateUserCommand;
import org.contextmapper.generated.usermanagementcontext.domain.enumeration.UserStatus;
import org.contextmapper.generated.usermanagementcontext.repository.ValidateUserCommandRepository;
import org.contextmapper.generated.usermanagementcontext.service.mapper.UserInfosMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class ValidateUserCommandHandlerService extends ValidateUserCommandService {

    private final Logger log = LoggerFactory.getLogger(ValidateUserCommandHandlerService.class);

    private final UserInfosService userInfosService;

    private final UserInfosMapper userInfosMapper;

    private final ValidateUserCommandRepository validateUserCommandRepository;

    public ValidateUserCommandHandlerService(
        ValidateUserCommandRepository validateUserCommandRepository,
        UserInfosService userInfosService,
        UserInfosMapper userInfosMapper
    ) {
        super(validateUserCommandRepository);
        this.validateUserCommandRepository = validateUserCommandRepository;
        this.userInfosService = userInfosService;
        this.userInfosMapper = userInfosMapper;
    }

    /**
     * Handle a validateUserCommand.
     *
     * @param validateUserCommand the entity to handle.
     * @return the persisted entity.
     */
    public ValidateUserCommand handleValidateUser(ValidateUserCommand validateUserCommand) {
        log.debug("Request to handle ValidateUserCommand : {}", validateUserCommand);

        final var userId = validateUserCommand.getUserInfos().getId();

        final var userInfos = userInfosService.findOne(userId).map(foundUserInfos -> {
            foundUserInfos.setStatus(UserStatus.VALIDATED);
            return userInfosService.save(foundUserInfos);

        });

       validateUserCommand.setUserInfos(
           userInfosMapper.toEntity(
                userInfos.orElseThrow(() -> new RuntimeException("User not found"))
           )
       );

        validateUserCommandRepository.save(validateUserCommand);

        return validateUserCommand;
    }
}
