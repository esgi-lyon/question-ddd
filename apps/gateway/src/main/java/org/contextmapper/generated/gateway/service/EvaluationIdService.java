package org.contextmapper.generated.gateway.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.contextmapper.generated.gateway.domain.EvaluationId;
import org.contextmapper.generated.gateway.repository.EvaluationIdRepository;
import org.contextmapper.generated.gateway.service.dto.EvaluationIdDTO;
import org.contextmapper.generated.gateway.service.mapper.EvaluationIdMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link EvaluationId}.
 */
@Service
@Transactional
public class EvaluationIdService {

    private final Logger log = LoggerFactory.getLogger(EvaluationIdService.class);

    private final EvaluationIdRepository evaluationIdRepository;

    private final EvaluationIdMapper evaluationIdMapper;

    public EvaluationIdService(EvaluationIdRepository evaluationIdRepository, EvaluationIdMapper evaluationIdMapper) {
        this.evaluationIdRepository = evaluationIdRepository;
        this.evaluationIdMapper = evaluationIdMapper;
    }

    /**
     * Save a evaluationId.
     *
     * @param evaluationIdDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<EvaluationIdDTO> save(EvaluationIdDTO evaluationIdDTO) {
        log.debug("Request to save EvaluationId : {}", evaluationIdDTO);
        return evaluationIdRepository.save(evaluationIdMapper.toEntity(evaluationIdDTO)).map(evaluationIdMapper::toDto);
    }

    /**
     * Update a evaluationId.
     *
     * @param evaluationIdDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<EvaluationIdDTO> update(EvaluationIdDTO evaluationIdDTO) {
        log.debug("Request to update EvaluationId : {}", evaluationIdDTO);
        return evaluationIdRepository.save(evaluationIdMapper.toEntity(evaluationIdDTO)).map(evaluationIdMapper::toDto);
    }

    /**
     * Partially update a evaluationId.
     *
     * @param evaluationIdDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<EvaluationIdDTO> partialUpdate(EvaluationIdDTO evaluationIdDTO) {
        log.debug("Request to partially update EvaluationId : {}", evaluationIdDTO);

        return evaluationIdRepository
            .findById(evaluationIdDTO.getId())
            .map(existingEvaluationId -> {
                evaluationIdMapper.partialUpdate(existingEvaluationId, evaluationIdDTO);

                return existingEvaluationId;
            })
            .flatMap(evaluationIdRepository::save)
            .map(evaluationIdMapper::toDto);
    }

    /**
     * Get all the evaluationIds.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<EvaluationIdDTO> findAll() {
        log.debug("Request to get all EvaluationIds");
        return evaluationIdRepository.findAll().map(evaluationIdMapper::toDto);
    }

    /**
     * Returns the number of evaluationIds available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return evaluationIdRepository.count();
    }

    /**
     * Get one evaluationId by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<EvaluationIdDTO> findOne(Long id) {
        log.debug("Request to get EvaluationId : {}", id);
        return evaluationIdRepository.findById(id).map(evaluationIdMapper::toDto);
    }

    /**
     * Delete the evaluationId by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete EvaluationId : {}", id);
        return evaluationIdRepository.deleteById(id);
    }
}
