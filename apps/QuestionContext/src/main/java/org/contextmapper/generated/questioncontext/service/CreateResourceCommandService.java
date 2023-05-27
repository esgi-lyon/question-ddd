package org.contextmapper.generated.questioncontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.questioncontext.domain.CreateResourceCommand;
import org.contextmapper.generated.questioncontext.repository.CreateResourceCommandRepository;
import org.contextmapper.generated.questioncontext.service.dto.CreateResourceCommandDTO;
import org.contextmapper.generated.questioncontext.service.mapper.CreateResourceCommandMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link CreateResourceCommand}.
 */
@Service
@Transactional
public class CreateResourceCommandService {

    private final Logger log = LoggerFactory.getLogger(CreateResourceCommandService.class);

    private final CreateResourceCommandRepository createResourceCommandRepository;

    private final CreateResourceCommandMapper createResourceCommandMapper;

    public CreateResourceCommandService(
        CreateResourceCommandRepository createResourceCommandRepository,
        CreateResourceCommandMapper createResourceCommandMapper
    ) {
        this.createResourceCommandRepository = createResourceCommandRepository;
        this.createResourceCommandMapper = createResourceCommandMapper;
    }

    /**
     * Save a createResourceCommand.
     *
     * @param createResourceCommandDTO the entity to save.
     * @return the persisted entity.
     */
    public CreateResourceCommandDTO save(CreateResourceCommandDTO createResourceCommandDTO) {
        log.debug("Request to save CreateResourceCommand : {}", createResourceCommandDTO);
        CreateResourceCommand createResourceCommand = createResourceCommandMapper.toEntity(createResourceCommandDTO);
        createResourceCommand = createResourceCommandRepository.save(createResourceCommand);
        return createResourceCommandMapper.toDto(createResourceCommand);
    }

    /**
     * Update a createResourceCommand.
     *
     * @param createResourceCommandDTO the entity to save.
     * @return the persisted entity.
     */
    public CreateResourceCommandDTO update(CreateResourceCommandDTO createResourceCommandDTO) {
        log.debug("Request to update CreateResourceCommand : {}", createResourceCommandDTO);
        CreateResourceCommand createResourceCommand = createResourceCommandMapper.toEntity(createResourceCommandDTO);
        createResourceCommand = createResourceCommandRepository.save(createResourceCommand);
        return createResourceCommandMapper.toDto(createResourceCommand);
    }

    /**
     * Partially update a createResourceCommand.
     *
     * @param createResourceCommandDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CreateResourceCommandDTO> partialUpdate(CreateResourceCommandDTO createResourceCommandDTO) {
        log.debug("Request to partially update CreateResourceCommand : {}", createResourceCommandDTO);

        return createResourceCommandRepository
            .findById(createResourceCommandDTO.getId())
            .map(existingCreateResourceCommand -> {
                createResourceCommandMapper.partialUpdate(existingCreateResourceCommand, createResourceCommandDTO);

                return existingCreateResourceCommand;
            })
            .map(createResourceCommandRepository::save)
            .map(createResourceCommandMapper::toDto);
    }

    /**
     * Get all the createResourceCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CreateResourceCommandDTO> findAll() {
        log.debug("Request to get all CreateResourceCommands");
        return createResourceCommandRepository
            .findAll()
            .stream()
            .map(createResourceCommandMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one createResourceCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CreateResourceCommandDTO> findOne(Long id) {
        log.debug("Request to get CreateResourceCommand : {}", id);
        return createResourceCommandRepository.findById(id).map(createResourceCommandMapper::toDto);
    }

    /**
     * Delete the createResourceCommand by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CreateResourceCommand : {}", id);
        createResourceCommandRepository.deleteById(id);
    }
}
