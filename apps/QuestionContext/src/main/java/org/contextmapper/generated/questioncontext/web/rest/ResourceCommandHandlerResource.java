package org.contextmapper.generated.questioncontext.web.rest;

import org.contextmapper.generated.questioncontext.domain.CreateResourceCommand;
import org.contextmapper.generated.questioncontext.domain.ValidateResourceTagLinkageCommand;
import org.contextmapper.generated.questioncontext.service.CreateResourceCommandHandler;
import org.contextmapper.generated.questioncontext.service.ValidateResourceTagLinkageCommandHandler;
import org.contextmapper.generated.questioncontext.service.dto.QuestionResourceDTO;
import org.contextmapper.generated.questioncontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.jhipster.web.util.HeaderUtil;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/handlers")
public class ResourceCommandHandlerResource {

    private final Logger log = LoggerFactory.getLogger(ResourceCommandHandlerResource.class);

    private static final String ENTITY_NAME_CREATE = "questionContextCreateResourceCommand";
    private static final String ENTITY_NAME_VALIDATION = "questionContextValidateResourceTagLinkageCommand";

    private final CreateResourceCommandHandler createResourceCommandService;

    private final ValidateResourceTagLinkageCommandHandler validateResourceTagLinkageCommandHandler;

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    public ResourceCommandHandlerResource(
        CreateResourceCommandHandler createResourceCommandService,
        ValidateResourceTagLinkageCommandHandler validateResourceTagLinkageCommandHandler
    ) {
        this.createResourceCommandService = createResourceCommandService;
        this.validateResourceTagLinkageCommandHandler = validateResourceTagLinkageCommandHandler;
    }

    @PostMapping("/resource-command")
    public ResponseEntity<CreateResourceCommand> handle(@RequestBody QuestionResourceDTO createResource)
        throws URISyntaxException {
        log.debug("REST request to handle create resource command : {}", createResource);
        if (createResource.getId() != null) {
            throw new BadRequestAlertException("A new createResourceCommand cannot already have an ID", ENTITY_NAME_CREATE, "idexists");
        }
        final var result = createResourceCommandService.handleCreateResourceCommand(createResource);
        return ResponseEntity
            .created(new URI("/api/handlers/resource-command/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME_CREATE, result.getId().toString()))
            .body(result);
    }

    @PostMapping("/validate-resource-command")
    public ResponseEntity<ValidateResourceTagLinkageCommand> handleValidateResource(
        @RequestBody ValidateResourceTagLinkageCommand command
    ) throws URISyntaxException {
        log.debug("REST request to handle validate resource command : {}", command);
        if (command.getId() != null) {
            throw new BadRequestAlertException("New entity cannot already have an ID", ENTITY_NAME_VALIDATION, "idexists");
        }
        final var result = validateResourceTagLinkageCommandHandler.handle(command);
        return ResponseEntity
            .created(new URI("/api/handlers/alidate-resource-command/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME_VALIDATION, result.getId().toString()))
            .body(result);
    }
}
