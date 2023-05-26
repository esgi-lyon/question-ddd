package org.contextmapper.generated.answercontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.answercontext.domain.QuestionSentId;
import org.contextmapper.generated.answercontext.repository.QuestionSentIdRepository;
import org.contextmapper.generated.answercontext.service.dto.QuestionSentIdDTO;
import org.contextmapper.generated.answercontext.service.mapper.QuestionSentIdMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link QuestionSentId}.
 */
@Service
@Transactional
public class QuestionSentIdService {

    private final Logger log = LoggerFactory.getLogger(QuestionSentIdService.class);

    private final QuestionSentIdRepository questionSentIdRepository;

    private final QuestionSentIdMapper questionSentIdMapper;

    public QuestionSentIdService(QuestionSentIdRepository questionSentIdRepository, QuestionSentIdMapper questionSentIdMapper) {
        this.questionSentIdRepository = questionSentIdRepository;
        this.questionSentIdMapper = questionSentIdMapper;
    }

    /**
     * Save a questionSentId.
     *
     * @param questionSentIdDTO the entity to save.
     * @return the persisted entity.
     */
    public QuestionSentIdDTO save(QuestionSentIdDTO questionSentIdDTO) {
        log.debug("Request to save QuestionSentId : {}", questionSentIdDTO);
        QuestionSentId questionSentId = questionSentIdMapper.toEntity(questionSentIdDTO);
        questionSentId = questionSentIdRepository.save(questionSentId);
        return questionSentIdMapper.toDto(questionSentId);
    }

    /**
     * Update a questionSentId.
     *
     * @param questionSentIdDTO the entity to save.
     * @return the persisted entity.
     */
    public QuestionSentIdDTO update(QuestionSentIdDTO questionSentIdDTO) {
        log.debug("Request to update QuestionSentId : {}", questionSentIdDTO);
        QuestionSentId questionSentId = questionSentIdMapper.toEntity(questionSentIdDTO);
        questionSentId = questionSentIdRepository.save(questionSentId);
        return questionSentIdMapper.toDto(questionSentId);
    }

    /**
     * Partially update a questionSentId.
     *
     * @param questionSentIdDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<QuestionSentIdDTO> partialUpdate(QuestionSentIdDTO questionSentIdDTO) {
        log.debug("Request to partially update QuestionSentId : {}", questionSentIdDTO);

        return questionSentIdRepository
            .findById(questionSentIdDTO.getId())
            .map(existingQuestionSentId -> {
                questionSentIdMapper.partialUpdate(existingQuestionSentId, questionSentIdDTO);

                return existingQuestionSentId;
            })
            .map(questionSentIdRepository::save)
            .map(questionSentIdMapper::toDto);
    }

    /**
     * Get all the questionSentIds.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<QuestionSentIdDTO> findAll() {
        log.debug("Request to get all QuestionSentIds");
        return questionSentIdRepository
            .findAll()
            .stream()
            .map(questionSentIdMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one questionSentId by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<QuestionSentIdDTO> findOne(Long id) {
        log.debug("Request to get QuestionSentId : {}", id);
        return questionSentIdRepository.findById(id).map(questionSentIdMapper::toDto);
    }

    /**
     * Delete the questionSentId by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete QuestionSentId : {}", id);
        questionSentIdRepository.deleteById(id);
    }
}
