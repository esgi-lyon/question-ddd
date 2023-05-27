package org.contextmapper.generated.questioncontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.questioncontext.domain.ValidateResourceTagLinkageCommand;
import org.contextmapper.generated.questioncontext.repository.ValidateResourceTagLinkageCommandRepository;
import org.contextmapper.generated.questioncontext.service.dto.ValidateResourceTagLinkageCommandDTO;
import org.contextmapper.generated.questioncontext.service.mapper.ValidateResourceTagLinkageCommandMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ValidateResourceTagLinkageCommand}.
 */
@Service
@Transactional
public class ValidateResourceTagLinkageCommandService {

    private final Logger log = LoggerFactory.getLogger(ValidateResourceTagLinkageCommandService.class);

    private final ValidateResourceTagLinkageCommandRepository validateResourceTagLinkageCommandRepository;

    private final ValidateResourceTagLinkageCommandMapper validateResourceTagLinkageCommandMapper;

    public ValidateResourceTagLinkageCommandService(
        ValidateResourceTagLinkageCommandRepository validateResourceTagLinkageCommandRepository,
        ValidateResourceTagLinkageCommandMapper validateResourceTagLinkageCommandMapper
    ) {
        this.validateResourceTagLinkageCommandRepository = validateResourceTagLinkageCommandRepository;
        this.validateResourceTagLinkageCommandMapper = validateResourceTagLinkageCommandMapper;
    }

    /**
     * Save a validateResourceTagLinkageCommand.
     *
     * @param validateResourceTagLinkageCommandDTO the entity to save.
     * @return the persisted entity.
     */
    public ValidateResourceTagLinkageCommandDTO save(ValidateResourceTagLinkageCommandDTO validateResourceTagLinkageCommandDTO) {
        log.debug("Request to save ValidateResourceTagLinkageCommand : {}", validateResourceTagLinkageCommandDTO);
        ValidateResourceTagLinkageCommand validateResourceTagLinkageCommand = validateResourceTagLinkageCommandMapper.toEntity(
            validateResourceTagLinkageCommandDTO
        );
        validateResourceTagLinkageCommand = validateResourceTagLinkageCommandRepository.save(validateResourceTagLinkageCommand);
        return validateResourceTagLinkageCommandMapper.toDto(validateResourceTagLinkageCommand);
    }

    /**
     * Update a validateResourceTagLinkageCommand.
     *
     * @param validateResourceTagLinkageCommandDTO the entity to save.
     * @return the persisted entity.
     */
    public ValidateResourceTagLinkageCommandDTO update(ValidateResourceTagLinkageCommandDTO validateResourceTagLinkageCommandDTO) {
        log.debug("Request to update ValidateResourceTagLinkageCommand : {}", validateResourceTagLinkageCommandDTO);
        ValidateResourceTagLinkageCommand validateResourceTagLinkageCommand = validateResourceTagLinkageCommandMapper.toEntity(
            validateResourceTagLinkageCommandDTO
        );
        validateResourceTagLinkageCommand = validateResourceTagLinkageCommandRepository.save(validateResourceTagLinkageCommand);
        return validateResourceTagLinkageCommandMapper.toDto(validateResourceTagLinkageCommand);
    }

    /**
     * Partially update a validateResourceTagLinkageCommand.
     *
     * @param validateResourceTagLinkageCommandDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ValidateResourceTagLinkageCommandDTO> partialUpdate(
        ValidateResourceTagLinkageCommandDTO validateResourceTagLinkageCommandDTO
    ) {
        log.debug("Request to partially update ValidateResourceTagLinkageCommand : {}", validateResourceTagLinkageCommandDTO);

        return validateResourceTagLinkageCommandRepository
            .findById(validateResourceTagLinkageCommandDTO.getId())
            .map(existingValidateResourceTagLinkageCommand -> {
                validateResourceTagLinkageCommandMapper.partialUpdate(
                    existingValidateResourceTagLinkageCommand,
                    validateResourceTagLinkageCommandDTO
                );

                return existingValidateResourceTagLinkageCommand;
            })
            .map(validateResourceTagLinkageCommandRepository::save)
            .map(validateResourceTagLinkageCommandMapper::toDto);
    }

    /**
     * Get all the validateResourceTagLinkageCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ValidateResourceTagLinkageCommandDTO> findAll() {
        log.debug("Request to get all ValidateResourceTagLinkageCommands");
        return validateResourceTagLinkageCommandRepository
            .findAll()
            .stream()
            .map(validateResourceTagLinkageCommandMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one validateResourceTagLinkageCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ValidateResourceTagLinkageCommandDTO> findOne(Long id) {
        log.debug("Request to get ValidateResourceTagLinkageCommand : {}", id);
        return validateResourceTagLinkageCommandRepository.findById(id).map(validateResourceTagLinkageCommandMapper::toDto);
    }

    /**
     * Delete the validateResourceTagLinkageCommand by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ValidateResourceTagLinkageCommand : {}", id);
        validateResourceTagLinkageCommandRepository.deleteById(id);
    }
}
