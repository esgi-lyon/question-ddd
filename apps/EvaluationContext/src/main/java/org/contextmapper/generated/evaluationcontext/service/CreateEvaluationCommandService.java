package org.contextmapper.generated.evaluationcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.evaluationcontext.domain.CreateEvaluationCommand;
import org.contextmapper.generated.evaluationcontext.repository.CreateEvaluationCommandRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.CreateEvaluationCommandDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.CreateEvaluationCommandMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link CreateEvaluationCommand}.
 */
@Service
@Transactional
public class CreateEvaluationCommandService {

    private final Logger log = LoggerFactory.getLogger(CreateEvaluationCommandService.class);

    private final CreateEvaluationCommandRepository createEvaluationCommandRepository;

    private final CreateEvaluationCommandMapper createEvaluationCommandMapper;

    public CreateEvaluationCommandService(
        CreateEvaluationCommandRepository createEvaluationCommandRepository,
        CreateEvaluationCommandMapper createEvaluationCommandMapper
    ) {
        this.createEvaluationCommandRepository = createEvaluationCommandRepository;
        this.createEvaluationCommandMapper = createEvaluationCommandMapper;
    }

    /**
     * Save a createEvaluationCommand.
     *
     * @param createEvaluationCommandDTO the entity to save.
     * @return the persisted entity.
     */
    public CreateEvaluationCommandDTO save(CreateEvaluationCommandDTO createEvaluationCommandDTO) {
        log.debug("Request to save CreateEvaluationCommand : {}", createEvaluationCommandDTO);
        CreateEvaluationCommand createEvaluationCommand = createEvaluationCommandMapper.toEntity(createEvaluationCommandDTO);
        createEvaluationCommand = createEvaluationCommandRepository.save(createEvaluationCommand);
        return createEvaluationCommandMapper.toDto(createEvaluationCommand);
    }

    /**
     * Update a createEvaluationCommand.
     *
     * @param createEvaluationCommandDTO the entity to save.
     * @return the persisted entity.
     */
    public CreateEvaluationCommandDTO update(CreateEvaluationCommandDTO createEvaluationCommandDTO) {
        log.debug("Request to update CreateEvaluationCommand : {}", createEvaluationCommandDTO);
        CreateEvaluationCommand createEvaluationCommand = createEvaluationCommandMapper.toEntity(createEvaluationCommandDTO);
        createEvaluationCommand = createEvaluationCommandRepository.save(createEvaluationCommand);
        return createEvaluationCommandMapper.toDto(createEvaluationCommand);
    }

    /**
     * Partially update a createEvaluationCommand.
     *
     * @param createEvaluationCommandDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CreateEvaluationCommandDTO> partialUpdate(CreateEvaluationCommandDTO createEvaluationCommandDTO) {
        log.debug("Request to partially update CreateEvaluationCommand : {}", createEvaluationCommandDTO);

        return createEvaluationCommandRepository
            .findById(createEvaluationCommandDTO.getId())
            .map(existingCreateEvaluationCommand -> {
                createEvaluationCommandMapper.partialUpdate(existingCreateEvaluationCommand, createEvaluationCommandDTO);

                return existingCreateEvaluationCommand;
            })
            .map(createEvaluationCommandRepository::save)
            .map(createEvaluationCommandMapper::toDto);
    }

    /**
     * Get all the createEvaluationCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CreateEvaluationCommandDTO> findAll() {
        log.debug("Request to get all CreateEvaluationCommands");
        return createEvaluationCommandRepository
            .findAll()
            .stream()
            .map(createEvaluationCommandMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one createEvaluationCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CreateEvaluationCommandDTO> findOne(Long id) {
        log.debug("Request to get CreateEvaluationCommand : {}", id);
        return createEvaluationCommandRepository.findById(id).map(createEvaluationCommandMapper::toDto);
    }

    /**
     * Delete the createEvaluationCommand by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CreateEvaluationCommand : {}", id);
        createEvaluationCommandRepository.deleteById(id);
    }
}
