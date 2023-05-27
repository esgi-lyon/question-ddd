package org.contextmapper.generated.sendquestioncontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.sendquestioncontext.domain.AddPreferencesCommand;
import org.contextmapper.generated.sendquestioncontext.repository.AddPreferencesCommandRepository;
import org.contextmapper.generated.sendquestioncontext.service.dto.AddPreferencesCommandDTO;
import org.contextmapper.generated.sendquestioncontext.service.mapper.AddPreferencesCommandMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link AddPreferencesCommand}.
 */
@Service
@Transactional
public class AddPreferencesCommandService {

    private final Logger log = LoggerFactory.getLogger(AddPreferencesCommandService.class);

    private final AddPreferencesCommandRepository addPreferencesCommandRepository;

    private final AddPreferencesCommandMapper addPreferencesCommandMapper;

    public AddPreferencesCommandService(
        AddPreferencesCommandRepository addPreferencesCommandRepository,
        AddPreferencesCommandMapper addPreferencesCommandMapper
    ) {
        this.addPreferencesCommandRepository = addPreferencesCommandRepository;
        this.addPreferencesCommandMapper = addPreferencesCommandMapper;
    }

    /**
     * Save a addPreferencesCommand.
     *
     * @param addPreferencesCommandDTO the entity to save.
     * @return the persisted entity.
     */
    public AddPreferencesCommandDTO save(AddPreferencesCommandDTO addPreferencesCommandDTO) {
        log.debug("Request to save AddPreferencesCommand : {}", addPreferencesCommandDTO);
        AddPreferencesCommand addPreferencesCommand = addPreferencesCommandMapper.toEntity(addPreferencesCommandDTO);
        addPreferencesCommand = addPreferencesCommandRepository.save(addPreferencesCommand);
        return addPreferencesCommandMapper.toDto(addPreferencesCommand);
    }

    /**
     * Update a addPreferencesCommand.
     *
     * @param addPreferencesCommandDTO the entity to save.
     * @return the persisted entity.
     */
    public AddPreferencesCommandDTO update(AddPreferencesCommandDTO addPreferencesCommandDTO) {
        log.debug("Request to update AddPreferencesCommand : {}", addPreferencesCommandDTO);
        AddPreferencesCommand addPreferencesCommand = addPreferencesCommandMapper.toEntity(addPreferencesCommandDTO);
        addPreferencesCommand = addPreferencesCommandRepository.save(addPreferencesCommand);
        return addPreferencesCommandMapper.toDto(addPreferencesCommand);
    }

    /**
     * Partially update a addPreferencesCommand.
     *
     * @param addPreferencesCommandDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<AddPreferencesCommandDTO> partialUpdate(AddPreferencesCommandDTO addPreferencesCommandDTO) {
        log.debug("Request to partially update AddPreferencesCommand : {}", addPreferencesCommandDTO);

        return addPreferencesCommandRepository
            .findById(addPreferencesCommandDTO.getId())
            .map(existingAddPreferencesCommand -> {
                addPreferencesCommandMapper.partialUpdate(existingAddPreferencesCommand, addPreferencesCommandDTO);

                return existingAddPreferencesCommand;
            })
            .map(addPreferencesCommandRepository::save)
            .map(addPreferencesCommandMapper::toDto);
    }

    /**
     * Get all the addPreferencesCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AddPreferencesCommandDTO> findAll() {
        log.debug("Request to get all AddPreferencesCommands");
        return addPreferencesCommandRepository
            .findAll()
            .stream()
            .map(addPreferencesCommandMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one addPreferencesCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AddPreferencesCommandDTO> findOne(Long id) {
        log.debug("Request to get AddPreferencesCommand : {}", id);
        return addPreferencesCommandRepository.findById(id).map(addPreferencesCommandMapper::toDto);
    }

    /**
     * Delete the addPreferencesCommand by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AddPreferencesCommand : {}", id);
        addPreferencesCommandRepository.deleteById(id);
    }
}
