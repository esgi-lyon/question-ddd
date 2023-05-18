package org.contextmapper.generated.answercontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.answercontext.domain.QuestionId;
import org.contextmapper.generated.answercontext.repository.QuestionIdRepository;
import org.contextmapper.generated.answercontext.service.dto.QuestionIdDTO;
import org.contextmapper.generated.answercontext.service.mapper.QuestionIdMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link QuestionId}.
 */
@Service
@Transactional
public class QuestionIdService {

    private final Logger log = LoggerFactory.getLogger(QuestionIdService.class);

    private final QuestionIdRepository questionIdRepository;

    private final QuestionIdMapper questionIdMapper;

    public QuestionIdService(QuestionIdRepository questionIdRepository, QuestionIdMapper questionIdMapper) {
        this.questionIdRepository = questionIdRepository;
        this.questionIdMapper = questionIdMapper;
    }

    /**
     * Save a questionId.
     *
     * @param questionIdDTO the entity to save.
     * @return the persisted entity.
     */
    public QuestionIdDTO save(QuestionIdDTO questionIdDTO) {
        log.debug("Request to save QuestionId : {}", questionIdDTO);
        QuestionId questionId = questionIdMapper.toEntity(questionIdDTO);
        questionId = questionIdRepository.save(questionId);
        return questionIdMapper.toDto(questionId);
    }

    /**
     * Update a questionId.
     *
     * @param questionIdDTO the entity to save.
     * @return the persisted entity.
     */
    public QuestionIdDTO update(QuestionIdDTO questionIdDTO) {
        log.debug("Request to update QuestionId : {}", questionIdDTO);
        QuestionId questionId = questionIdMapper.toEntity(questionIdDTO);
        questionId = questionIdRepository.save(questionId);
        return questionIdMapper.toDto(questionId);
    }

    /**
     * Partially update a questionId.
     *
     * @param questionIdDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<QuestionIdDTO> partialUpdate(QuestionIdDTO questionIdDTO) {
        log.debug("Request to partially update QuestionId : {}", questionIdDTO);

        return questionIdRepository
            .findById(questionIdDTO.getId())
            .map(existingQuestionId -> {
                questionIdMapper.partialUpdate(existingQuestionId, questionIdDTO);

                return existingQuestionId;
            })
            .map(questionIdRepository::save)
            .map(questionIdMapper::toDto);
    }

    /**
     * Get all the questionIds.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<QuestionIdDTO> findAll() {
        log.debug("Request to get all QuestionIds");
        return questionIdRepository.findAll().stream().map(questionIdMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one questionId by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<QuestionIdDTO> findOne(Long id) {
        log.debug("Request to get QuestionId : {}", id);
        return questionIdRepository.findById(id).map(questionIdMapper::toDto);
    }

    /**
     * Delete the questionId by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete QuestionId : {}", id);
        questionIdRepository.deleteById(id);
    }
}
