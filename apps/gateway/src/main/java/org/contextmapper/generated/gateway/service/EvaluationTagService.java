package org.contextmapper.generated.gateway.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.contextmapper.generated.gateway.domain.EvaluationTag;
import org.contextmapper.generated.gateway.repository.EvaluationTagRepository;
import org.contextmapper.generated.gateway.service.dto.EvaluationTagDTO;
import org.contextmapper.generated.gateway.service.mapper.EvaluationTagMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link EvaluationTag}.
 */
@Service
@Transactional
public class EvaluationTagService {

    private final Logger log = LoggerFactory.getLogger(EvaluationTagService.class);

    private final EvaluationTagRepository evaluationTagRepository;

    private final EvaluationTagMapper evaluationTagMapper;

    public EvaluationTagService(EvaluationTagRepository evaluationTagRepository, EvaluationTagMapper evaluationTagMapper) {
        this.evaluationTagRepository = evaluationTagRepository;
        this.evaluationTagMapper = evaluationTagMapper;
    }

    /**
     * Save a evaluationTag.
     *
     * @param evaluationTagDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<EvaluationTagDTO> save(EvaluationTagDTO evaluationTagDTO) {
        log.debug("Request to save EvaluationTag : {}", evaluationTagDTO);
        return evaluationTagRepository.save(evaluationTagMapper.toEntity(evaluationTagDTO)).map(evaluationTagMapper::toDto);
    }

    /**
     * Update a evaluationTag.
     *
     * @param evaluationTagDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<EvaluationTagDTO> update(EvaluationTagDTO evaluationTagDTO) {
        log.debug("Request to update EvaluationTag : {}", evaluationTagDTO);
        return evaluationTagRepository.save(evaluationTagMapper.toEntity(evaluationTagDTO)).map(evaluationTagMapper::toDto);
    }

    /**
     * Partially update a evaluationTag.
     *
     * @param evaluationTagDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<EvaluationTagDTO> partialUpdate(EvaluationTagDTO evaluationTagDTO) {
        log.debug("Request to partially update EvaluationTag : {}", evaluationTagDTO);

        return evaluationTagRepository
            .findById(evaluationTagDTO.getId())
            .map(existingEvaluationTag -> {
                evaluationTagMapper.partialUpdate(existingEvaluationTag, evaluationTagDTO);

                return existingEvaluationTag;
            })
            .flatMap(evaluationTagRepository::save)
            .map(evaluationTagMapper::toDto);
    }

    /**
     * Get all the evaluationTags.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<EvaluationTagDTO> findAll() {
        log.debug("Request to get all EvaluationTags");
        return evaluationTagRepository.findAll().map(evaluationTagMapper::toDto);
    }

    /**
     * Returns the number of evaluationTags available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return evaluationTagRepository.count();
    }

    /**
     * Get one evaluationTag by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<EvaluationTagDTO> findOne(Long id) {
        log.debug("Request to get EvaluationTag : {}", id);
        return evaluationTagRepository.findById(id).map(evaluationTagMapper::toDto);
    }

    /**
     * Delete the evaluationTag by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete EvaluationTag : {}", id);
        return evaluationTagRepository.deleteById(id);
    }
}
