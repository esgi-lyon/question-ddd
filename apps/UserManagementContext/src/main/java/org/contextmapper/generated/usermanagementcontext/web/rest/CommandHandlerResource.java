package org.contextmapper.generated.usermanagementcontext.web.rest;

import org.contextmapper.generated.usermanagementcontext.domain.RegisterCommand;
import org.contextmapper.generated.usermanagementcontext.repository.RegisterCommandRepository;
import org.contextmapper.generated.usermanagementcontext.service.RegisterCommandHandlerService;
import org.contextmapper.generated.usermanagementcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;

import java.net.URI;
import java.net.URISyntaxException;


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


    public CommandHandlerResource(RegisterCommandHandlerService registerCommandHandlerService, RegisterCommandRepository registerCommandRepository) {
        this.registerCommandHandlerService = registerCommandHandlerService;
    }

    /**
     * {@code POST  /register-command} : Create a new registerCommand.
     *
     * @param registerCommand the registerCommand to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new registerCommand, or with status {@code 400 (Bad Request)} if the registerCommand has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/register-command")
    public ResponseEntity<RegisterCommand> handleRegisterCommand(@RequestBody RegisterCommand registerCommand) throws URISyntaxException {
        log.debug("REST request to save RegisterCommand : {}", registerCommand);
        if (registerCommand.getId() != null) {
            throw new BadRequestAlertException("A new registerCommand cannot already have an ID", REGISTER_ENTITY_NAME, "idexists");
        }
        RegisterCommand result = registerCommandHandlerService.handleRegister(registerCommand);
        return ResponseEntity
            .created(new URI("/api/handlers/register-command/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, REGISTER_ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
}
