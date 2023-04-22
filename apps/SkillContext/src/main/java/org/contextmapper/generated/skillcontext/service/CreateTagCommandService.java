package org.contextmapper.generated.skillcontext.service;

import java.util.List;
import java.util.Optional;
import org.contextmapper.generated.skillcontext.domain.CreateTagCommand;
import org.contextmapper.generated.skillcontext.repository.CreateTagCommandRepository;
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

    public CreateTagCommandService(CreateTagCommandRepository createTagCommandRepository) {
        this.createTagCommandRepository = createTagCommandRepository;
    }

    /**
     * Save a createTagCommand.
     *
     * @param createTagCommand the entity to save.
     * @return the persisted entity.
     */
    public CreateTagCommand save(CreateTagCommand createTagCommand) {
        log.debug("Request to save CreateTagCommand : {}", createTagCommand);
        return createTagCommandRepository.save(createTagCommand);
    }

    /**
     * Update a createTagCommand.
     *
     * @param createTagCommand the entity to save.
     * @return the persisted entity.
     */
    public CreateTagCommand update(CreateTagCommand createTagCommand) {
        log.debug("Request to update CreateTagCommand : {}", createTagCommand);
        // no save call needed as we have no fields that can be updated
        return createTagCommand;
    }

    /**
     * Partially update a createTagCommand.
     *
     * @param createTagCommand the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CreateTagCommand> partialUpdate(CreateTagCommand createTagCommand) {
        log.debug("Request to partially update CreateTagCommand : {}", createTagCommand);

        return createTagCommandRepository
            .findById(createTagCommand.getId())
            .map(existingCreateTagCommand -> {
                return existingCreateTagCommand;
            })// .map(createTagCommandRepository::save)
        ;
    }

    /**
     * Get all the createTagCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CreateTagCommand> findAll() {
        log.debug("Request to get all CreateTagCommands");
        return createTagCommandRepository.findAll();
    }

    /**
     * Get one createTagCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CreateTagCommand> findOne(Long id) {
        log.debug("Request to get CreateTagCommand : {}", id);
        return createTagCommandRepository.findById(id);
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
