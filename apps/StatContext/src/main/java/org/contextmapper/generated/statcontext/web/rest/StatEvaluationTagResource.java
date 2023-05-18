package org.contextmapper.generated.statcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.statcontext.repository.StatEvaluationTagRepository;
import org.contextmapper.generated.statcontext.service.StatEvaluationTagService;
import org.contextmapper.generated.statcontext.service.dto.StatEvaluationTagDTO;
import org.contextmapper.generated.statcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.statcontext.domain.StatEvaluationTag}.
 */
@RestController
@RequestMapping("/api")
public class StatEvaluationTagResource {

    private final Logger log = LoggerFactory.getLogger(StatEvaluationTagResource.class);

    private final StatEvaluationTagService statEvaluationTagService;

    private final StatEvaluationTagRepository statEvaluationTagRepository;

    public StatEvaluationTagResource(
        StatEvaluationTagService statEvaluationTagService,
        StatEvaluationTagRepository statEvaluationTagRepository
    ) {
        this.statEvaluationTagService = statEvaluationTagService;
        this.statEvaluationTagRepository = statEvaluationTagRepository;
    }

    /**
     * {@code GET  /stat-evaluation-tags} : get all the statEvaluationTags.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of statEvaluationTags in body.
     */
    @GetMapping("/stat-evaluation-tags")
    public List<StatEvaluationTagDTO> getAllStatEvaluationTags() {
        log.debug("REST request to get all StatEvaluationTags");
        return statEvaluationTagService.findAll();
    }

    /**
     * {@code GET  /stat-evaluation-tags/:id} : get the "id" statEvaluationTag.
     *
     * @param id the id of the statEvaluationTagDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the statEvaluationTagDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/stat-evaluation-tags/{id}")
    public ResponseEntity<StatEvaluationTagDTO> getStatEvaluationTag(@PathVariable Long id) {
        log.debug("REST request to get StatEvaluationTag : {}", id);
        Optional<StatEvaluationTagDTO> statEvaluationTagDTO = statEvaluationTagService.findOne(id);
        return ResponseUtil.wrapOrNotFound(statEvaluationTagDTO);
    }
}
