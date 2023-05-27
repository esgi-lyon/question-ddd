package org.contextmapper.generated.usermanagementcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.usermanagementcontext.domain.RegisterCommand;
import org.contextmapper.generated.usermanagementcontext.repository.RegisterCommandRepository;
import org.contextmapper.generated.usermanagementcontext.service.dto.RegisterCommandDTO;
import org.contextmapper.generated.usermanagementcontext.service.mapper.RegisterCommandMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link RegisterCommand}.
 */
@Service
@Transactional
public class RegisterCommandService {

    private final Logger log = LoggerFactory.getLogger(RegisterCommandService.class);

    private final RegisterCommandRepository registerCommandRepository;

    private final RegisterCommandMapper registerCommandMapper;

    public RegisterCommandService(RegisterCommandRepository registerCommandRepository, RegisterCommandMapper registerCommandMapper) {
        this.registerCommandRepository = registerCommandRepository;
        this.registerCommandMapper = registerCommandMapper;
    }

    /**
     * Save a registerCommand.
     *
     * @param registerCommandDTO the entity to save.
     * @return the persisted entity.
     */
    public RegisterCommandDTO save(RegisterCommandDTO registerCommandDTO) {
        log.debug("Request to save RegisterCommand : {}", registerCommandDTO);
        RegisterCommand registerCommand = registerCommandMapper.toEntity(registerCommandDTO);
        registerCommand = registerCommandRepository.save(registerCommand);
        return registerCommandMapper.toDto(registerCommand);
    }

    /**
     * Update a registerCommand.
     *
     * @param registerCommandDTO the entity to save.
     * @return the persisted entity.
     */
    public RegisterCommandDTO update(RegisterCommandDTO registerCommandDTO) {
        log.debug("Request to update RegisterCommand : {}", registerCommandDTO);
        RegisterCommand registerCommand = registerCommandMapper.toEntity(registerCommandDTO);
        registerCommand = registerCommandRepository.save(registerCommand);
        return registerCommandMapper.toDto(registerCommand);
    }

    /**
     * Partially update a registerCommand.
     *
     * @param registerCommandDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<RegisterCommandDTO> partialUpdate(RegisterCommandDTO registerCommandDTO) {
        log.debug("Request to partially update RegisterCommand : {}", registerCommandDTO);

        return registerCommandRepository
            .findById(registerCommandDTO.getId())
            .map(existingRegisterCommand -> {
                registerCommandMapper.partialUpdate(existingRegisterCommand, registerCommandDTO);

                return existingRegisterCommand;
            })
            .map(registerCommandRepository::save)
            .map(registerCommandMapper::toDto);
    }

    /**
     * Get all the registerCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<RegisterCommandDTO> findAll() {
        log.debug("Request to get all RegisterCommands");
        return registerCommandRepository
            .findAll()
            .stream()
            .map(registerCommandMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one registerCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RegisterCommandDTO> findOne(Long id) {
        log.debug("Request to get RegisterCommand : {}", id);
        return registerCommandRepository.findById(id).map(registerCommandMapper::toDto);
    }

    /**
     * Delete the registerCommand by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete RegisterCommand : {}", id);
        registerCommandRepository.deleteById(id);
    }
}
