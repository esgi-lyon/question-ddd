package org.contextmapper.generated.evaluationcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.evaluationcontext.domain.EvaluationCreated;
import org.contextmapper.generated.evaluationcontext.repository.EvaluationCreatedRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.EvaluationCreatedDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.EvaluationCreatedMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link EvaluationCreated}.
 */
@Service
@Transactional
public class EvaluationCreatedService {

    private final Logger log = LoggerFactory.getLogger(EvaluationCreatedService.class);

    private final EvaluationCreatedRepository evaluationCreatedRepository;

    private final EvaluationCreatedMapper evaluationCreatedMapper;

    public EvaluationCreatedService(
        EvaluationCreatedRepository evaluationCreatedRepository,
        EvaluationCreatedMapper evaluationCreatedMapper
    ) {
        this.evaluationCreatedRepository = evaluationCreatedRepository;
        this.evaluationCreatedMapper = evaluationCreatedMapper;
    }

    /**
     * Save a evaluationCreated.
     *
     * @param evaluationCreatedDTO the entity to save.
     * @return the persisted entity.
     */
    public EvaluationCreatedDTO save(EvaluationCreatedDTO evaluationCreatedDTO) {
        log.debug("Request to save EvaluationCreated : {}", evaluationCreatedDTO);
        EvaluationCreated evaluationCreated = evaluationCreatedMapper.toEntity(evaluationCreatedDTO);
        evaluationCreated = evaluationCreatedRepository.save(evaluationCreated);
        return evaluationCreatedMapper.toDto(evaluationCreated);
    }

    /**
     * Update a evaluationCreated.
     *
     * @param evaluationCreatedDTO the entity to save.
     * @return the persisted entity.
     */
    public EvaluationCreatedDTO update(EvaluationCreatedDTO evaluationCreatedDTO) {
        log.debug("Request to update EvaluationCreated : {}", evaluationCreatedDTO);
        EvaluationCreated evaluationCreated = evaluationCreatedMapper.toEntity(evaluationCreatedDTO);
        evaluationCreated = evaluationCreatedRepository.save(evaluationCreated);
        return evaluationCreatedMapper.toDto(evaluationCreated);
    }

    /**
     * Partially update a evaluationCreated.
     *
     * @param evaluationCreatedDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EvaluationCreatedDTO> partialUpdate(EvaluationCreatedDTO evaluationCreatedDTO) {
        log.debug("Request to partially update EvaluationCreated : {}", evaluationCreatedDTO);

        return evaluationCreatedRepository
            .findById(evaluationCreatedDTO.getId())
            .map(existingEvaluationCreated -> {
                evaluationCreatedMapper.partialUpdate(existingEvaluationCreated, evaluationCreatedDTO);

                return existingEvaluationCreated;
            })
            .map(evaluationCreatedRepository::save)
            .map(evaluationCreatedMapper::toDto);
    }

    /**
     * Get all the evaluationCreateds.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EvaluationCreatedDTO> findAll() {
        log.debug("Request to get all EvaluationCreateds");
        return evaluationCreatedRepository
            .findAll()
            .stream()
            .map(evaluationCreatedMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one evaluationCreated by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EvaluationCreatedDTO> findOne(Long id) {
        log.debug("Request to get EvaluationCreated : {}", id);
        return evaluationCreatedRepository.findById(id).map(evaluationCreatedMapper::toDto);
    }

    /**
     * Delete the evaluationCreated by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete EvaluationCreated : {}", id);
        evaluationCreatedRepository.deleteById(id);
    }
}
