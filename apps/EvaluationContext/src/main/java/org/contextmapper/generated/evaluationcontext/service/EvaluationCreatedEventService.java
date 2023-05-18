package org.contextmapper.generated.evaluationcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.evaluationcontext.domain.EvaluationCreatedEvent;
import org.contextmapper.generated.evaluationcontext.repository.EvaluationCreatedEventRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.EvaluationCreatedEventDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.EvaluationCreatedEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link EvaluationCreatedEvent}.
 */
@Service
@Transactional
public class EvaluationCreatedEventService {

    private final Logger log = LoggerFactory.getLogger(EvaluationCreatedEventService.class);

    private final EvaluationCreatedEventRepository evaluationCreatedEventRepository;

    private final EvaluationCreatedEventMapper evaluationCreatedEventMapper;

    public EvaluationCreatedEventService(
        EvaluationCreatedEventRepository evaluationCreatedEventRepository,
        EvaluationCreatedEventMapper evaluationCreatedEventMapper
    ) {
        this.evaluationCreatedEventRepository = evaluationCreatedEventRepository;
        this.evaluationCreatedEventMapper = evaluationCreatedEventMapper;
    }

    /**
     * Save a evaluationCreatedEvent.
     *
     * @param evaluationCreatedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public EvaluationCreatedEventDTO save(EvaluationCreatedEventDTO evaluationCreatedEventDTO) {
        log.debug("Request to save EvaluationCreatedEvent : {}", evaluationCreatedEventDTO);
        EvaluationCreatedEvent evaluationCreatedEvent = evaluationCreatedEventMapper.toEntity(evaluationCreatedEventDTO);
        evaluationCreatedEvent = evaluationCreatedEventRepository.save(evaluationCreatedEvent);
        return evaluationCreatedEventMapper.toDto(evaluationCreatedEvent);
    }

    /**
     * Update a evaluationCreatedEvent.
     *
     * @param evaluationCreatedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public EvaluationCreatedEventDTO update(EvaluationCreatedEventDTO evaluationCreatedEventDTO) {
        log.debug("Request to update EvaluationCreatedEvent : {}", evaluationCreatedEventDTO);
        EvaluationCreatedEvent evaluationCreatedEvent = evaluationCreatedEventMapper.toEntity(evaluationCreatedEventDTO);
        evaluationCreatedEvent = evaluationCreatedEventRepository.save(evaluationCreatedEvent);
        return evaluationCreatedEventMapper.toDto(evaluationCreatedEvent);
    }

    /**
     * Partially update a evaluationCreatedEvent.
     *
     * @param evaluationCreatedEventDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EvaluationCreatedEventDTO> partialUpdate(EvaluationCreatedEventDTO evaluationCreatedEventDTO) {
        log.debug("Request to partially update EvaluationCreatedEvent : {}", evaluationCreatedEventDTO);

        return evaluationCreatedEventRepository
            .findById(evaluationCreatedEventDTO.getId())
            .map(existingEvaluationCreatedEvent -> {
                evaluationCreatedEventMapper.partialUpdate(existingEvaluationCreatedEvent, evaluationCreatedEventDTO);

                return existingEvaluationCreatedEvent;
            })
            .map(evaluationCreatedEventRepository::save)
            .map(evaluationCreatedEventMapper::toDto);
    }

    /**
     * Get all the evaluationCreatedEvents.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EvaluationCreatedEventDTO> findAll() {
        log.debug("Request to get all EvaluationCreatedEvents");
        return evaluationCreatedEventRepository
            .findAll()
            .stream()
            .map(evaluationCreatedEventMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one evaluationCreatedEvent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EvaluationCreatedEventDTO> findOne(Long id) {
        log.debug("Request to get EvaluationCreatedEvent : {}", id);
        return evaluationCreatedEventRepository.findById(id).map(evaluationCreatedEventMapper::toDto);
    }

    /**
     * Delete the evaluationCreatedEvent by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete EvaluationCreatedEvent : {}", id);
        evaluationCreatedEventRepository.deleteById(id);
    }
}
