package org.contextmapper.generated.questioncontext.web.rest;

import org.contextmapper.generated.questioncontext.domain.CreateResourceCommand;
import org.contextmapper.generated.questioncontext.service.ResourceCommandHandler;
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

    private static final String ENTITY_NAME = "questionContextCreateResourceCommand";

    private final ResourceCommandHandler createResourceCommandService;

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    public ResourceCommandHandlerResource(
        ResourceCommandHandler createResourceCommandService
    ) {
        this.createResourceCommandService = createResourceCommandService;
    }

    @PostMapping("/resource-command")
    public ResponseEntity<CreateResourceCommand> handle(@RequestBody QuestionResourceDTO createResource)
        throws URISyntaxException {
        log.debug("REST request to handle create resource command : {}", createResource);
        if (createResource.getId() != null) {
            throw new BadRequestAlertException("A new createResourceCommand cannot already have an ID", ENTITY_NAME, "idexists");
        }
        final var result = createResourceCommandService.handle(createResource);
        return ResponseEntity
            .created(new URI("/api/handlers/resource-command/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
}
