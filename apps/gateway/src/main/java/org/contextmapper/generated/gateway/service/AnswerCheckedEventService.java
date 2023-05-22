package org.contextmapper.generated.gateway.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.contextmapper.generated.gateway.domain.AnswerCheckedEvent;
import org.contextmapper.generated.gateway.repository.AnswerCheckedEventRepository;
import org.contextmapper.generated.gateway.service.dto.AnswerCheckedEventDTO;
import org.contextmapper.generated.gateway.service.mapper.AnswerCheckedEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link AnswerCheckedEvent}.
 */
@Service
@Transactional
public class AnswerCheckedEventService {

    private final Logger log = LoggerFactory.getLogger(AnswerCheckedEventService.class);

    private final AnswerCheckedEventRepository answerCheckedEventRepository;

    private final AnswerCheckedEventMapper answerCheckedEventMapper;

    public AnswerCheckedEventService(
        AnswerCheckedEventRepository answerCheckedEventRepository,
        AnswerCheckedEventMapper answerCheckedEventMapper
    ) {
        this.answerCheckedEventRepository = answerCheckedEventRepository;
        this.answerCheckedEventMapper = answerCheckedEventMapper;
    }

    /**
     * Save a answerCheckedEvent.
     *
     * @param answerCheckedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<AnswerCheckedEventDTO> save(AnswerCheckedEventDTO answerCheckedEventDTO) {
        log.debug("Request to save AnswerCheckedEvent : {}", answerCheckedEventDTO);
        return answerCheckedEventRepository
            .save(answerCheckedEventMapper.toEntity(answerCheckedEventDTO))
            .map(answerCheckedEventMapper::toDto);
    }

    /**
     * Update a answerCheckedEvent.
     *
     * @param answerCheckedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<AnswerCheckedEventDTO> update(AnswerCheckedEventDTO answerCheckedEventDTO) {
        log.debug("Request to update AnswerCheckedEvent : {}", answerCheckedEventDTO);
        return answerCheckedEventRepository
            .save(answerCheckedEventMapper.toEntity(answerCheckedEventDTO))
            .map(answerCheckedEventMapper::toDto);
    }

    /**
     * Partially update a answerCheckedEvent.
     *
     * @param answerCheckedEventDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<AnswerCheckedEventDTO> partialUpdate(AnswerCheckedEventDTO answerCheckedEventDTO) {
        log.debug("Request to partially update AnswerCheckedEvent : {}", answerCheckedEventDTO);

        return answerCheckedEventRepository
            .findById(answerCheckedEventDTO.getId())
            .map(existingAnswerCheckedEvent -> {
                answerCheckedEventMapper.partialUpdate(existingAnswerCheckedEvent, answerCheckedEventDTO);

                return existingAnswerCheckedEvent;
            })
            .flatMap(answerCheckedEventRepository::save)
            .map(answerCheckedEventMapper::toDto);
    }

    /**
     * Get all the answerCheckedEvents.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<AnswerCheckedEventDTO> findAll() {
        log.debug("Request to get all AnswerCheckedEvents");
        return answerCheckedEventRepository.findAll().map(answerCheckedEventMapper::toDto);
    }

    /**
     * Returns the number of answerCheckedEvents available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return answerCheckedEventRepository.count();
    }

    /**
     * Get one answerCheckedEvent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<AnswerCheckedEventDTO> findOne(Long id) {
        log.debug("Request to get AnswerCheckedEvent : {}", id);
        return answerCheckedEventRepository.findById(id).map(answerCheckedEventMapper::toDto);
    }

    /**
     * Delete the answerCheckedEvent by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete AnswerCheckedEvent : {}", id);
        return answerCheckedEventRepository.deleteById(id);
    }
}
