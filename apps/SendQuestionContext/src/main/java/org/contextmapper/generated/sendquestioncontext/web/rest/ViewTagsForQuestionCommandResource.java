package org.contextmapper.generated.sendquestioncontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.sendquestioncontext.repository.ViewTagsForQuestionCommandRepository;
import org.contextmapper.generated.sendquestioncontext.service.ViewTagsForQuestionCommandService;
import org.contextmapper.generated.sendquestioncontext.service.dto.ViewTagsForQuestionCommandDTO;
import org.contextmapper.generated.sendquestioncontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.sendquestioncontext.domain.ViewTagsForQuestionCommand}.
 */
@RestController
@RequestMapping("/api")
public class ViewTagsForQuestionCommandResource {

    private final Logger log = LoggerFactory.getLogger(ViewTagsForQuestionCommandResource.class);

    private static final String ENTITY_NAME = "sendQuestionContextViewTagsForQuestionCommand";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ViewTagsForQuestionCommandService viewTagsForQuestionCommandService;

    private final ViewTagsForQuestionCommandRepository viewTagsForQuestionCommandRepository;

    public ViewTagsForQuestionCommandResource(
        ViewTagsForQuestionCommandService viewTagsForQuestionCommandService,
        ViewTagsForQuestionCommandRepository viewTagsForQuestionCommandRepository
    ) {
        this.viewTagsForQuestionCommandService = viewTagsForQuestionCommandService;
        this.viewTagsForQuestionCommandRepository = viewTagsForQuestionCommandRepository;
    }

    /**
     * {@code POST  /view-tags-for-question-commands} : Create a new viewTagsForQuestionCommand.
     *
     * @param viewTagsForQuestionCommandDTO the viewTagsForQuestionCommandDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new viewTagsForQuestionCommandDTO, or with status {@code 400 (Bad Request)} if the viewTagsForQuestionCommand has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/view-tags-for-question-commands")
    public ResponseEntity<ViewTagsForQuestionCommandDTO> createViewTagsForQuestionCommand(
        @RequestBody ViewTagsForQuestionCommandDTO viewTagsForQuestionCommandDTO
    ) throws URISyntaxException {
        log.debug("REST request to save ViewTagsForQuestionCommand : {}", viewTagsForQuestionCommandDTO);
        if (viewTagsForQuestionCommandDTO.getId() != null) {
            throw new BadRequestAlertException("A new viewTagsForQuestionCommand cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ViewTagsForQuestionCommandDTO result = viewTagsForQuestionCommandService.save(viewTagsForQuestionCommandDTO);
        return ResponseEntity
            .created(new URI("/api/view-tags-for-question-commands/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /view-tags-for-question-commands/:id} : Updates an existing viewTagsForQuestionCommand.
     *
     * @param id the id of the viewTagsForQuestionCommandDTO to save.
     * @param viewTagsForQuestionCommandDTO the viewTagsForQuestionCommandDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated viewTagsForQuestionCommandDTO,
     * or with status {@code 400 (Bad Request)} if the viewTagsForQuestionCommandDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the viewTagsForQuestionCommandDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/view-tags-for-question-commands/{id}")
    public ResponseEntity<ViewTagsForQuestionCommandDTO> updateViewTagsForQuestionCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ViewTagsForQuestionCommandDTO viewTagsForQuestionCommandDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ViewTagsForQuestionCommand : {}, {}", id, viewTagsForQuestionCommandDTO);
        if (viewTagsForQuestionCommandDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, viewTagsForQuestionCommandDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!viewTagsForQuestionCommandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ViewTagsForQuestionCommandDTO result = viewTagsForQuestionCommandService.update(viewTagsForQuestionCommandDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, viewTagsForQuestionCommandDTO.getId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /view-tags-for-question-commands/:id} : Partial updates given fields of an existing viewTagsForQuestionCommand, field will ignore if it is null
     *
     * @param id the id of the viewTagsForQuestionCommandDTO to save.
     * @param viewTagsForQuestionCommandDTO the viewTagsForQuestionCommandDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated viewTagsForQuestionCommandDTO,
     * or with status {@code 400 (Bad Request)} if the viewTagsForQuestionCommandDTO is not valid,
     * or with status {@code 404 (Not Found)} if the viewTagsForQuestionCommandDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the viewTagsForQuestionCommandDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/view-tags-for-question-commands/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ViewTagsForQuestionCommandDTO> partialUpdateViewTagsForQuestionCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ViewTagsForQuestionCommandDTO viewTagsForQuestionCommandDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ViewTagsForQuestionCommand partially : {}, {}", id, viewTagsForQuestionCommandDTO);
        if (viewTagsForQuestionCommandDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, viewTagsForQuestionCommandDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!viewTagsForQuestionCommandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ViewTagsForQuestionCommandDTO> result = viewTagsForQuestionCommandService.partialUpdate(viewTagsForQuestionCommandDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, viewTagsForQuestionCommandDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /view-tags-for-question-commands} : get all the viewTagsForQuestionCommands.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of viewTagsForQuestionCommands in body.
     */
    @GetMapping("/view-tags-for-question-commands")
    public List<ViewTagsForQuestionCommandDTO> getAllViewTagsForQuestionCommands() {
        log.debug("REST request to get all ViewTagsForQuestionCommands");
        return viewTagsForQuestionCommandService.findAll();
    }

    /**
     * {@code GET  /view-tags-for-question-commands/:id} : get the "id" viewTagsForQuestionCommand.
     *
     * @param id the id of the viewTagsForQuestionCommandDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the viewTagsForQuestionCommandDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/view-tags-for-question-commands/{id}")
    public ResponseEntity<ViewTagsForQuestionCommandDTO> getViewTagsForQuestionCommand(@PathVariable Long id) {
        log.debug("REST request to get ViewTagsForQuestionCommand : {}", id);
        Optional<ViewTagsForQuestionCommandDTO> viewTagsForQuestionCommandDTO = viewTagsForQuestionCommandService.findOne(id);
        return ResponseUtil.wrapOrNotFound(viewTagsForQuestionCommandDTO);
    }

    /**
     * {@code DELETE  /view-tags-for-question-commands/:id} : delete the "id" viewTagsForQuestionCommand.
     *
     * @param id the id of the viewTagsForQuestionCommandDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/view-tags-for-question-commands/{id}")
    public ResponseEntity<Void> deleteViewTagsForQuestionCommand(@PathVariable Long id) {
        log.debug("REST request to delete ViewTagsForQuestionCommand : {}", id);
        viewTagsForQuestionCommandService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
