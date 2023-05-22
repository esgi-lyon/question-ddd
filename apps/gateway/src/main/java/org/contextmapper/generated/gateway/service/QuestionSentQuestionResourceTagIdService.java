package org.contextmapper.generated.gateway.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.contextmapper.generated.gateway.domain.QuestionSentQuestionResourceTagId;
import org.contextmapper.generated.gateway.repository.QuestionSentQuestionResourceTagIdRepository;
import org.contextmapper.generated.gateway.service.dto.QuestionSentQuestionResourceTagIdDTO;
import org.contextmapper.generated.gateway.service.mapper.QuestionSentQuestionResourceTagIdMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    public Mono<QuestionSentQuestionResourceTagIdDTO> save(QuestionSentQuestionResourceTagIdDTO questionSentQuestionResourceTagIdDTO) {
        log.debug("Request to save QuestionSentQuestionResourceTagId : {}", questionSentQuestionResourceTagIdDTO);
        return questionSentQuestionResourceTagIdRepository
            .save(questionSentQuestionResourceTagIdMapper.toEntity(questionSentQuestionResourceTagIdDTO))
            .map(questionSentQuestionResourceTagIdMapper::toDto);
    }

    /**
     * Update a questionSentQuestionResourceTagId.
     *
     * @param questionSentQuestionResourceTagIdDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<QuestionSentQuestionResourceTagIdDTO> update(QuestionSentQuestionResourceTagIdDTO questionSentQuestionResourceTagIdDTO) {
        log.debug("Request to update QuestionSentQuestionResourceTagId : {}", questionSentQuestionResourceTagIdDTO);
        return questionSentQuestionResourceTagIdRepository
            .save(questionSentQuestionResourceTagIdMapper.toEntity(questionSentQuestionResourceTagIdDTO))
            .map(questionSentQuestionResourceTagIdMapper::toDto);
    }

    /**
     * Partially update a questionSentQuestionResourceTagId.
     *
     * @param questionSentQuestionResourceTagIdDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<QuestionSentQuestionResourceTagIdDTO> partialUpdate(
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
            .flatMap(questionSentQuestionResourceTagIdRepository::save)
            .map(questionSentQuestionResourceTagIdMapper::toDto);
    }

    /**
     * Get all the questionSentQuestionResourceTagIds.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<QuestionSentQuestionResourceTagIdDTO> findAll() {
        log.debug("Request to get all QuestionSentQuestionResourceTagIds");
        return questionSentQuestionResourceTagIdRepository.findAll().map(questionSentQuestionResourceTagIdMapper::toDto);
    }

    /**
     * Returns the number of questionSentQuestionResourceTagIds available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return questionSentQuestionResourceTagIdRepository.count();
    }

    /**
     * Get one questionSentQuestionResourceTagId by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<QuestionSentQuestionResourceTagIdDTO> findOne(Long id) {
        log.debug("Request to get QuestionSentQuestionResourceTagId : {}", id);
        return questionSentQuestionResourceTagIdRepository.findById(id).map(questionSentQuestionResourceTagIdMapper::toDto);
    }

    /**
     * Delete the questionSentQuestionResourceTagId by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete QuestionSentQuestionResourceTagId : {}", id);
        return questionSentQuestionResourceTagIdRepository.deleteById(id);
    }
}
