package org.contextmapper.generated.sendquestioncontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.sendquestioncontext.domain.QuestionSentTagId;
import org.contextmapper.generated.sendquestioncontext.repository.QuestionSentTagIdRepository;
import org.contextmapper.generated.sendquestioncontext.service.dto.QuestionSentTagIdDTO;
import org.contextmapper.generated.sendquestioncontext.service.mapper.QuestionSentTagIdMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link QuestionSentTagId}.
 */
@Service
@Transactional
public class QuestionSentTagIdService {

    private final Logger log = LoggerFactory.getLogger(QuestionSentTagIdService.class);

    private final QuestionSentTagIdRepository questionSentTagIdRepository;

    private final QuestionSentTagIdMapper questionSentTagIdMapper;

    public QuestionSentTagIdService(
        QuestionSentTagIdRepository questionSentTagIdRepository,
        QuestionSentTagIdMapper questionSentTagIdMapper
    ) {
        this.questionSentTagIdRepository = questionSentTagIdRepository;
        this.questionSentTagIdMapper = questionSentTagIdMapper;
    }

    /**
     * Save a questionSentTagId.
     *
     * @param questionSentTagIdDTO the entity to save.
     * @return the persisted entity.
     */
    public QuestionSentTagIdDTO save(QuestionSentTagIdDTO questionSentTagIdDTO) {
        log.debug("Request to save QuestionSentTagId : {}", questionSentTagIdDTO);
        QuestionSentTagId questionSentTagId = questionSentTagIdMapper.toEntity(questionSentTagIdDTO);
        questionSentTagId = questionSentTagIdRepository.save(questionSentTagId);
        return questionSentTagIdMapper.toDto(questionSentTagId);
    }

    /**
     * Update a questionSentTagId.
     *
     * @param questionSentTagIdDTO the entity to save.
     * @return the persisted entity.
     */
    public QuestionSentTagIdDTO update(QuestionSentTagIdDTO questionSentTagIdDTO) {
        log.debug("Request to update QuestionSentTagId : {}", questionSentTagIdDTO);
        QuestionSentTagId questionSentTagId = questionSentTagIdMapper.toEntity(questionSentTagIdDTO);
        questionSentTagId = questionSentTagIdRepository.save(questionSentTagId);
        return questionSentTagIdMapper.toDto(questionSentTagId);
    }

    /**
     * Partially update a questionSentTagId.
     *
     * @param questionSentTagIdDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<QuestionSentTagIdDTO> partialUpdate(QuestionSentTagIdDTO questionSentTagIdDTO) {
        log.debug("Request to partially update QuestionSentTagId : {}", questionSentTagIdDTO);

        return questionSentTagIdRepository
            .findById(questionSentTagIdDTO.getId())
            .map(existingQuestionSentTagId -> {
                questionSentTagIdMapper.partialUpdate(existingQuestionSentTagId, questionSentTagIdDTO);

                return existingQuestionSentTagId;
            })
            .map(questionSentTagIdRepository::save)
            .map(questionSentTagIdMapper::toDto);
    }

    /**
     * Get all the questionSentTagIds.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<QuestionSentTagIdDTO> findAll() {
        log.debug("Request to get all QuestionSentTagIds");
        return questionSentTagIdRepository
            .findAll()
            .stream()
            .map(questionSentTagIdMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one questionSentTagId by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<QuestionSentTagIdDTO> findOne(Long id) {
        log.debug("Request to get QuestionSentTagId : {}", id);
        return questionSentTagIdRepository.findById(id).map(questionSentTagIdMapper::toDto);
    }

    /**
     * Delete the questionSentTagId by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete QuestionSentTagId : {}", id);
        questionSentTagIdRepository.deleteById(id);
    }
}
