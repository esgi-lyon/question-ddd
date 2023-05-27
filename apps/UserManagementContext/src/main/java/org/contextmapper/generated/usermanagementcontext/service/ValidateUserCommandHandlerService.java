package org.contextmapper.generated.usermanagementcontext.service;

import org.contextmapper.generated.usermanagementcontext.domain.enumeration.UserStatus;
import org.contextmapper.generated.usermanagementcontext.repository.ValidateUserCommandRepository;
import org.contextmapper.generated.usermanagementcontext.service.dto.UserInfosDTO;
import org.contextmapper.generated.usermanagementcontext.service.dto.UserRejectedEventDTO;
import org.contextmapper.generated.usermanagementcontext.service.dto.UserValidatedEventDTO;
import org.contextmapper.generated.usermanagementcontext.service.dto.ValidateUserCommandDTO;
import org.contextmapper.generated.usermanagementcontext.service.mapper.UserInfosMapper;
import org.contextmapper.generated.usermanagementcontext.service.mapper.ValidateUserCommandMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.util.Optional;

@Service
@Primary
public class ValidateUserCommandHandlerService extends ValidateUserCommandService {

    private final Logger log = LoggerFactory.getLogger(ValidateUserCommandHandlerService.class);

    private final UserInfosService userInfosService;

    private final UserInfosMapper userInfosMapper;

    private final ValidateUserCommandRepository validateUserCommandRepository;

    private final UserValidatedEventService userValidatedEventService;

    private final UserRejectedEventService userRejectedEventService;

    public ValidateUserCommandHandlerService(
        ValidateUserCommandRepository validateUserCommandRepository,
        UserInfosService userInfosService,
        UserInfosMapper userInfosMapper,
        ValidateUserCommandMapper validateUserCommandMapper,
        UserValidatedEventService userValidatedEventService,
        UserRejectedEventService userRejectedEventService
    ) {
        super(validateUserCommandRepository, validateUserCommandMapper);
        this.validateUserCommandRepository = validateUserCommandRepository;
        this.userInfosService = userInfosService;
        this.userInfosMapper = userInfosMapper;
        this.userValidatedEventService = userValidatedEventService;
        this.userRejectedEventService = userRejectedEventService;
    }

    /**
     * Handle a validateUserCommand.
     *
     * @param validateUserCommand the entity to handle.
     * @return the persisted entity.
     */
    public UserInfosDTO handleValidateUser(ValidateUserCommandDTO validateUserCommand) {
        log.debug("Request to handle ValidateUserCommand : {}", validateUserCommand);

        final var userId = validateUserCommand.getUserInfos().getId();
        final var statusToSet = Optional.ofNullable(validateUserCommand.getUserInfos().getStatus())
            .orElseThrow();

        return userInfosService.findOne(userId).map(foundUserInfos -> {
            foundUserInfos.setStatus(statusToSet);
            return userInfosService.save(foundUserInfos);
        })
            .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
