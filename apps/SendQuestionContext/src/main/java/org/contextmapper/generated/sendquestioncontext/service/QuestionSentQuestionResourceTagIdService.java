package org.contextmapper.generated.sendquestioncontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.sendquestioncontext.domain.QuestionSentQuestionResourceTagId;
import org.contextmapper.generated.sendquestioncontext.repository.QuestionSentQuestionResourceTagIdRepository;
import org.contextmapper.generated.sendquestioncontext.service.dto.QuestionSentQuestionResourceTagIdDTO;
import org.contextmapper.generated.sendquestioncontext.service.mapper.QuestionSentQuestionResourceTagIdMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link QuestionSentQuestionResourceTagId}.
 */
@Service
@Transactional
public class QuestionSentQuestionResourceTagIdService {

    private final Logger log = LoggerFactory.getLogger(QuestionSentQuestionResourceTagIdService.class);

    private final QuestionSentQuestionResourceTagIdRepository questionSentQuestionResourceTagIdRepository;

    private final QuestionSentQuestionResourceTagIdMapper questionSentQuestionResourceTagIdMapper;

    public QuestionSentQuestionResourceTagIdService(
        QuestionSentQuestionResourceTagIdRepository questionSentQuestionResourceTagIdRepository,
        QuestionSentQuestionResourceTagIdMapper questionSentQuestionResourceTagIdMapper
    ) {
        this.questionSentQuestionResourceTagIdRepository = questionSentQuestionResourceTagIdRepository;
        this.questionSentQuestionResourceTagIdMapper = questionSentQuestionResourceTagIdMapper;
    }

    /**
     * Save a questionSentQuestionResourceTagId.
     *
     * @param questionSentQuestionResourceTagIdDTO the entity to save.
     * @return the persisted entity.
     */
    public QuestionSentQuestionResourceTagIdDTO save(QuestionSentQuestionResourceTagIdDTO questionSentQuestionResourceTagIdDTO) {
        log.debug("Request to save QuestionSentQuestionResourceTagId : {}", questionSentQuestionResourceTagIdDTO);
        QuestionSentQuestionResourceTagId questionSentQuestionResourceTagId = questionSentQuestionResourceTagIdMapper.toEntity(
            questionSentQuestionResourceTagIdDTO
        );
        questionSentQuestionResourceTagId = questionSentQuestionResourceTagIdRepository.save(questionSentQuestionResourceTagId);
        return questionSentQuestionResourceTagIdMapper.toDto(questionSentQuestionResourceTagId);
    }

    /**
     * Update a questionSentQuestionResourceTagId.
     *
     * @param questionSentQuestionResourceTagIdDTO the entity to save.
     * @return the persisted entity.
     */
    public QuestionSentQuestionResourceTagIdDTO update(QuestionSentQuestionResourceTagIdDTO questionSentQuestionResourceTagIdDTO) {
        log.debug("Request to update QuestionSentQuestionResourceTagId : {}", questionSentQuestionResourceTagIdDTO);
        QuestionSentQuestionResourceTagId questionSentQuestionResourceTagId = questionSentQuestionResourceTagIdMapper.toEntity(
            questionSentQuestionResourceTagIdDTO
        );
        questionSentQuestionResourceTagId = questionSentQuestionResourceTagIdRepository.save(questionSentQuestionResourceTagId);
        return questionSentQuestionResourceTagIdMapper.toDto(questionSentQuestionResourceTagId);
    }

    /**
     * Partially update a questionSentQuestionResourceTagId.
     *
     * @param questionSentQuestionResourceTagIdDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<QuestionSentQuestionResourceTagIdDTO> partialUpdate(
        QuestionSentQuestionResourceTagIdDTO questionSentQuestionResourceTagIdDTO
    ) {
        log.debug("Request to partially update QuestionSentQuestionResourceTagId : {}", questionSentQuestionResourceTagIdDTO);

        return questionSentQuestionResourceTagIdRepository
            .findById(questionSentQuestionResourceTagIdDTO.getId())
            .map(existingQuestionSentQuestionResourceTagId -> {
                questionSentQuestionResourceTagIdMapper.partialUpdate(
                    existingQuestionSentQuestionResourceTagId,
                    questionSentQuestionResourceTagIdDTO
                );

                return existingQuestionSentQuestionResourceTagId;
            })
            .map(questionSentQuestionResourceTagIdRepository::save)
            .map(questionSentQuestionResourceTagIdMapper::toDto);
    }

    /**
     * Get all the questionSentQuestionResourceTagIds.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<QuestionSentQuestionResourceTagIdDTO> findAll() {
        log.debug("Request to get all QuestionSentQuestionResourceTagIds");
        return questionSentQuestionResourceTagIdRepository
            .findAll()
            .stream()
            .map(questionSentQuestionResourceTagIdMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one questionSentQuestionResourceTagId by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<QuestionSentQuestionResourceTagIdDTO> findOne(Long id) {
        log.debug("Request to get QuestionSentQuestionResourceTagId : {}", id);
        return questionSentQuestionResourceTagIdRepository.findById(id).map(questionSentQuestionResourceTagIdMapper::toDto);
    }

    /**
     * Delete the questionSentQuestionResourceTagId by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete QuestionSentQuestionResourceTagId : {}", id);
        questionSentQuestionResourceTagIdRepository.deleteById(id);
    }
}
