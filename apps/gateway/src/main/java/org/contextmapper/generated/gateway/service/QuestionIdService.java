package org.contextmapper.generated.gateway.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.contextmapper.generated.gateway.domain.QuestionId;
import org.contextmapper.generated.gateway.repository.QuestionIdRepository;
import org.contextmapper.generated.gateway.service.dto.QuestionIdDTO;
import org.contextmapper.generated.gateway.service.mapper.QuestionIdMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    public Mono<QuestionIdDTO> save(QuestionIdDTO questionIdDTO) {
        log.debug("Request to save QuestionId : {}", questionIdDTO);
        return questionIdRepository.save(questionIdMapper.toEntity(questionIdDTO)).map(questionIdMapper::toDto);
    }

    /**
     * Update a questionId.
     *
     * @param questionIdDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<QuestionIdDTO> update(QuestionIdDTO questionIdDTO) {
        log.debug("Request to update QuestionId : {}", questionIdDTO);
        return questionIdRepository.save(questionIdMapper.toEntity(questionIdDTO)).map(questionIdMapper::toDto);
    }

    /**
     * Partially update a questionId.
     *
     * @param questionIdDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<QuestionIdDTO> partialUpdate(QuestionIdDTO questionIdDTO) {
        log.debug("Request to partially update QuestionId : {}", questionIdDTO);

        return questionIdRepository
            .findById(questionIdDTO.getId())
            .map(existingQuestionId -> {
                questionIdMapper.partialUpdate(existingQuestionId, questionIdDTO);

                return existingQuestionId;
            })
            .flatMap(questionIdRepository::save)
            .map(questionIdMapper::toDto);
    }

    /**
     * Get all the questionIds.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<QuestionIdDTO> findAll() {
        log.debug("Request to get all QuestionIds");
        return questionIdRepository.findAll().map(questionIdMapper::toDto);
    }

    /**
     * Returns the number of questionIds available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return questionIdRepository.count();
    }

    /**
     * Get one questionId by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<QuestionIdDTO> findOne(Long id) {
        log.debug("Request to get QuestionId : {}", id);
        return questionIdRepository.findById(id).map(questionIdMapper::toDto);
    }

    /**
     * Delete the questionId by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete QuestionId : {}", id);
        return questionIdRepository.deleteById(id);
    }
}
