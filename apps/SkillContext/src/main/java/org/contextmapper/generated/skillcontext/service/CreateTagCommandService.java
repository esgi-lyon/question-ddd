package org.contextmapper.generated.skillcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.skillcontext.domain.CreateTagCommand;
import org.contextmapper.generated.skillcontext.repository.CreateTagCommandRepository;
import org.contextmapper.generated.skillcontext.service.dto.CreateTagCommandDTO;
import org.contextmapper.generated.skillcontext.service.mapper.CreateTagCommandMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link CreateTagCommand}.
 */
@Service
@Transactional
public class CreateTagCommandService {

    private final Logger log = LoggerFactory.getLogger(CreateTagCommandService.class);

    private final CreateTagCommandRepository createTagCommandRepository;

    private final CreateTagCommandMapper createTagCommandMapper;

    public CreateTagCommandService(CreateTagCommandRepository createTagCommandRepository, CreateTagCommandMapper createTagCommandMapper) {
        this.createTagCommandRepository = createTagCommandRepository;
        this.createTagCommandMapper = createTagCommandMapper;
    }

    /**
     * Save a createTagCommand.
     *
     * @param createTagCommandDTO the entity to save.
     * @return the persisted entity.
     */
    public CreateTagCommandDTO save(CreateTagCommandDTO createTagCommandDTO) {
        log.debug("Request to save CreateTagCommand : {}", createTagCommandDTO);
        CreateTagCommand createTagCommand = createTagCommandMapper.toEntity(createTagCommandDTO);
        createTagCommand = createTagCommandRepository.save(createTagCommand);
        return createTagCommandMapper.toDto(createTagCommand);
    }

    /**
     * Update a createTagCommand.
     *
     * @param createTagCommandDTO the entity to save.
     * @return the persisted entity.
     */
    public CreateTagCommandDTO update(CreateTagCommandDTO createTagCommandDTO) {
        log.debug("Request to update CreateTagCommand : {}", createTagCommandDTO);
        CreateTagCommand createTagCommand = createTagCommandMapper.toEntity(createTagCommandDTO);
        createTagCommand = createTagCommandRepository.save(createTagCommand);
        return createTagCommandMapper.toDto(createTagCommand);
    }

    /**
     * Partially update a createTagCommand.
     *
     * @param createTagCommandDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CreateTagCommandDTO> partialUpdate(CreateTagCommandDTO createTagCommandDTO) {
        log.debug("Request to partially update CreateTagCommand : {}", createTagCommandDTO);

        return createTagCommandRepository
            .findById(createTagCommandDTO.getId())
            .map(existingCreateTagCommand -> {
                createTagCommandMapper.partialUpdate(existingCreateTagCommand, createTagCommandDTO);

                return existingCreateTagCommand;
            })
            .map(createTagCommandRepository::save)
            .map(createTagCommandMapper::toDto);
    }

    /**
     * Get all the createTagCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CreateTagCommandDTO> findAll() {
        log.debug("Request to get all CreateTagCommands");
        return createTagCommandRepository
            .findAll()
            .stream()
            .map(createTagCommandMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one createTagCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CreateTagCommandDTO> findOne(Long id) {
        log.debug("Request to get CreateTagCommand : {}", id);
        return createTagCommandRepository.findById(id).map(createTagCommandMapper::toDto);
    }

    /**
     * Delete the createTagCommand by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CreateTagCommand : {}", id);
        createTagCommandRepository.deleteById(id);
    }
}
