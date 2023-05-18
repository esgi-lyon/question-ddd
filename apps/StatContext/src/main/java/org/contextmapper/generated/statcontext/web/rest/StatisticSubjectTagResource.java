package org.contextmapper.generated.statcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.statcontext.repository.StatisticSubjectTagRepository;
import org.contextmapper.generated.statcontext.service.StatisticSubjectTagService;
import org.contextmapper.generated.statcontext.service.dto.StatisticSubjectTagDTO;
import org.contextmapper.generated.statcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.statcontext.domain.StatisticSubjectTag}.
 */
@RestController
@RequestMapping("/api")
public class StatisticSubjectTagResource {

    private final Logger log = LoggerFactory.getLogger(StatisticSubjectTagResource.class);

    private final StatisticSubjectTagService statisticSubjectTagService;

    private final StatisticSubjectTagRepository statisticSubjectTagRepository;

    public StatisticSubjectTagResource(
        StatisticSubjectTagService statisticSubjectTagService,
        StatisticSubjectTagRepository statisticSubjectTagRepository
    ) {
        this.statisticSubjectTagService = statisticSubjectTagService;
        this.statisticSubjectTagRepository = statisticSubjectTagRepository;
    }

    /**
     * {@code GET  /statistic-subject-tags} : get all the statisticSubjectTags.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of statisticSubjectTags in body.
     */
    @GetMapping("/statistic-subject-tags")
    public List<StatisticSubjectTagDTO> getAllStatisticSubjectTags() {
        log.debug("REST request to get all StatisticSubjectTags");
        return statisticSubjectTagService.findAll();
    }

    /**
     * {@code GET  /statistic-subject-tags/:id} : get the "id" statisticSubjectTag.
     *
     * @param id the id of the statisticSubjectTagDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the statisticSubjectTagDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/statistic-subject-tags/{id}")
    public ResponseEntity<StatisticSubjectTagDTO> getStatisticSubjectTag(@PathVariable Long id) {
        log.debug("REST request to get StatisticSubjectTag : {}", id);
        Optional<StatisticSubjectTagDTO> statisticSubjectTagDTO = statisticSubjectTagService.findOne(id);
        return ResponseUtil.wrapOrNotFound(statisticSubjectTagDTO);
    }
}
