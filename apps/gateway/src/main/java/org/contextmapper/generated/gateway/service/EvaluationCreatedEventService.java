package org.contextmapper.generated.gateway.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.contextmapper.generated.gateway.domain.EvaluationCreatedEvent;
import org.contextmapper.generated.gateway.repository.EvaluationCreatedEventRepository;
import org.contextmapper.generated.gateway.service.dto.EvaluationCreatedEventDTO;
import org.contextmapper.generated.gateway.service.mapper.EvaluationCreatedEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link EvaluationCreatedEvent}.
 */
@Service
@Transactional
public class EvaluationCreatedEventService {

    private final Logger log = LoggerFactory.getLogger(EvaluationCreatedEventService.class);

    private final EvaluationCreatedEventRepository evaluationCreatedEventRepository;

    private final EvaluationCreatedEventMapper evaluationCreatedEventMapper;

    public EvaluationCreatedEventService(
        EvaluationCreatedEventRepository evaluationCreatedEventRepository,
        EvaluationCreatedEventMapper evaluationCreatedEventMapper
    ) {
        this.evaluationCreatedEventRepository = evaluationCreatedEventRepository;
        this.evaluationCreatedEventMapper = evaluationCreatedEventMapper;
    }

    /**
     * Save a evaluationCreatedEvent.
     *
     * @param evaluationCreatedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<EvaluationCreatedEventDTO> save(EvaluationCreatedEventDTO evaluationCreatedEventDTO) {
        log.debug("Request to save EvaluationCreatedEvent : {}", evaluationCreatedEventDTO);
        return evaluationCreatedEventRepository
            .save(evaluationCreatedEventMapper.toEntity(evaluationCreatedEventDTO))
            .map(evaluationCreatedEventMapper::toDto);
    }

    /**
     * Update a evaluationCreatedEvent.
     *
     * @param evaluationCreatedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<EvaluationCreatedEventDTO> update(EvaluationCreatedEventDTO evaluationCreatedEventDTO) {
        log.debug("Request to update EvaluationCreatedEvent : {}", evaluationCreatedEventDTO);
        return evaluationCreatedEventRepository
            .save(evaluationCreatedEventMapper.toEntity(evaluationCreatedEventDTO))
            .map(evaluationCreatedEventMapper::toDto);
    }

    /**
     * Partially update a evaluationCreatedEvent.
     *
     * @param evaluationCreatedEventDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<EvaluationCreatedEventDTO> partialUpdate(EvaluationCreatedEventDTO evaluationCreatedEventDTO) {
        log.debug("Request to partially update EvaluationCreatedEvent : {}", evaluationCreatedEventDTO);

        return evaluationCreatedEventRepository
            .findById(evaluationCreatedEventDTO.getId())
            .map(existingEvaluationCreatedEvent -> {
                evaluationCreatedEventMapper.partialUpdate(existingEvaluationCreatedEvent, evaluationCreatedEventDTO);

                return existingEvaluationCreatedEvent;
            })
            .flatMap(evaluationCreatedEventRepository::save)
            .map(evaluationCreatedEventMapper::toDto);
    }

    /**
     * Get all the evaluationCreatedEvents.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<EvaluationCreatedEventDTO> findAll() {
        log.debug("Request to get all EvaluationCreatedEvents");
        return evaluationCreatedEventRepository.findAll().map(evaluationCreatedEventMapper::toDto);
    }

    /**
     * Returns the number of evaluationCreatedEvents available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return evaluationCreatedEventRepository.count();
    }

    /**
     * Get one evaluationCreatedEvent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<EvaluationCreatedEventDTO> findOne(Long id) {
        log.debug("Request to get EvaluationCreatedEvent : {}", id);
        return evaluationCreatedEventRepository.findById(id).map(evaluationCreatedEventMapper::toDto);
    }

    /**
     * Delete the evaluationCreatedEvent by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete EvaluationCreatedEvent : {}", id);
        return evaluationCreatedEventRepository.deleteById(id);
    }
}
