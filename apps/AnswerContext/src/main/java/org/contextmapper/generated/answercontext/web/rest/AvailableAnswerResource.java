package org.contextmapper.generated.answercontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.answercontext.repository.AvailableAnswerRepository;
import org.contextmapper.generated.answercontext.service.AvailableAnswerService;
import org.contextmapper.generated.answercontext.service.dto.AvailableAnswerDTO;
import org.contextmapper.generated.answercontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.answercontext.domain.AvailableAnswer}.
 */
@RestController
@RequestMapping("/api")
public class AvailableAnswerResource {

    private final Logger log = LoggerFactory.getLogger(AvailableAnswerResource.class);

    private final AvailableAnswerService availableAnswerService;

    private final AvailableAnswerRepository availableAnswerRepository;

    public AvailableAnswerResource(AvailableAnswerService availableAnswerService, AvailableAnswerRepository availableAnswerRepository) {
        this.availableAnswerService = availableAnswerService;
        this.availableAnswerRepository = availableAnswerRepository;
    }

    /**
     * {@code GET  /available-answers} : get all the availableAnswers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of availableAnswers in body.
     */
    @GetMapping("/available-answers")
    public List<AvailableAnswerDTO> getAllAvailableAnswers() {
        log.debug("REST request to get all AvailableAnswers");
        return availableAnswerService.findAll();
    }

    /**
     * {@code GET  /available-answers/:id} : get the "id" availableAnswer.
     *
     * @param id the id of the availableAnswerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the availableAnswerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/available-answers/{id}")
    public ResponseEntity<AvailableAnswerDTO> getAvailableAnswer(@PathVariable Long id) {
        log.debug("REST request to get AvailableAnswer : {}", id);
        Optional<AvailableAnswerDTO> availableAnswerDTO = availableAnswerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(availableAnswerDTO);
    }
}
