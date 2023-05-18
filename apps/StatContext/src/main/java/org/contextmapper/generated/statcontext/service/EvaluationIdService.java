package org.contextmapper.generated.statcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.statcontext.domain.EvaluationId;
import org.contextmapper.generated.statcontext.repository.EvaluationIdRepository;
import org.contextmapper.generated.statcontext.service.dto.EvaluationIdDTO;
import org.contextmapper.generated.statcontext.service.mapper.EvaluationIdMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link EvaluationId}.
 */
@Service
@Transactional
public class EvaluationIdService {

    private final Logger log = LoggerFactory.getLogger(EvaluationIdService.class);

    private final EvaluationIdRepository evaluationIdRepository;

    private final EvaluationIdMapper evaluationIdMapper;

    public EvaluationIdService(EvaluationIdRepository evaluationIdRepository, EvaluationIdMapper evaluationIdMapper) {
        this.evaluationIdRepository = evaluationIdRepository;
        this.evaluationIdMapper = evaluationIdMapper;
    }

    /**
     * Save a evaluationId.
     *
     * @param evaluationIdDTO the entity to save.
     * @return the persisted entity.
     */
    public EvaluationIdDTO save(EvaluationIdDTO evaluationIdDTO) {
        log.debug("Request to save EvaluationId : {}", evaluationIdDTO);
        EvaluationId evaluationId = evaluationIdMapper.toEntity(evaluationIdDTO);
        evaluationId = evaluationIdRepository.save(evaluationId);
        return evaluationIdMapper.toDto(evaluationId);
    }

    /**
     * Update a evaluationId.
     *
     * @param evaluationIdDTO the entity to save.
     * @return the persisted entity.
     */
    public EvaluationIdDTO update(EvaluationIdDTO evaluationIdDTO) {
        log.debug("Request to update EvaluationId : {}", evaluationIdDTO);
        EvaluationId evaluationId = evaluationIdMapper.toEntity(evaluationIdDTO);
        evaluationId = evaluationIdRepository.save(evaluationId);
        return evaluationIdMapper.toDto(evaluationId);
    }

    /**
     * Partially update a evaluationId.
     *
     * @param evaluationIdDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EvaluationIdDTO> partialUpdate(EvaluationIdDTO evaluationIdDTO) {
        log.debug("Request to partially update EvaluationId : {}", evaluationIdDTO);

        return evaluationIdRepository
            .findById(evaluationIdDTO.getId())
            .map(existingEvaluationId -> {
                evaluationIdMapper.partialUpdate(existingEvaluationId, evaluationIdDTO);

                return existingEvaluationId;
            })
            .map(evaluationIdRepository::save)
            .map(evaluationIdMapper::toDto);
    }

    /**
     * Get all the evaluationIds.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EvaluationIdDTO> findAll() {
        log.debug("Request to get all EvaluationIds");
        return evaluationIdRepository.findAll().stream().map(evaluationIdMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one evaluationId by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EvaluationIdDTO> findOne(Long id) {
        log.debug("Request to get EvaluationId : {}", id);
        return evaluationIdRepository.findById(id).map(evaluationIdMapper::toDto);
    }

    /**
     * Delete the evaluationId by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete EvaluationId : {}", id);
        evaluationIdRepository.deleteById(id);
    }
}
