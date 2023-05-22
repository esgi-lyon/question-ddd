package org.contextmapper.generated.gateway.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.contextmapper.generated.gateway.domain.AnswerSubmittedEvent;
import org.contextmapper.generated.gateway.repository.AnswerSubmittedEventRepository;
import org.contextmapper.generated.gateway.service.dto.AnswerSubmittedEventDTO;
import org.contextmapper.generated.gateway.service.mapper.AnswerSubmittedEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link AnswerSubmittedEvent}.
 */
@Service
@Transactional
public class AnswerSubmittedEventService {

    private final Logger log = LoggerFactory.getLogger(AnswerSubmittedEventService.class);

    private final AnswerSubmittedEventRepository answerSubmittedEventRepository;

    private final AnswerSubmittedEventMapper answerSubmittedEventMapper;

    public AnswerSubmittedEventService(
        AnswerSubmittedEventRepository answerSubmittedEventRepository,
        AnswerSubmittedEventMapper answerSubmittedEventMapper
    ) {
        this.answerSubmittedEventRepository = answerSubmittedEventRepository;
        this.answerSubmittedEventMapper = answerSubmittedEventMapper;
    }

    /**
     * Save a answerSubmittedEvent.
     *
     * @param answerSubmittedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<AnswerSubmittedEventDTO> save(AnswerSubmittedEventDTO answerSubmittedEventDTO) {
        log.debug("Request to save AnswerSubmittedEvent : {}", answerSubmittedEventDTO);
        return answerSubmittedEventRepository
            .save(answerSubmittedEventMapper.toEntity(answerSubmittedEventDTO))
            .map(answerSubmittedEventMapper::toDto);
    }

    /**
     * Update a answerSubmittedEvent.
     *
     * @param answerSubmittedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<AnswerSubmittedEventDTO> update(AnswerSubmittedEventDTO answerSubmittedEventDTO) {
        log.debug("Request to update AnswerSubmittedEvent : {}", answerSubmittedEventDTO);
        return answerSubmittedEventRepository
            .save(answerSubmittedEventMapper.toEntity(answerSubmittedEventDTO))
            .map(answerSubmittedEventMapper::toDto);
    }

    /**
     * Partially update a answerSubmittedEvent.
     *
     * @param answerSubmittedEventDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<AnswerSubmittedEventDTO> partialUpdate(AnswerSubmittedEventDTO answerSubmittedEventDTO) {
        log.debug("Request to partially update AnswerSubmittedEvent : {}", answerSubmittedEventDTO);

        return answerSubmittedEventRepository
            .findById(answerSubmittedEventDTO.getId())
            .map(existingAnswerSubmittedEvent -> {
                answerSubmittedEventMapper.partialUpdate(existingAnswerSubmittedEvent, answerSubmittedEventDTO);

                return existingAnswerSubmittedEvent;
            })
            .flatMap(answerSubmittedEventRepository::save)
            .map(answerSubmittedEventMapper::toDto);
    }

    /**
     * Get all the answerSubmittedEvents.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<AnswerSubmittedEventDTO> findAll() {
        log.debug("Request to get all AnswerSubmittedEvents");
        return answerSubmittedEventRepository.findAll().map(answerSubmittedEventMapper::toDto);
    }

    /**
     * Returns the number of answerSubmittedEvents available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return answerSubmittedEventRepository.count();
    }

    /**
     * Get one answerSubmittedEvent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<AnswerSubmittedEventDTO> findOne(Long id) {
        log.debug("Request to get AnswerSubmittedEvent : {}", id);
        return answerSubmittedEventRepository.findById(id).map(answerSubmittedEventMapper::toDto);
    }

    /**
     * Delete the answerSubmittedEvent by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete AnswerSubmittedEvent : {}", id);
        return answerSubmittedEventRepository.deleteById(id);
    }
}
