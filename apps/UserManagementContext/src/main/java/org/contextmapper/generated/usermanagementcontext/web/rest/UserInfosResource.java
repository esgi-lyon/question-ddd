package org.contextmapper.generated.usermanagementcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.usermanagementcontext.repository.UserInfosRepository;
import org.contextmapper.generated.usermanagementcontext.service.UserInfosService;
import org.contextmapper.generated.usermanagementcontext.service.dto.UserInfosDTO;
import org.contextmapper.generated.usermanagementcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.usermanagementcontext.domain.UserInfos}.
 */
@RestController
@RequestMapping("/api")
public class UserInfosResource {

    private final Logger log = LoggerFactory.getLogger(UserInfosResource.class);

    private static final String ENTITY_NAME = "userManagementContextUserInfos";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserInfosService userInfosService;

    private final UserInfosRepository userInfosRepository;

    public UserInfosResource(UserInfosService userInfosService, UserInfosRepository userInfosRepository) {
        this.userInfosService = userInfosService;
        this.userInfosRepository = userInfosRepository;
    }

    /**
     * {@code POST  /user-infos} : Create a new userInfos.
     *
     * @param userInfosDTO the userInfosDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userInfosDTO, or with status {@code 400 (Bad Request)} if the userInfos has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/user-infos")
    public ResponseEntity<UserInfosDTO> createUserInfos(@RequestBody UserInfosDTO userInfosDTO) throws URISyntaxException {
        log.debug("REST request to save UserInfos : {}", userInfosDTO);
        if (userInfosDTO.getId() != null) {
            throw new BadRequestAlertException("A new userInfos cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserInfosDTO result = userInfosService.save(userInfosDTO);
        return ResponseEntity
            .created(new URI("/api/user-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /user-infos/:id} : Updates an existing userInfos.
     *
     * @param id the id of the userInfosDTO to save.
     * @param userInfosDTO the userInfosDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userInfosDTO,
     * or with status {@code 400 (Bad Request)} if the userInfosDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userInfosDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/user-infos/{id}")
    public ResponseEntity<UserInfosDTO> updateUserInfos(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody UserInfosDTO userInfosDTO
    ) throws URISyntaxException {
        log.debug("REST request to update UserInfos : {}, {}", id, userInfosDTO);
        if (userInfosDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, userInfosDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!userInfosRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        UserInfosDTO result = userInfosService.update(userInfosDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, userInfosDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /user-infos/:id} : Partial updates given fields of an existing userInfos, field will ignore if it is null
     *
     * @param id the id of the userInfosDTO to save.
     * @param userInfosDTO the userInfosDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userInfosDTO,
     * or with status {@code 400 (Bad Request)} if the userInfosDTO is not valid,
     * or with status {@code 404 (Not Found)} if the userInfosDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the userInfosDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/user-infos/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<UserInfosDTO> partialUpdateUserInfos(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody UserInfosDTO userInfosDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update UserInfos partially : {}, {}", id, userInfosDTO);
        if (userInfosDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, userInfosDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!userInfosRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<UserInfosDTO> result = userInfosService.partialUpdate(userInfosDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, userInfosDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /user-infos} : get all the userInfos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userInfos in body.
     */
    @GetMapping("/user-infos")
    public List<UserInfosDTO> getAllUserInfos() {
        log.debug("REST request to get all UserInfos");
        return userInfosService.findAll();
    }

    /**
     * {@code GET  /user-infos/:id} : get the "id" userInfos.
     *
     * @param id the id of the userInfosDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userInfosDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-infos/{id}")
    public ResponseEntity<UserInfosDTO> getUserInfos(@PathVariable Long id) {
        log.debug("REST request to get UserInfos : {}", id);
        Optional<UserInfosDTO> userInfosDTO = userInfosService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userInfosDTO);
    }

    /**
     * {@code DELETE  /user-infos/:id} : delete the "id" userInfos.
     *
     * @param id the id of the userInfosDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/user-infos/{id}")
    public ResponseEntity<Void> deleteUserInfos(@PathVariable Long id) {
        log.debug("REST request to delete UserInfos : {}", id);
        userInfosService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
