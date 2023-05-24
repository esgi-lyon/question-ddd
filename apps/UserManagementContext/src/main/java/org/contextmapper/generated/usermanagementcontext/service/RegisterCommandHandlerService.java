package org.contextmapper.generated.usermanagementcontext.service;

import org.contextmapper.generated.usermanagementcontext.domain.RegisterCommand;
import org.contextmapper.generated.usermanagementcontext.domain.enumeration.UserStatus;
import org.contextmapper.generated.usermanagementcontext.repository.CustomUserInfosRepository;
import org.contextmapper.generated.usermanagementcontext.repository.RegisterCommandRepository;
import org.contextmapper.generated.usermanagementcontext.service.dto.UserInfosDTO;
import org.contextmapper.generated.usermanagementcontext.service.dto.UserWaitingForValidationEventDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * Service Implementation for managing {@link RegisterCommand}.
 */
@Service
@Transactional
@Primary
public class RegisterCommandHandlerService extends RegisterCommandService {

    private final Logger log = LoggerFactory.getLogger(RegisterCommandHandlerService.class);

    private final UserInfosService userInfosService;

    private final PasswordEncoder passwordEncoder;

    private final CustomUserInfosRepository userInfosRepository;

    private final UserWaitingForValidationEventService userWaitingForValidationEventService;

    public RegisterCommandHandlerService(
        RegisterCommandRepository registerCommandRepository,
        UserInfosService userInfosService,
        PasswordEncoder passwordEncoder,
        CustomUserInfosRepository userInfosRepository,
        UserWaitingForValidationEventService userWaitingForValidationEventService
    ) {
        super(registerCommandRepository);
        this.userInfosService = userInfosService;
        this.passwordEncoder = passwordEncoder;
        this.userInfosRepository = userInfosRepository;
        this.userWaitingForValidationEventService = userWaitingForValidationEventService;
    }

    /**
     * Handle a registerCommand.
     *
     * @param registerCommand the entity to handle.
     * @return the persisted entity.
     */
    public RegisterCommand handleRegister(RegisterCommand registerCommand) {
        log.debug("Request to handle RegisterCommand : {}", registerCommand);

        if (Objects.isNull(registerCommand.getMail()) || Objects.isNull(registerCommand.getPassword())) {
            throw new RuntimeException("Mandatory credentials missing");
        }

        if (userInfosRepository.findByMail(registerCommand.getMail()).isPresent()){
            throw new RuntimeException("Mail already exists");
        }

        final var userInfos = new UserInfosDTO();
        userInfos.setFirstname(registerCommand.getFirstname());
        userInfos.setLastname(registerCommand.getLastname());
        userInfos.setMail(registerCommand.getMail());
        userInfos.setPassword(passwordEncoder.encode(registerCommand.getPassword()));
        userInfos.setRole(registerCommand.getRole());
        userInfos.setStatus(UserStatus.WAITING_VALIDATION);
        userInfosService.save(userInfos);

        final var userWaitingForValidationEventDTO = new UserWaitingForValidationEventDTO();
        userWaitingForValidationEventDTO.setUserInfos(userInfos);

        userWaitingForValidationEventService.save(userWaitingForValidationEventDTO);

        return save(registerCommand);
    }

}
