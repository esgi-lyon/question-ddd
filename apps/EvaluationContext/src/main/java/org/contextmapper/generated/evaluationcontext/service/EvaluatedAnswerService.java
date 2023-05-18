package org.contextmapper.generated.evaluationcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.evaluationcontext.domain.EvaluatedAnswer;
import org.contextmapper.generated.evaluationcontext.repository.EvaluatedAnswerRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.EvaluatedAnswerDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.EvaluatedAnswerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link EvaluatedAnswer}.
 */
@Service
@Transactional
public class EvaluatedAnswerService {

    private final Logger log = LoggerFactory.getLogger(EvaluatedAnswerService.class);

    private final EvaluatedAnswerRepository evaluatedAnswerRepository;

    private final EvaluatedAnswerMapper evaluatedAnswerMapper;

    public EvaluatedAnswerService(EvaluatedAnswerRepository evaluatedAnswerRepository, EvaluatedAnswerMapper evaluatedAnswerMapper) {
        this.evaluatedAnswerRepository = evaluatedAnswerRepository;
        this.evaluatedAnswerMapper = evaluatedAnswerMapper;
    }

    /**
     * Save a evaluatedAnswer.
     *
     * @param evaluatedAnswerDTO the entity to save.
     * @return the persisted entity.
     */
    public EvaluatedAnswerDTO save(EvaluatedAnswerDTO evaluatedAnswerDTO) {
        log.debug("Request to save EvaluatedAnswer : {}", evaluatedAnswerDTO);
        EvaluatedAnswer evaluatedAnswer = evaluatedAnswerMapper.toEntity(evaluatedAnswerDTO);
        evaluatedAnswer = evaluatedAnswerRepository.save(evaluatedAnswer);
        return evaluatedAnswerMapper.toDto(evaluatedAnswer);
    }

    /**
     * Update a evaluatedAnswer.
     *
     * @param evaluatedAnswerDTO the entity to save.
     * @return the persisted entity.
     */
    public EvaluatedAnswerDTO update(EvaluatedAnswerDTO evaluatedAnswerDTO) {
        log.debug("Request to update EvaluatedAnswer : {}", evaluatedAnswerDTO);
        EvaluatedAnswer evaluatedAnswer = evaluatedAnswerMapper.toEntity(evaluatedAnswerDTO);
        evaluatedAnswer = evaluatedAnswerRepository.save(evaluatedAnswer);
        return evaluatedAnswerMapper.toDto(evaluatedAnswer);
    }

    /**
     * Partially update a evaluatedAnswer.
     *
     * @param evaluatedAnswerDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EvaluatedAnswerDTO> partialUpdate(EvaluatedAnswerDTO evaluatedAnswerDTO) {
        log.debug("Request to partially update EvaluatedAnswer : {}", evaluatedAnswerDTO);

        return evaluatedAnswerRepository
            .findById(evaluatedAnswerDTO.getId())
            .map(existingEvaluatedAnswer -> {
                evaluatedAnswerMapper.partialUpdate(existingEvaluatedAnswer, evaluatedAnswerDTO);

                return existingEvaluatedAnswer;
            })
            .map(evaluatedAnswerRepository::save)
            .map(evaluatedAnswerMapper::toDto);
    }

    /**
     * Get all the evaluatedAnswers.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EvaluatedAnswerDTO> findAll() {
        log.debug("Request to get all EvaluatedAnswers");
        return evaluatedAnswerRepository
            .findAll()
            .stream()
            .map(evaluatedAnswerMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one evaluatedAnswer by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EvaluatedAnswerDTO> findOne(Long id) {
        log.debug("Request to get EvaluatedAnswer : {}", id);
        return evaluatedAnswerRepository.findById(id).map(evaluatedAnswerMapper::toDto);
    }

    /**
     * Delete the evaluatedAnswer by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete EvaluatedAnswer : {}", id);
        evaluatedAnswerRepository.deleteById(id);
    }
}
