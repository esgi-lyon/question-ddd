package org.contextmapper.generated.evaluationcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.evaluationcontext.domain.EvaluationQuestion;
import org.contextmapper.generated.evaluationcontext.repository.EvaluationQuestionRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.EvaluationQuestionDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.EvaluationQuestionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public EvaluationQuestionDTO save(EvaluationQuestionDTO evaluationQuestionDTO) {
        log.debug("Request to save EvaluationQuestion : {}", evaluationQuestionDTO);
        EvaluationQuestion evaluationQuestion = evaluationQuestionMapper.toEntity(evaluationQuestionDTO);
        evaluationQuestion = evaluationQuestionRepository.save(evaluationQuestion);
        return evaluationQuestionMapper.toDto(evaluationQuestion);
    }

    /**
     * Update a evaluationQuestion.
     *
     * @param evaluationQuestionDTO the entity to save.
     * @return the persisted entity.
     */
    public EvaluationQuestionDTO update(EvaluationQuestionDTO evaluationQuestionDTO) {
        log.debug("Request to update EvaluationQuestion : {}", evaluationQuestionDTO);
        EvaluationQuestion evaluationQuestion = evaluationQuestionMapper.toEntity(evaluationQuestionDTO);
        evaluationQuestion = evaluationQuestionRepository.save(evaluationQuestion);
        return evaluationQuestionMapper.toDto(evaluationQuestion);
    }

    /**
     * Partially update a evaluationQuestion.
     *
     * @param evaluationQuestionDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EvaluationQuestionDTO> partialUpdate(EvaluationQuestionDTO evaluationQuestionDTO) {
        log.debug("Request to partially update EvaluationQuestion : {}", evaluationQuestionDTO);

        return evaluationQuestionRepository
            .findById(evaluationQuestionDTO.getId())
            .map(existingEvaluationQuestion -> {
                evaluationQuestionMapper.partialUpdate(existingEvaluationQuestion, evaluationQuestionDTO);

                return existingEvaluationQuestion;
            })
            .map(evaluationQuestionRepository::save)
            .map(evaluationQuestionMapper::toDto);
    }

    /**
     * Get all the evaluationQuestions.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EvaluationQuestionDTO> findAll() {
        log.debug("Request to get all EvaluationQuestions");
        return evaluationQuestionRepository
            .findAll()
            .stream()
            .map(evaluationQuestionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one evaluationQuestion by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EvaluationQuestionDTO> findOne(Long id) {
        log.debug("Request to get EvaluationQuestion : {}", id);
        return evaluationQuestionRepository.findById(id).map(evaluationQuestionMapper::toDto);
    }

    /**
     * Delete the evaluationQuestion by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete EvaluationQuestion : {}", id);
        evaluationQuestionRepository.deleteById(id);
    }
}
