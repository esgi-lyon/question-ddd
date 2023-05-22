package org.contextmapper.generated.gateway.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.contextmapper.generated.gateway.domain.EvaluationQuestion;
import org.contextmapper.generated.gateway.repository.EvaluationQuestionRepository;
import org.contextmapper.generated.gateway.service.dto.EvaluationQuestionDTO;
import org.contextmapper.generated.gateway.service.mapper.EvaluationQuestionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link EvaluationQuestion}.
 */
@Service
@Transactional
public class EvaluationQuestionService {

    private final Logger log = LoggerFactory.getLogger(EvaluationQuestionService.class);

    private final EvaluationQuestionRepository evaluationQuestionRepository;

    private final EvaluationQuestionMapper evaluationQuestionMapper;

    public EvaluationQuestionService(
        EvaluationQuestionRepository evaluationQuestionRepository,
        EvaluationQuestionMapper evaluationQuestionMapper
    ) {
        this.evaluationQuestionRepository = evaluationQuestionRepository;
        this.evaluationQuestionMapper = evaluationQuestionMapper;
    }

    /**
     * Save a evaluationQuestion.
     *
     * @param evaluationQuestionDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<EvaluationQuestionDTO> save(EvaluationQuestionDTO evaluationQuestionDTO) {
        log.debug("Request to save EvaluationQuestion : {}", evaluationQuestionDTO);
        return evaluationQuestionRepository
            .save(evaluationQuestionMapper.toEntity(evaluationQuestionDTO))
            .map(evaluationQuestionMapper::toDto);
    }

    /**
     * Update a evaluationQuestion.
     *
     * @param evaluationQuestionDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<EvaluationQuestionDTO> update(EvaluationQuestionDTO evaluationQuestionDTO) {
        log.debug("Request to update EvaluationQuestion : {}", evaluationQuestionDTO);
        return evaluationQuestionRepository
            .save(evaluationQuestionMapper.toEntity(evaluationQuestionDTO))
            .map(evaluationQuestionMapper::toDto);
    }

    /**
     * Partially update a evaluationQuestion.
     *
     * @param evaluationQuestionDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<EvaluationQuestionDTO> partialUpdate(EvaluationQuestionDTO evaluationQuestionDTO) {
        log.debug("Request to partially update EvaluationQuestion : {}", evaluationQuestionDTO);

        return evaluationQuestionRepository
            .findById(evaluationQuestionDTO.getId())
            .map(existingEvaluationQuestion -> {
                evaluationQuestionMapper.partialUpdate(existingEvaluationQuestion, evaluationQuestionDTO);

                return existingEvaluationQuestion;
            })
            .flatMap(evaluationQuestionRepository::save)
            .map(evaluationQuestionMapper::toDto);
    }

    /**
     * Get all the evaluationQuestions.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<EvaluationQuestionDTO> findAll() {
        log.debug("Request to get all EvaluationQuestions");
        return evaluationQuestionRepository.findAll().map(evaluationQuestionMapper::toDto);
    }

    /**
     * Returns the number of evaluationQuestions available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return evaluationQuestionRepository.count();
    }

    /**
     * Get one evaluationQuestion by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<EvaluationQuestionDTO> findOne(Long id) {
        log.debug("Request to get EvaluationQuestion : {}", id);
        return evaluationQuestionRepository.findById(id).map(evaluationQuestionMapper::toDto);
    }

    /**
     * Delete the evaluationQuestion by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete EvaluationQuestion : {}", id);
        return evaluationQuestionRepository.deleteById(id);
    }
}
