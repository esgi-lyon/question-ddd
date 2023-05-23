package org.contextmapper.generated.usermanagementcontext.service;

import java.util.List;
import java.util.Optional;
import org.contextmapper.generated.usermanagementcontext.domain.ValidateUserCommand;
import org.contextmapper.generated.usermanagementcontext.repository.ValidateUserCommandRepository;
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

    public ValidateUserCommandService(ValidateUserCommandRepository validateUserCommandRepository) {
        this.validateUserCommandRepository = validateUserCommandRepository;
    }

    /**
     * Save a validateUserCommand.
     *
     * @param validateUserCommand the entity to save.
     * @return the persisted entity.
     */
    public ValidateUserCommand save(ValidateUserCommand validateUserCommand) {
        log.debug("Request to save ValidateUserCommand : {}", validateUserCommand);
        return validateUserCommandRepository.save(validateUserCommand);
    }

    /**
     * Update a validateUserCommand.
     *
     * @param validateUserCommand the entity to save.
     * @return the persisted entity.
     */
    public ValidateUserCommand update(ValidateUserCommand validateUserCommand) {
        log.debug("Request to update ValidateUserCommand : {}", validateUserCommand);
        return validateUserCommandRepository.save(validateUserCommand);
    }

    /**
     * Partially update a validateUserCommand.
     *
     * @param validateUserCommand the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ValidateUserCommand> partialUpdate(ValidateUserCommand validateUserCommand) {
        log.debug("Request to partially update ValidateUserCommand : {}", validateUserCommand);

        return validateUserCommandRepository
            .findById(validateUserCommand.getId())
            .map(existingValidateUserCommand -> {
                return existingValidateUserCommand;
            })
            .map(validateUserCommandRepository::save);
    }

    /**
     * Get all the validateUserCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ValidateUserCommand> findAll() {
        log.debug("Request to get all ValidateUserCommands");
        return validateUserCommandRepository.findAll();
    }

    /**
     * Get one validateUserCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ValidateUserCommand> findOne(Long id) {
        log.debug("Request to get ValidateUserCommand : {}", id);
        return validateUserCommandRepository.findById(id);
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
