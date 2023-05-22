package org.contextmapper.generated.gateway.service;

import java.util.List;
import org.contextmapper.generated.gateway.domain.CreateTagCommand;
import org.contextmapper.generated.gateway.repository.CreateTagCommandRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    public Mono<CreateTagCommand> save(CreateTagCommand createTagCommand) {
        log.debug("Request to save CreateTagCommand : {}", createTagCommand);
        return createTagCommandRepository.save(createTagCommand);
    }

    /**
     * Update a createTagCommand.
     *
     * @param createTagCommand the entity to save.
     * @return the persisted entity.
     */
    public Mono<CreateTagCommand> update(CreateTagCommand createTagCommand) {
        log.debug("Request to update CreateTagCommand : {}", createTagCommand);
        return createTagCommandRepository.save(createTagCommand);
    }

    /**
     * Partially update a createTagCommand.
     *
     * @param createTagCommand the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<CreateTagCommand> partialUpdate(CreateTagCommand createTagCommand) {
        log.debug("Request to partially update CreateTagCommand : {}", createTagCommand);

        return createTagCommandRepository
            .findById(createTagCommand.getId())
            .map(existingCreateTagCommand -> {
                return existingCreateTagCommand;
            })
            .flatMap(createTagCommandRepository::save);
    }

    /**
     * Get all the createTagCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<CreateTagCommand> findAll() {
        log.debug("Request to get all CreateTagCommands");
        return createTagCommandRepository.findAll();
    }

    /**
     * Returns the number of createTagCommands available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return createTagCommandRepository.count();
    }

    /**
     * Get one createTagCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<CreateTagCommand> findOne(Long id) {
        log.debug("Request to get CreateTagCommand : {}", id);
        return createTagCommandRepository.findById(id);
    }

    /**
     * Delete the createTagCommand by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete CreateTagCommand : {}", id);
        return createTagCommandRepository.deleteById(id);
    }
}
