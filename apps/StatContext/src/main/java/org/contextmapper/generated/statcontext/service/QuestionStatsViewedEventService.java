package org.contextmapper.generated.statcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.statcontext.domain.QuestionStatsViewedEvent;
import org.contextmapper.generated.statcontext.repository.QuestionStatsViewedEventRepository;
import org.contextmapper.generated.statcontext.service.dto.QuestionStatsViewedEventDTO;
import org.contextmapper.generated.statcontext.service.mapper.QuestionStatsViewedEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public QuestionStatsViewedEventDTO save(QuestionStatsViewedEventDTO questionStatsViewedEventDTO) {
        log.debug("Request to save QuestionStatsViewedEvent : {}", questionStatsViewedEventDTO);
        QuestionStatsViewedEvent questionStatsViewedEvent = questionStatsViewedEventMapper.toEntity(questionStatsViewedEventDTO);
        questionStatsViewedEvent = questionStatsViewedEventRepository.save(questionStatsViewedEvent);
        return questionStatsViewedEventMapper.toDto(questionStatsViewedEvent);
    }

    /**
     * Update a questionStatsViewedEvent.
     *
     * @param questionStatsViewedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public QuestionStatsViewedEventDTO update(QuestionStatsViewedEventDTO questionStatsViewedEventDTO) {
        log.debug("Request to update QuestionStatsViewedEvent : {}", questionStatsViewedEventDTO);
        QuestionStatsViewedEvent questionStatsViewedEvent = questionStatsViewedEventMapper.toEntity(questionStatsViewedEventDTO);
        questionStatsViewedEvent = questionStatsViewedEventRepository.save(questionStatsViewedEvent);
        return questionStatsViewedEventMapper.toDto(questionStatsViewedEvent);
    }

    /**
     * Partially update a questionStatsViewedEvent.
     *
     * @param questionStatsViewedEventDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<QuestionStatsViewedEventDTO> partialUpdate(QuestionStatsViewedEventDTO questionStatsViewedEventDTO) {
        log.debug("Request to partially update QuestionStatsViewedEvent : {}", questionStatsViewedEventDTO);

        return questionStatsViewedEventRepository
            .findById(questionStatsViewedEventDTO.getId())
            .map(existingQuestionStatsViewedEvent -> {
                questionStatsViewedEventMapper.partialUpdate(existingQuestionStatsViewedEvent, questionStatsViewedEventDTO);

                return existingQuestionStatsViewedEvent;
            })
            .map(questionStatsViewedEventRepository::save)
            .map(questionStatsViewedEventMapper::toDto);
    }

    /**
     * Get all the questionStatsViewedEvents.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<QuestionStatsViewedEventDTO> findAll() {
        log.debug("Request to get all QuestionStatsViewedEvents");
        return questionStatsViewedEventRepository
            .findAll()
            .stream()
            .map(questionStatsViewedEventMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one questionStatsViewedEvent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<QuestionStatsViewedEventDTO> findOne(Long id) {
        log.debug("Request to get QuestionStatsViewedEvent : {}", id);
        return questionStatsViewedEventRepository.findById(id).map(questionStatsViewedEventMapper::toDto);
    }

    /**
     * Delete the questionStatsViewedEvent by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete QuestionStatsViewedEvent : {}", id);
        questionStatsViewedEventRepository.deleteById(id);
    }
}
