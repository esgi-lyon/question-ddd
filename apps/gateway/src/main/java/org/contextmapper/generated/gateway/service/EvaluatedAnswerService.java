package org.contextmapper.generated.gateway.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.contextmapper.generated.gateway.domain.EvaluatedAnswer;
import org.contextmapper.generated.gateway.repository.EvaluatedAnswerRepository;
import org.contextmapper.generated.gateway.service.dto.EvaluatedAnswerDTO;
import org.contextmapper.generated.gateway.service.mapper.EvaluatedAnswerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    public Mono<EvaluatedAnswerDTO> save(EvaluatedAnswerDTO evaluatedAnswerDTO) {
        log.debug("Request to save EvaluatedAnswer : {}", evaluatedAnswerDTO);
        return evaluatedAnswerRepository.save(evaluatedAnswerMapper.toEntity(evaluatedAnswerDTO)).map(evaluatedAnswerMapper::toDto);
    }

    /**
     * Update a evaluatedAnswer.
     *
     * @param evaluatedAnswerDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<EvaluatedAnswerDTO> update(EvaluatedAnswerDTO evaluatedAnswerDTO) {
        log.debug("Request to update EvaluatedAnswer : {}", evaluatedAnswerDTO);
        return evaluatedAnswerRepository.save(evaluatedAnswerMapper.toEntity(evaluatedAnswerDTO)).map(evaluatedAnswerMapper::toDto);
    }

    /**
     * Partially update a evaluatedAnswer.
     *
     * @param evaluatedAnswerDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<EvaluatedAnswerDTO> partialUpdate(EvaluatedAnswerDTO evaluatedAnswerDTO) {
        log.debug("Request to partially update EvaluatedAnswer : {}", evaluatedAnswerDTO);

        return evaluatedAnswerRepository
            .findById(evaluatedAnswerDTO.getId())
            .map(existingEvaluatedAnswer -> {
                evaluatedAnswerMapper.partialUpdate(existingEvaluatedAnswer, evaluatedAnswerDTO);

                return existingEvaluatedAnswer;
            })
            .flatMap(evaluatedAnswerRepository::save)
            .map(evaluatedAnswerMapper::toDto);
    }

    /**
     * Get all the evaluatedAnswers.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<EvaluatedAnswerDTO> findAll() {
        log.debug("Request to get all EvaluatedAnswers");
        return evaluatedAnswerRepository.findAll().map(evaluatedAnswerMapper::toDto);
    }

    /**
     * Returns the number of evaluatedAnswers available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return evaluatedAnswerRepository.count();
    }

    /**
     * Get one evaluatedAnswer by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<EvaluatedAnswerDTO> findOne(Long id) {
        log.debug("Request to get EvaluatedAnswer : {}", id);
        return evaluatedAnswerRepository.findById(id).map(evaluatedAnswerMapper::toDto);
    }

    /**
     * Delete the evaluatedAnswer by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete EvaluatedAnswer : {}", id);
        return evaluatedAnswerRepository.deleteById(id);
    }
}
