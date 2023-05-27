package org.contextmapper.generated.usermanagementcontext.service;

import org.contextmapper.generated.usermanagementcontext.repository.ValidateUserCommandRepository;
import org.contextmapper.generated.usermanagementcontext.service.dto.ValidateUserCommandDTO;
import org.contextmapper.generated.usermanagementcontext.service.mapper.UserInfosMapper;
import org.contextmapper.generated.usermanagementcontext.service.mapper.ValidateUserCommandMapper;
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
        UserInfosMapper userInfosMapper,
        ValidateUserCommandMapper validateUserCommandMapper
    ) {
        super(validateUserCommandRepository, validateUserCommandMapper);
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
    public ValidateUserCommandDTO handleValidateUser(ValidateUserCommandDTO validateUserCommand) {
        log.debug("Request to handle ValidateUserCommand : {}", validateUserCommand);

        final var userId = validateUserCommand.getUserInfos().getId();
        final var statusToSet = validateUserCommand.getUserInfos().getStatus();

        final var userInfos = userInfosService.findOne(userId).map(foundUserInfos -> {
            foundUserInfos.setStatus(statusToSet);
            return userInfosService.save(foundUserInfos);
        });

        validateUserCommand.setUserInfos(
            userInfos.orElseThrow(() -> new RuntimeException("User not found"))
        );

        return save(validateUserCommand);
    }
}
