package org.contextmapper.generated.gateway.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.contextmapper.generated.gateway.domain.QuestionStatsViewedEvent;
import org.contextmapper.generated.gateway.repository.QuestionStatsViewedEventRepository;
import org.contextmapper.generated.gateway.service.dto.QuestionStatsViewedEventDTO;
import org.contextmapper.generated.gateway.service.mapper.QuestionStatsViewedEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link QuestionStatsViewedEvent}.
 */
@Service
@Transactional
public class QuestionStatsViewedEventService {

    private final Logger log = LoggerFactory.getLogger(QuestionStatsViewedEventService.class);

    private final QuestionStatsViewedEventRepository questionStatsViewedEventRepository;

    private final QuestionStatsViewedEventMapper questionStatsViewedEventMapper;

    public QuestionStatsViewedEventService(
        QuestionStatsViewedEventRepository questionStatsViewedEventRepository,
        QuestionStatsViewedEventMapper questionStatsViewedEventMapper
    ) {
        this.questionStatsViewedEventRepository = questionStatsViewedEventRepository;
        this.questionStatsViewedEventMapper = questionStatsViewedEventMapper;
    }

    /**
     * Save a questionStatsViewedEvent.
     *
     * @param questionStatsViewedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<QuestionStatsViewedEventDTO> save(QuestionStatsViewedEventDTO questionStatsViewedEventDTO) {
        log.debug("Request to save QuestionStatsViewedEvent : {}", questionStatsViewedEventDTO);
        return questionStatsViewedEventRepository
            .save(questionStatsViewedEventMapper.toEntity(questionStatsViewedEventDTO))
            .map(questionStatsViewedEventMapper::toDto);
    }

    /**
     * Update a questionStatsViewedEvent.
     *
     * @param questionStatsViewedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<QuestionStatsViewedEventDTO> update(QuestionStatsViewedEventDTO questionStatsViewedEventDTO) {
        log.debug("Request to update QuestionStatsViewedEvent : {}", questionStatsViewedEventDTO);
        return questionStatsViewedEventRepository
            .save(questionStatsViewedEventMapper.toEntity(questionStatsViewedEventDTO))
            .map(questionStatsViewedEventMapper::toDto);
    }

    /**
     * Partially update a questionStatsViewedEvent.
     *
     * @param questionStatsViewedEventDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<QuestionStatsViewedEventDTO> partialUpdate(QuestionStatsViewedEventDTO questionStatsViewedEventDTO) {
        log.debug("Request to partially update QuestionStatsViewedEvent : {}", questionStatsViewedEventDTO);

        return questionStatsViewedEventRepository
            .findById(questionStatsViewedEventDTO.getId())
            .map(existingQuestionStatsViewedEvent -> {
                questionStatsViewedEventMapper.partialUpdate(existingQuestionStatsViewedEvent, questionStatsViewedEventDTO);

                return existingQuestionStatsViewedEvent;
            })
            .flatMap(questionStatsViewedEventRepository::save)
            .map(questionStatsViewedEventMapper::toDto);
    }

    /**
     * Get all the questionStatsViewedEvents.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<QuestionStatsViewedEventDTO> findAll() {
        log.debug("Request to get all QuestionStatsViewedEvents");
        return questionStatsViewedEventRepository.findAll().map(questionStatsViewedEventMapper::toDto);
    }

    /**
     * Returns the number of questionStatsViewedEvents available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return questionStatsViewedEventRepository.count();
    }

    /**
     * Get one questionStatsViewedEvent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<QuestionStatsViewedEventDTO> findOne(Long id) {
        log.debug("Request to get QuestionStatsViewedEvent : {}", id);
        return questionStatsViewedEventRepository.findById(id).map(questionStatsViewedEventMapper::toDto);
    }

    /**
     * Delete the questionStatsViewedEvent by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete QuestionStatsViewedEvent : {}", id);
        return questionStatsViewedEventRepository.deleteById(id);
    }
}
