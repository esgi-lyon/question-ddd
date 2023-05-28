package org.contextmapper.generated.sendquestioncontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.sendquestioncontext.repository.NotifiedUsersRepository;
import org.contextmapper.generated.sendquestioncontext.service.NotifiedUsersService;
import org.contextmapper.generated.sendquestioncontext.service.dto.NotifiedUsersDTO;
import org.contextmapper.generated.sendquestioncontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.sendquestioncontext.domain.NotifiedUsers}.
 */
@RestController
@RequestMapping("/api")
public class NotifiedUsersResource {

    private final Logger log = LoggerFactory.getLogger(NotifiedUsersResource.class);

    private static final String ENTITY_NAME = "sendQuestionContextNotifiedUsers";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NotifiedUsersService notifiedUsersService;

    private final NotifiedUsersRepository notifiedUsersRepository;

    public NotifiedUsersResource(NotifiedUsersService notifiedUsersService, NotifiedUsersRepository notifiedUsersRepository) {
        this.notifiedUsersService = notifiedUsersService;
        this.notifiedUsersRepository = notifiedUsersRepository;
    }

    /**
     * {@code POST  /notified-users} : Create a new notifiedUsers.
     *
     * @param notifiedUsersDTO the notifiedUsersDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new notifiedUsersDTO, or with status {@code 400 (Bad Request)} if the notifiedUsers has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/notified-users")
    public ResponseEntity<NotifiedUsersDTO> createNotifiedUsers(@RequestBody NotifiedUsersDTO notifiedUsersDTO) throws URISyntaxException {
        log.debug("REST request to save NotifiedUsers : {}", notifiedUsersDTO);
        if (notifiedUsersDTO.getId() != null) {
            throw new BadRequestAlertException("A new notifiedUsers cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NotifiedUsersDTO result = notifiedUsersService.save(notifiedUsersDTO);
        return ResponseEntity
            .created(new URI("/api/notified-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /notified-users/:id} : Updates an existing notifiedUsers.
     *
     * @param id the id of the notifiedUsersDTO to save.
     * @param notifiedUsersDTO the notifiedUsersDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated notifiedUsersDTO,
     * or with status {@code 400 (Bad Request)} if the notifiedUsersDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the notifiedUsersDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/notified-users/{id}")
    public ResponseEntity<NotifiedUsersDTO> updateNotifiedUsers(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody NotifiedUsersDTO notifiedUsersDTO
    ) throws URISyntaxException {
        log.debug("REST request to update NotifiedUsers : {}, {}", id, notifiedUsersDTO);
        if (notifiedUsersDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, notifiedUsersDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!notifiedUsersRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        NotifiedUsersDTO result = notifiedUsersService.update(notifiedUsersDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, notifiedUsersDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /notified-users/:id} : Partial updates given fields of an existing notifiedUsers, field will ignore if it is null
     *
     * @param id the id of the notifiedUsersDTO to save.
     * @param notifiedUsersDTO the notifiedUsersDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated notifiedUsersDTO,
     * or with status {@code 400 (Bad Request)} if the notifiedUsersDTO is not valid,
     * or with status {@code 404 (Not Found)} if the notifiedUsersDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the notifiedUsersDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/notified-users/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<NotifiedUsersDTO> partialUpdateNotifiedUsers(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody NotifiedUsersDTO notifiedUsersDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update NotifiedUsers partially : {}, {}", id, notifiedUsersDTO);
        if (notifiedUsersDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, notifiedUsersDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!notifiedUsersRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<NotifiedUsersDTO> result = notifiedUsersService.partialUpdate(notifiedUsersDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, notifiedUsersDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /notified-users} : get all the notifiedUsers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of notifiedUsers in body.
     */
    @GetMapping("/notified-users")
    public List<NotifiedUsersDTO> getAllNotifiedUsers() {
        log.debug("REST request to get all NotifiedUsers");
        return notifiedUsersService.findAll();
    }

    /**
     * {@code GET  /notified-users/:id} : get the "id" notifiedUsers.
     *
     * @param id the id of the notifiedUsersDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the notifiedUsersDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/notified-users/{id}")
    public ResponseEntity<NotifiedUsersDTO> getNotifiedUsers(@PathVariable Long id) {
        log.debug("REST request to get NotifiedUsers : {}", id);
        Optional<NotifiedUsersDTO> notifiedUsersDTO = notifiedUsersService.findOne(id);
        return ResponseUtil.wrapOrNotFound(notifiedUsersDTO);
    }

    /**
     * {@code DELETE  /notified-users/:id} : delete the "id" notifiedUsers.
     *
     * @param id the id of the notifiedUsersDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/notified-users/{id}")
    public ResponseEntity<Void> deleteNotifiedUsers(@PathVariable Long id) {
        log.debug("REST request to delete NotifiedUsers : {}", id);
        notifiedUsersService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
