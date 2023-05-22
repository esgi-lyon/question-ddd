package org.contextmapper.generated.skillcontext.web.rest;

import org.contextmapper.generated.skillcontext.domain.CreateCategoryCommand;
import org.contextmapper.generated.skillcontext.domain.CreateTagCommand;
import org.contextmapper.generated.skillcontext.service.CreateCategoryCommandHandler;
import org.contextmapper.generated.skillcontext.service.CreateTagCommandHandler;
import org.contextmapper.generated.skillcontext.service.dto.CategoryInfosDTO;
import org.contextmapper.generated.skillcontext.service.dto.TagWithCategoryDTO;
import org.contextmapper.generated.skillcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * REST controller for managing {@link CreateTagCommand}.
 */
@RestController
@RequestMapping("/api/handlers")
public class CommandHandlerResource {

    private final Logger log = LoggerFactory.getLogger(CommandHandlerResource.class);

    private static final String TAG_ENTITY_NAME = "skillContextCreateTagCommand";
    private static final String CATEGORY_ENTITY_NAME = "skillContextCreateCategoryCommand";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CreateTagCommandHandler createTagCommandHandler;

    private final CreateCategoryCommandHandler createCategoryCommandHandler;

    public CommandHandlerResource(
        CreateTagCommandHandler createTagCommandService,
        CreateCategoryCommandHandler createCategoryCommandHandler
    ) {
        this.createTagCommandHandler = createTagCommandService;
        this.createCategoryCommandHandler = createCategoryCommandHandler;
    }

    /**
     * {@code POST  /create-tag-command} : Create a new createTagCommand.
     *
     * @param createTag the createTagCommand to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new createTagCommand, or with status {@code 400 (Bad Request)} if the createTagCommand has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/create-tag-command")
    public ResponseEntity<CreateTagCommand> createCreateTagCommand(@RequestBody TagWithCategoryDTO createTag)
        throws URISyntaxException {
        log.debug("REST request to save CreateTagCommand : {}", createTag);
        if (createTag.getId() != null) {
            throw new BadRequestAlertException("A new createTagCommand cannot already have an ID", TAG_ENTITY_NAME, "idexists");
        }
        CreateTagCommand result = createTagCommandHandler.handle(createTag);
        return ResponseEntity
            .created(new URI("/api/handlers/create-tag-command" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, TAG_ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code POST  /create-category-command} : Create a new createCategoryCommand.
     *
     * @param createCategory the createCategoryCommand to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new createCategoryCommand, or with status {@code 400 (Bad Request)} if the createCategoryCommand has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/create-category-command")
    public ResponseEntity<CreateCategoryCommand> createCreateCategoryCommand(@RequestBody CategoryInfosDTO createCategory)
        throws URISyntaxException {
        log.debug("REST request to save CreateCategoryCommand : {}", createCategory);
        if (createCategory.getId() != null) {
            throw new BadRequestAlertException("A new createCategory cannot already have an ID", CATEGORY_ENTITY_NAME, "idexists");
        }
        CreateCategoryCommand result = createCategoryCommandHandler.handle(createCategory);
        return ResponseEntity
            .created(new URI("/api/handlers/create-category" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, CATEGORY_ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

}
