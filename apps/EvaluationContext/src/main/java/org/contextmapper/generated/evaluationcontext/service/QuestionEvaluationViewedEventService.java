package org.contextmapper.generated.evaluationcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.evaluationcontext.domain.QuestionEvaluationViewedEvent;
import org.contextmapper.generated.evaluationcontext.repository.QuestionEvaluationViewedEventRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.QuestionEvaluationViewedEventDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.QuestionEvaluationViewedEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link QuestionEvaluationViewedEvent}.
 */
@Service
@Transactional
public class QuestionEvaluationViewedEventService {

    private final Logger log = LoggerFactory.getLogger(QuestionEvaluationViewedEventService.class);

    private final QuestionEvaluationViewedEventRepository questionEvaluationViewedEventRepository;

    private final QuestionEvaluationViewedEventMapper questionEvaluationViewedEventMapper;

    public QuestionEvaluationViewedEventService(
        QuestionEvaluationViewedEventRepository questionEvaluationViewedEventRepository,
        QuestionEvaluationViewedEventMapper questionEvaluationViewedEventMapper
    ) {
        this.questionEvaluationViewedEventRepository = questionEvaluationViewedEventRepository;
        this.questionEvaluationViewedEventMapper = questionEvaluationViewedEventMapper;
    }

    /**
     * Save a questionEvaluationViewedEvent.
     *
     * @param questionEvaluationViewedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public QuestionEvaluationViewedEventDTO save(QuestionEvaluationViewedEventDTO questionEvaluationViewedEventDTO) {
        log.debug("Request to save QuestionEvaluationViewedEvent : {}", questionEvaluationViewedEventDTO);
        QuestionEvaluationViewedEvent questionEvaluationViewedEvent = questionEvaluationViewedEventMapper.toEntity(
            questionEvaluationViewedEventDTO
        );
        questionEvaluationViewedEvent = questionEvaluationViewedEventRepository.save(questionEvaluationViewedEvent);
        return questionEvaluationViewedEventMapper.toDto(questionEvaluationViewedEvent);
    }

    /**
     * Update a questionEvaluationViewedEvent.
     *
     * @param questionEvaluationViewedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public QuestionEvaluationViewedEventDTO update(QuestionEvaluationViewedEventDTO questionEvaluationViewedEventDTO) {
        log.debug("Request to update QuestionEvaluationViewedEvent : {}", questionEvaluationViewedEventDTO);
        QuestionEvaluationViewedEvent questionEvaluationViewedEvent = questionEvaluationViewedEventMapper.toEntity(
            questionEvaluationViewedEventDTO
        );
        questionEvaluationViewedEvent = questionEvaluationViewedEventRepository.save(questionEvaluationViewedEvent);
        return questionEvaluationViewedEventMapper.toDto(questionEvaluationViewedEvent);
    }

    /**
     * Partially update a questionEvaluationViewedEvent.
     *
     * @param questionEvaluationViewedEventDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<QuestionEvaluationViewedEventDTO> partialUpdate(QuestionEvaluationViewedEventDTO questionEvaluationViewedEventDTO) {
        log.debug("Request to partially update QuestionEvaluationViewedEvent : {}", questionEvaluationViewedEventDTO);

        return questionEvaluationViewedEventRepository
            .findById(questionEvaluationViewedEventDTO.getId())
            .map(existingQuestionEvaluationViewedEvent -> {
                questionEvaluationViewedEventMapper.partialUpdate(existingQuestionEvaluationViewedEvent, questionEvaluationViewedEventDTO);

                return existingQuestionEvaluationViewedEvent;
            })
            .map(questionEvaluationViewedEventRepository::save)
            .map(questionEvaluationViewedEventMapper::toDto);
    }

    /**
     * Get all the questionEvaluationViewedEvents.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<QuestionEvaluationViewedEventDTO> findAll() {
        log.debug("Request to get all QuestionEvaluationViewedEvents");
        return questionEvaluationViewedEventRepository
            .findAll()
            .stream()
            .map(questionEvaluationViewedEventMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one questionEvaluationViewedEvent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<QuestionEvaluationViewedEventDTO> findOne(Long id) {
        log.debug("Request to get QuestionEvaluationViewedEvent : {}", id);
        return questionEvaluationViewedEventRepository.findById(id).map(questionEvaluationViewedEventMapper::toDto);
    }

    /**
     * Delete the questionEvaluationViewedEvent by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete QuestionEvaluationViewedEvent : {}", id);
        questionEvaluationViewedEventRepository.deleteById(id);
    }
}
