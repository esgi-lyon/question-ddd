package org.contextmapper.generated.usermanagementcontext.web.rest;

import org.contextmapper.generated.usermanagementcontext.service.RegisterCommandHandlerService;
import org.contextmapper.generated.usermanagementcontext.service.ValidateUserCommandHandlerService;
import org.contextmapper.generated.usermanagementcontext.service.dto.RegisterCommandDTO;
import org.contextmapper.generated.usermanagementcontext.service.dto.UserInfosDTO;
import org.contextmapper.generated.usermanagementcontext.service.dto.UserValidatedEventDTO;
import org.contextmapper.generated.usermanagementcontext.service.dto.ValidateUserCommandDTO;
import org.contextmapper.generated.usermanagementcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.Optional;


/**
 * REST controller for managing {@link org.contextmapper.generated.usermanagementcontext.domain.RegisterCommand}.
 */
@RestController
@RequestMapping("/api/handlers")
public class CommandHandlerResource {

    private final Logger log = LoggerFactory.getLogger(CommandHandlerResource.class);

    private static final String REGISTER_ENTITY_NAME = "userManagementContextRegisterCommand";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RegisterCommandHandlerService registerCommandHandlerService;

    private final ValidateUserCommandHandlerService validateUserCommandHandler;

    public CommandHandlerResource(
        RegisterCommandHandlerService registerCommandHandlerService,
        ValidateUserCommandHandlerService validateUserCommandHandler
    ) {
        this.registerCommandHandlerService = registerCommandHandlerService;
        this.validateUserCommandHandler = validateUserCommandHandler;
    }

    /**
     * {@code POST  /register-command} : Create a new registerCommand.
     *
     * @param registerCommand the registerCommand to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new registerCommand, or with status {@code 400 (Bad Request)} if the registerCommand has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/register-command")
    public ResponseEntity<RegisterCommandDTO> handleRegisterCommand(@RequestBody RegisterCommandDTO registerCommand) throws URISyntaxException {
        log.debug("REST request to save RegisterCommand : {}", registerCommand);
        if (registerCommand.getId() != null) {
            throw new BadRequestAlertException("A new registerCommand cannot already have an ID", REGISTER_ENTITY_NAME, "idexists");
        }
        RegisterCommandDTO result = registerCommandHandlerService.handleRegister(registerCommand);
        return ResponseEntity
            .created(new URI("/api/handlers/register-command/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, REGISTER_ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PostMapping("/validate-user-command")
    public ResponseEntity<UserInfosDTO> handleRegisterCommand(@RequestBody ValidateUserCommandDTO userCommand) throws URISyntaxException {
        log.debug("REST request to save RegisterCommand : {}", userCommand);
        if (userCommand.getId() != null) {
            throw new BadRequestAlertException("A new registerCommand cannot already have an ID", REGISTER_ENTITY_NAME, "idexists");
        }
        final var result = validateUserCommandHandler.handleValidateUser(userCommand);

        return ResponseEntity
            .created(new URI("/api/handlers/validate-user-command" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, "ValidateUserCommand", result.getId().toString()))
            .body(result);
    }
}
