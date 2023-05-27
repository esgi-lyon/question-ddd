package org.contextmapper.generated.skillcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.skillcontext.domain.CreateCategoryCommand;
import org.contextmapper.generated.skillcontext.repository.CreateCategoryCommandRepository;
import org.contextmapper.generated.skillcontext.service.dto.CreateCategoryCommandDTO;
import org.contextmapper.generated.skillcontext.service.mapper.CreateCategoryCommandMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link CreateCategoryCommand}.
 */
@Service
@Transactional
public class CreateCategoryCommandService {

    private final Logger log = LoggerFactory.getLogger(CreateCategoryCommandService.class);

    private final CreateCategoryCommandRepository createCategoryCommandRepository;

    private final CreateCategoryCommandMapper createCategoryCommandMapper;

    public CreateCategoryCommandService(
        CreateCategoryCommandRepository createCategoryCommandRepository,
        CreateCategoryCommandMapper createCategoryCommandMapper
    ) {
        this.createCategoryCommandRepository = createCategoryCommandRepository;
        this.createCategoryCommandMapper = createCategoryCommandMapper;
    }

    /**
     * Save a createCategoryCommand.
     *
     * @param createCategoryCommandDTO the entity to save.
     * @return the persisted entity.
     */
    public CreateCategoryCommandDTO save(CreateCategoryCommandDTO createCategoryCommandDTO) {
        log.debug("Request to save CreateCategoryCommand : {}", createCategoryCommandDTO);
        CreateCategoryCommand createCategoryCommand = createCategoryCommandMapper.toEntity(createCategoryCommandDTO);
        createCategoryCommand = createCategoryCommandRepository.save(createCategoryCommand);
        return createCategoryCommandMapper.toDto(createCategoryCommand);
    }

    /**
     * Update a createCategoryCommand.
     *
     * @param createCategoryCommandDTO the entity to save.
     * @return the persisted entity.
     */
    public CreateCategoryCommandDTO update(CreateCategoryCommandDTO createCategoryCommandDTO) {
        log.debug("Request to update CreateCategoryCommand : {}", createCategoryCommandDTO);
        CreateCategoryCommand createCategoryCommand = createCategoryCommandMapper.toEntity(createCategoryCommandDTO);
        createCategoryCommand = createCategoryCommandRepository.save(createCategoryCommand);
        return createCategoryCommandMapper.toDto(createCategoryCommand);
    }

    /**
     * Partially update a createCategoryCommand.
     *
     * @param createCategoryCommandDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CreateCategoryCommandDTO> partialUpdate(CreateCategoryCommandDTO createCategoryCommandDTO) {
        log.debug("Request to partially update CreateCategoryCommand : {}", createCategoryCommandDTO);

        return createCategoryCommandRepository
            .findById(createCategoryCommandDTO.getId())
            .map(existingCreateCategoryCommand -> {
                createCategoryCommandMapper.partialUpdate(existingCreateCategoryCommand, createCategoryCommandDTO);

                return existingCreateCategoryCommand;
            })
            .map(createCategoryCommandRepository::save)
            .map(createCategoryCommandMapper::toDto);
    }

    /**
     * Get all the createCategoryCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CreateCategoryCommandDTO> findAll() {
        log.debug("Request to get all CreateCategoryCommands");
        return createCategoryCommandRepository
            .findAll()
            .stream()
            .map(createCategoryCommandMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one createCategoryCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CreateCategoryCommandDTO> findOne(Long id) {
        log.debug("Request to get CreateCategoryCommand : {}", id);
        return createCategoryCommandRepository.findById(id).map(createCategoryCommandMapper::toDto);
    }

    /**
     * Delete the createCategoryCommand by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CreateCategoryCommand : {}", id);
        createCategoryCommandRepository.deleteById(id);
    }
}
