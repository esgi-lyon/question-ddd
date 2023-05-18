package org.contextmapper.generated.sendquestioncontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.sendquestioncontext.repository.UserPreferencesTagInfosRepository;
import org.contextmapper.generated.sendquestioncontext.service.UserPreferencesTagInfosService;
import org.contextmapper.generated.sendquestioncontext.service.dto.UserPreferencesTagInfosDTO;
import org.contextmapper.generated.sendquestioncontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.sendquestioncontext.domain.UserPreferencesTagInfos}.
 */
@RestController
@RequestMapping("/api")
public class UserPreferencesTagInfosResource {

    private final Logger log = LoggerFactory.getLogger(UserPreferencesTagInfosResource.class);

    private final UserPreferencesTagInfosService userPreferencesTagInfosService;

    private final UserPreferencesTagInfosRepository userPreferencesTagInfosRepository;

    public UserPreferencesTagInfosResource(
        UserPreferencesTagInfosService userPreferencesTagInfosService,
        UserPreferencesTagInfosRepository userPreferencesTagInfosRepository
    ) {
        this.userPreferencesTagInfosService = userPreferencesTagInfosService;
        this.userPreferencesTagInfosRepository = userPreferencesTagInfosRepository;
    }

    /**
     * {@code GET  /user-preferences-tag-infos} : get all the userPreferencesTagInfos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userPreferencesTagInfos in body.
     */
    @GetMapping("/user-preferences-tag-infos")
    public List<UserPreferencesTagInfosDTO> getAllUserPreferencesTagInfos() {
        log.debug("REST request to get all UserPreferencesTagInfos");
        return userPreferencesTagInfosService.findAll();
    }

    /**
     * {@code GET  /user-preferences-tag-infos/:id} : get the "id" userPreferencesTagInfos.
     *
     * @param id the id of the userPreferencesTagInfosDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userPreferencesTagInfosDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-preferences-tag-infos/{id}")
    public ResponseEntity<UserPreferencesTagInfosDTO> getUserPreferencesTagInfos(@PathVariable Long id) {
        log.debug("REST request to get UserPreferencesTagInfos : {}", id);
        Optional<UserPreferencesTagInfosDTO> userPreferencesTagInfosDTO = userPreferencesTagInfosService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userPreferencesTagInfosDTO);
    }
}
