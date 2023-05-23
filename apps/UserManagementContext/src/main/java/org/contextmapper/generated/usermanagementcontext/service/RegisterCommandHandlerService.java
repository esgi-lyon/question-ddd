package org.contextmapper.generated.usermanagementcontext.service;

import org.contextmapper.generated.usermanagementcontext.domain.RegisterCommand;
import org.contextmapper.generated.usermanagementcontext.repository.CustomUserInfosRepository;
import org.contextmapper.generated.usermanagementcontext.repository.RegisterCommandRepository;
import org.contextmapper.generated.usermanagementcontext.service.dto.UserInfosDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public RegisterCommandHandlerService(
        RegisterCommandRepository registerCommandRepository,
        UserInfosService userInfosService,
        PasswordEncoder passwordEncoder,
        CustomUserInfosRepository userInfosRepository,
        AuthenticationManager authenticationManager
    ) {
        super(registerCommandRepository);
        this.userInfosService = userInfosService;
        this.passwordEncoder = passwordEncoder;
        this.userInfosRepository = userInfosRepository;
    }

    /**
     * Handle a registerCommand.
     *
     * @param registerCommand the entity to handle.
     * @return the persisted entity.
     */
    public RegisterCommand handleRegister(RegisterCommand registerCommand) {
        log.debug("Request to handle RegisterCommand : {}", registerCommand);

        if (userInfosRepository.findByMail(registerCommand.getMail()).isPresent()){
            throw new RuntimeException("Mail already exists");
        }

        final var userInfos = new UserInfosDTO();
        userInfos.setFirstname(registerCommand.getFirstname());
        userInfos.setLastname(registerCommand.getLastname());
        userInfos.setMail(registerCommand.getMail());
        userInfos.setPassword(passwordEncoder.encode(registerCommand.getPassword()));
        userInfos.setRole(registerCommand.getRole());
        userInfosService.save(userInfos);

        return save(registerCommand);
    }

}
