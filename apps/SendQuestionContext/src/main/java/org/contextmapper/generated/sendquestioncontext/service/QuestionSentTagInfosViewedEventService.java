package org.contextmapper.generated.sendquestioncontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.sendquestioncontext.domain.QuestionSentTagInfosViewedEvent;
import org.contextmapper.generated.sendquestioncontext.repository.QuestionSentTagInfosViewedEventRepository;
import org.contextmapper.generated.sendquestioncontext.service.dto.QuestionSentTagInfosViewedEventDTO;
import org.contextmapper.generated.sendquestioncontext.service.mapper.QuestionSentTagInfosViewedEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link QuestionSentTagInfosViewedEvent}.
 */
@Service
@Transactional
public class QuestionSentTagInfosViewedEventService {

    private final Logger log = LoggerFactory.getLogger(QuestionSentTagInfosViewedEventService.class);

    private final QuestionSentTagInfosViewedEventRepository questionSentTagInfosViewedEventRepository;

    private final QuestionSentTagInfosViewedEventMapper questionSentTagInfosViewedEventMapper;

    public QuestionSentTagInfosViewedEventService(
        QuestionSentTagInfosViewedEventRepository questionSentTagInfosViewedEventRepository,
        QuestionSentTagInfosViewedEventMapper questionSentTagInfosViewedEventMapper
    ) {
        this.questionSentTagInfosViewedEventRepository = questionSentTagInfosViewedEventRepository;
        this.questionSentTagInfosViewedEventMapper = questionSentTagInfosViewedEventMapper;
    }

    /**
     * Save a questionSentTagInfosViewedEvent.
     *
     * @param questionSentTagInfosViewedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public QuestionSentTagInfosViewedEventDTO save(QuestionSentTagInfosViewedEventDTO questionSentTagInfosViewedEventDTO) {
        log.debug("Request to save QuestionSentTagInfosViewedEvent : {}", questionSentTagInfosViewedEventDTO);
        QuestionSentTagInfosViewedEvent questionSentTagInfosViewedEvent = questionSentTagInfosViewedEventMapper.toEntity(
            questionSentTagInfosViewedEventDTO
        );
        questionSentTagInfosViewedEvent = questionSentTagInfosViewedEventRepository.save(questionSentTagInfosViewedEvent);
        return questionSentTagInfosViewedEventMapper.toDto(questionSentTagInfosViewedEvent);
    }

    /**
     * Update a questionSentTagInfosViewedEvent.
     *
     * @param questionSentTagInfosViewedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public QuestionSentTagInfosViewedEventDTO update(QuestionSentTagInfosViewedEventDTO questionSentTagInfosViewedEventDTO) {
        log.debug("Request to update QuestionSentTagInfosViewedEvent : {}", questionSentTagInfosViewedEventDTO);
        QuestionSentTagInfosViewedEvent questionSentTagInfosViewedEvent = questionSentTagInfosViewedEventMapper.toEntity(
            questionSentTagInfosViewedEventDTO
        );
        // no save call needed as we have no fields that can be updated
        return questionSentTagInfosViewedEventMapper.toDto(questionSentTagInfosViewedEvent);
    }

    /**
     * Partially update a questionSentTagInfosViewedEvent.
     *
     * @param questionSentTagInfosViewedEventDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<QuestionSentTagInfosViewedEventDTO> partialUpdate(
        QuestionSentTagInfosViewedEventDTO questionSentTagInfosViewedEventDTO
    ) {
        log.debug("Request to partially update QuestionSentTagInfosViewedEvent : {}", questionSentTagInfosViewedEventDTO);

        return questionSentTagInfosViewedEventRepository
            .findById(questionSentTagInfosViewedEventDTO.getId())
            .map(existingQuestionSentTagInfosViewedEvent -> {
                questionSentTagInfosViewedEventMapper.partialUpdate(
                    existingQuestionSentTagInfosViewedEvent,
                    questionSentTagInfosViewedEventDTO
                );

                return existingQuestionSentTagInfosViewedEvent;
            })
            // .map(questionSentTagInfosViewedEventRepository::save)
            .map(questionSentTagInfosViewedEventMapper::toDto);
    }

    /**
     * Get all the questionSentTagInfosViewedEvents.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<QuestionSentTagInfosViewedEventDTO> findAll() {
        log.debug("Request to get all QuestionSentTagInfosViewedEvents");
        return questionSentTagInfosViewedEventRepository
            .findAll()
            .stream()
            .map(questionSentTagInfosViewedEventMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one questionSentTagInfosViewedEvent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<QuestionSentTagInfosViewedEventDTO> findOne(Long id) {
        log.debug("Request to get QuestionSentTagInfosViewedEvent : {}", id);
        return questionSentTagInfosViewedEventRepository.findById(id).map(questionSentTagInfosViewedEventMapper::toDto);
    }

    /**
     * Delete the questionSentTagInfosViewedEvent by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete QuestionSentTagInfosViewedEvent : {}", id);
        questionSentTagInfosViewedEventRepository.deleteById(id);
    }
}
