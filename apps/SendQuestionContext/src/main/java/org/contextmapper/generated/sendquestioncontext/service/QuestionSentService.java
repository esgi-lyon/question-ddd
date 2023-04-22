package org.contextmapper.generated.sendquestioncontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.sendquestioncontext.domain.QuestionSent;
import org.contextmapper.generated.sendquestioncontext.repository.QuestionSentRepository;
import org.contextmapper.generated.sendquestioncontext.service.dto.QuestionSentDTO;
import org.contextmapper.generated.sendquestioncontext.service.mapper.QuestionSentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link QuestionSent}.
 */
@Service
@Transactional
public class QuestionSentService {

    private final Logger log = LoggerFactory.getLogger(QuestionSentService.class);

    private final QuestionSentRepository questionSentRepository;

    private final QuestionSentMapper questionSentMapper;

    public QuestionSentService(QuestionSentRepository questionSentRepository, QuestionSentMapper questionSentMapper) {
        this.questionSentRepository = questionSentRepository;
        this.questionSentMapper = questionSentMapper;
    }

    /**
     * Save a questionSent.
     *
     * @param questionSentDTO the entity to save.
     * @return the persisted entity.
     */
    public QuestionSentDTO save(QuestionSentDTO questionSentDTO) {
        log.debug("Request to save QuestionSent : {}", questionSentDTO);
        QuestionSent questionSent = questionSentMapper.toEntity(questionSentDTO);
        questionSent = questionSentRepository.save(questionSent);
        return questionSentMapper.toDto(questionSent);
    }

    /**
     * Update a questionSent.
     *
     * @param questionSentDTO the entity to save.
     * @return the persisted entity.
     */
    public QuestionSentDTO update(QuestionSentDTO questionSentDTO) {
        log.debug("Request to update QuestionSent : {}", questionSentDTO);
        QuestionSent questionSent = questionSentMapper.toEntity(questionSentDTO);
        questionSent = questionSentRepository.save(questionSent);
        return questionSentMapper.toDto(questionSent);
    }

    /**
     * Partially update a questionSent.
     *
     * @param questionSentDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<QuestionSentDTO> partialUpdate(QuestionSentDTO questionSentDTO) {
        log.debug("Request to partially update QuestionSent : {}", questionSentDTO);

        return questionSentRepository
            .findById(questionSentDTO.getId())
            .map(existingQuestionSent -> {
                questionSentMapper.partialUpdate(existingQuestionSent, questionSentDTO);

                return existingQuestionSent;
            })
            .map(questionSentRepository::save)
            .map(questionSentMapper::toDto);
    }

    /**
     * Get all the questionSents.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<QuestionSentDTO> findAll() {
        log.debug("Request to get all QuestionSents");
        return questionSentRepository.findAll().stream().map(questionSentMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one questionSent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<QuestionSentDTO> findOne(Long id) {
        log.debug("Request to get QuestionSent : {}", id);
        return questionSentRepository.findById(id).map(questionSentMapper::toDto);
    }

    /**
     * Delete the questionSent by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete QuestionSent : {}", id);
        questionSentRepository.deleteById(id);
    }
}
