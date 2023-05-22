package org.contextmapper.generated.gateway.service;

import java.util.List;
import org.contextmapper.generated.gateway.domain.ValidateResourceTagLinkageCommand;
import org.contextmapper.generated.gateway.repository.ValidateResourceTagLinkageCommandRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    public Mono<ValidateResourceTagLinkageCommand> save(ValidateResourceTagLinkageCommand validateResourceTagLinkageCommand) {
        log.debug("Request to save ValidateResourceTagLinkageCommand : {}", validateResourceTagLinkageCommand);
        return validateResourceTagLinkageCommandRepository.save(validateResourceTagLinkageCommand);
    }

    /**
     * Update a validateResourceTagLinkageCommand.
     *
     * @param validateResourceTagLinkageCommand the entity to save.
     * @return the persisted entity.
     */
    public Mono<ValidateResourceTagLinkageCommand> update(ValidateResourceTagLinkageCommand validateResourceTagLinkageCommand) {
        log.debug("Request to update ValidateResourceTagLinkageCommand : {}", validateResourceTagLinkageCommand);
        return validateResourceTagLinkageCommandRepository.save(validateResourceTagLinkageCommand);
    }

    /**
     * Partially update a validateResourceTagLinkageCommand.
     *
     * @param validateResourceTagLinkageCommand the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<ValidateResourceTagLinkageCommand> partialUpdate(ValidateResourceTagLinkageCommand validateResourceTagLinkageCommand) {
        log.debug("Request to partially update ValidateResourceTagLinkageCommand : {}", validateResourceTagLinkageCommand);

        return validateResourceTagLinkageCommandRepository
            .findById(validateResourceTagLinkageCommand.getId())
            .map(existingValidateResourceTagLinkageCommand -> {
                return existingValidateResourceTagLinkageCommand;
            })
            .flatMap(validateResourceTagLinkageCommandRepository::save);
    }

    /**
     * Get all the validateResourceTagLinkageCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<ValidateResourceTagLinkageCommand> findAll() {
        log.debug("Request to get all ValidateResourceTagLinkageCommands");
        return validateResourceTagLinkageCommandRepository.findAll();
    }

    /**
     * Returns the number of validateResourceTagLinkageCommands available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return validateResourceTagLinkageCommandRepository.count();
    }

    /**
     * Get one validateResourceTagLinkageCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<ValidateResourceTagLinkageCommand> findOne(Long id) {
        log.debug("Request to get ValidateResourceTagLinkageCommand : {}", id);
        return validateResourceTagLinkageCommandRepository.findById(id);
    }

    /**
     * Delete the validateResourceTagLinkageCommand by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete ValidateResourceTagLinkageCommand : {}", id);
        return validateResourceTagLinkageCommandRepository.deleteById(id);
    }
}
