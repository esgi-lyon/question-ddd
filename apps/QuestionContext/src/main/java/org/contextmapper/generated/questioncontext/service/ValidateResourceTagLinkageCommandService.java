package org.contextmapper.generated.questioncontext.service;

import java.util.List;
import java.util.Optional;
import org.contextmapper.generated.questioncontext.domain.ValidateResourceTagLinkageCommand;
import org.contextmapper.generated.questioncontext.repository.ValidateResourceTagLinkageCommandRepository;
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

    public ValidateResourceTagLinkageCommandService(
        ValidateResourceTagLinkageCommandRepository validateResourceTagLinkageCommandRepository
    ) {
        this.validateResourceTagLinkageCommandRepository = validateResourceTagLinkageCommandRepository;
    }

    /**
     * Save a validateResourceTagLinkageCommand.
     *
     * @param validateResourceTagLinkageCommand the entity to save.
     * @return the persisted entity.
     */
    public ValidateResourceTagLinkageCommand save(ValidateResourceTagLinkageCommand validateResourceTagLinkageCommand) {
        log.debug("Request to save ValidateResourceTagLinkageCommand : {}", validateResourceTagLinkageCommand);
        return validateResourceTagLinkageCommandRepository.save(validateResourceTagLinkageCommand);
    }

    /**
     * Update a validateResourceTagLinkageCommand.
     *
     * @param validateResourceTagLinkageCommand the entity to save.
     * @return the persisted entity.
     */
    public ValidateResourceTagLinkageCommand update(ValidateResourceTagLinkageCommand validateResourceTagLinkageCommand) {
        log.debug("Request to update ValidateResourceTagLinkageCommand : {}", validateResourceTagLinkageCommand);
        // no save call needed as we have no fields that can be updated
        return validateResourceTagLinkageCommand;
    }

    /**
     * Partially update a validateResourceTagLinkageCommand.
     *
     * @param validateResourceTagLinkageCommand the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ValidateResourceTagLinkageCommand> partialUpdate(ValidateResourceTagLinkageCommand validateResourceTagLinkageCommand) {
        log.debug("Request to partially update ValidateResourceTagLinkageCommand : {}", validateResourceTagLinkageCommand);

        return validateResourceTagLinkageCommandRepository
            .findById(validateResourceTagLinkageCommand.getId())
            .map(existingValidateResourceTagLinkageCommand -> {
                return existingValidateResourceTagLinkageCommand;
            })// .map(validateResourceTagLinkageCommandRepository::save)
        ;
    }

    /**
     * Get all the validateResourceTagLinkageCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ValidateResourceTagLinkageCommand> findAll() {
        log.debug("Request to get all ValidateResourceTagLinkageCommands");
        return validateResourceTagLinkageCommandRepository.findAll();
    }

    /**
     * Get one validateResourceTagLinkageCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ValidateResourceTagLinkageCommand> findOne(Long id) {
        log.debug("Request to get ValidateResourceTagLinkageCommand : {}", id);
        return validateResourceTagLinkageCommandRepository.findById(id);
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
