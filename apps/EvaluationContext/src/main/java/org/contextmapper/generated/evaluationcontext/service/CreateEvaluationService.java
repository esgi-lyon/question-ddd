package org.contextmapper.generated.evaluationcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.evaluationcontext.domain.CreateEvaluation;
import org.contextmapper.generated.evaluationcontext.repository.CreateEvaluationRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.CreateEvaluationDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.CreateEvaluationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link CreateEvaluation}.
 */
@Service
@Transactional
public class CreateEvaluationService {

    private final Logger log = LoggerFactory.getLogger(CreateEvaluationService.class);

    private final CreateEvaluationRepository createEvaluationRepository;

    private final CreateEvaluationMapper createEvaluationMapper;

    public CreateEvaluationService(CreateEvaluationRepository createEvaluationRepository, CreateEvaluationMapper createEvaluationMapper) {
        this.createEvaluationRepository = createEvaluationRepository;
        this.createEvaluationMapper = createEvaluationMapper;
    }

    /**
     * Save a createEvaluation.
     *
     * @param createEvaluationDTO the entity to save.
     * @return the persisted entity.
     */
    public CreateEvaluationDTO save(CreateEvaluationDTO createEvaluationDTO) {
        log.debug("Request to save CreateEvaluation : {}", createEvaluationDTO);
        CreateEvaluation createEvaluation = createEvaluationMapper.toEntity(createEvaluationDTO);
        createEvaluation = createEvaluationRepository.save(createEvaluation);
        return createEvaluationMapper.toDto(createEvaluation);
    }

    /**
     * Update a createEvaluation.
     *
     * @param createEvaluationDTO the entity to save.
     * @return the persisted entity.
     */
    public CreateEvaluationDTO update(CreateEvaluationDTO createEvaluationDTO) {
        log.debug("Request to update CreateEvaluation : {}", createEvaluationDTO);
        CreateEvaluation createEvaluation = createEvaluationMapper.toEntity(createEvaluationDTO);
        createEvaluation = createEvaluationRepository.save(createEvaluation);
        return createEvaluationMapper.toDto(createEvaluation);
    }

    /**
     * Partially update a createEvaluation.
     *
     * @param createEvaluationDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CreateEvaluationDTO> partialUpdate(CreateEvaluationDTO createEvaluationDTO) {
        log.debug("Request to partially update CreateEvaluation : {}", createEvaluationDTO);

        return createEvaluationRepository
            .findById(createEvaluationDTO.getId())
            .map(existingCreateEvaluation -> {
                createEvaluationMapper.partialUpdate(existingCreateEvaluation, createEvaluationDTO);

                return existingCreateEvaluation;
            })
            .map(createEvaluationRepository::save)
            .map(createEvaluationMapper::toDto);
    }

    /**
     * Get all the createEvaluations.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CreateEvaluationDTO> findAll() {
        log.debug("Request to get all CreateEvaluations");
        return createEvaluationRepository
            .findAll()
            .stream()
            .map(createEvaluationMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one createEvaluation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CreateEvaluationDTO> findOne(Long id) {
        log.debug("Request to get CreateEvaluation : {}", id);
        return createEvaluationRepository.findById(id).map(createEvaluationMapper::toDto);
    }

    /**
     * Delete the createEvaluation by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CreateEvaluation : {}", id);
        createEvaluationRepository.deleteById(id);
    }
}
