package org.contextmapper.generated.gateway.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.contextmapper.generated.gateway.domain.QuestionSentTagId;
import org.contextmapper.generated.gateway.repository.QuestionSentTagIdRepository;
import org.contextmapper.generated.gateway.service.dto.QuestionSentTagIdDTO;
import org.contextmapper.generated.gateway.service.mapper.QuestionSentTagIdMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    public Mono<QuestionSentTagIdDTO> save(QuestionSentTagIdDTO questionSentTagIdDTO) {
        log.debug("Request to save QuestionSentTagId : {}", questionSentTagIdDTO);
        return questionSentTagIdRepository.save(questionSentTagIdMapper.toEntity(questionSentTagIdDTO)).map(questionSentTagIdMapper::toDto);
    }

    /**
     * Update a questionSentTagId.
     *
     * @param questionSentTagIdDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<QuestionSentTagIdDTO> update(QuestionSentTagIdDTO questionSentTagIdDTO) {
        log.debug("Request to update QuestionSentTagId : {}", questionSentTagIdDTO);
        return questionSentTagIdRepository.save(questionSentTagIdMapper.toEntity(questionSentTagIdDTO)).map(questionSentTagIdMapper::toDto);
    }

    /**
     * Partially update a questionSentTagId.
     *
     * @param questionSentTagIdDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<QuestionSentTagIdDTO> partialUpdate(QuestionSentTagIdDTO questionSentTagIdDTO) {
        log.debug("Request to partially update QuestionSentTagId : {}", questionSentTagIdDTO);

        return questionSentTagIdRepository
            .findById(questionSentTagIdDTO.getId())
            .map(existingQuestionSentTagId -> {
                questionSentTagIdMapper.partialUpdate(existingQuestionSentTagId, questionSentTagIdDTO);

                return existingQuestionSentTagId;
            })
            .flatMap(questionSentTagIdRepository::save)
            .map(questionSentTagIdMapper::toDto);
    }

    /**
     * Get all the questionSentTagIds.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<QuestionSentTagIdDTO> findAll() {
        log.debug("Request to get all QuestionSentTagIds");
        return questionSentTagIdRepository.findAll().map(questionSentTagIdMapper::toDto);
    }

    /**
     * Returns the number of questionSentTagIds available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return questionSentTagIdRepository.count();
    }

    /**
     * Get one questionSentTagId by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<QuestionSentTagIdDTO> findOne(Long id) {
        log.debug("Request to get QuestionSentTagId : {}", id);
        return questionSentTagIdRepository.findById(id).map(questionSentTagIdMapper::toDto);
    }

    /**
     * Delete the questionSentTagId by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete QuestionSentTagId : {}", id);
        return questionSentTagIdRepository.deleteById(id);
    }
}
