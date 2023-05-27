package org.contextmapper.generated.usermanagementcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.usermanagementcontext.domain.ValidateUserCommand;
import org.contextmapper.generated.usermanagementcontext.repository.ValidateUserCommandRepository;
import org.contextmapper.generated.usermanagementcontext.service.dto.ValidateUserCommandDTO;
import org.contextmapper.generated.usermanagementcontext.service.mapper.ValidateUserCommandMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ValidateUserCommand}.
 */
@Service
@Transactional
public class ValidateUserCommandService {

    private final Logger log = LoggerFactory.getLogger(ValidateUserCommandService.class);

    private final ValidateUserCommandRepository validateUserCommandRepository;

    private final ValidateUserCommandMapper validateUserCommandMapper;

    public ValidateUserCommandService(
        ValidateUserCommandRepository validateUserCommandRepository,
        ValidateUserCommandMapper validateUserCommandMapper
    ) {
        this.validateUserCommandRepository = validateUserCommandRepository;
        this.validateUserCommandMapper = validateUserCommandMapper;
    }

    /**
     * Save a validateUserCommand.
     *
     * @param validateUserCommandDTO the entity to save.
     * @return the persisted entity.
     */
    public ValidateUserCommandDTO save(ValidateUserCommandDTO validateUserCommandDTO) {
        log.debug("Request to save ValidateUserCommand : {}", validateUserCommandDTO);
        ValidateUserCommand validateUserCommand = validateUserCommandMapper.toEntity(validateUserCommandDTO);
        validateUserCommand = validateUserCommandRepository.save(validateUserCommand);
        return validateUserCommandMapper.toDto(validateUserCommand);
    }

    /**
     * Update a validateUserCommand.
     *
     * @param validateUserCommandDTO the entity to save.
     * @return the persisted entity.
     */
    public ValidateUserCommandDTO update(ValidateUserCommandDTO validateUserCommandDTO) {
        log.debug("Request to update ValidateUserCommand : {}", validateUserCommandDTO);
        ValidateUserCommand validateUserCommand = validateUserCommandMapper.toEntity(validateUserCommandDTO);
        validateUserCommand = validateUserCommandRepository.save(validateUserCommand);
        return validateUserCommandMapper.toDto(validateUserCommand);
    }

    /**
     * Partially update a validateUserCommand.
     *
     * @param validateUserCommandDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ValidateUserCommandDTO> partialUpdate(ValidateUserCommandDTO validateUserCommandDTO) {
        log.debug("Request to partially update ValidateUserCommand : {}", validateUserCommandDTO);

        return validateUserCommandRepository
            .findById(validateUserCommandDTO.getId())
            .map(existingValidateUserCommand -> {
                validateUserCommandMapper.partialUpdate(existingValidateUserCommand, validateUserCommandDTO);

                return existingValidateUserCommand;
            })
            .map(validateUserCommandRepository::save)
            .map(validateUserCommandMapper::toDto);
    }

    /**
     * Get all the validateUserCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ValidateUserCommandDTO> findAll() {
        log.debug("Request to get all ValidateUserCommands");
        return validateUserCommandRepository
            .findAll()
            .stream()
            .map(validateUserCommandMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one validateUserCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ValidateUserCommandDTO> findOne(Long id) {
        log.debug("Request to get ValidateUserCommand : {}", id);
        return validateUserCommandRepository.findById(id).map(validateUserCommandMapper::toDto);
    }

    /**
     * Delete the validateUserCommand by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ValidateUserCommand : {}", id);
        validateUserCommandRepository.deleteById(id);
    }
}
