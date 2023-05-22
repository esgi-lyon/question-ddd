package org.contextmapper.generated.gateway.service;

import java.util.List;
import org.contextmapper.generated.gateway.domain.CreateResourceCommand;
import org.contextmapper.generated.gateway.repository.CreateResourceCommandRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link CreateResourceCommand}.
 */
@Service
@Transactional
public class CreateResourceCommandService {

    private final Logger log = LoggerFactory.getLogger(CreateResourceCommandService.class);

    private final CreateResourceCommandRepository createResourceCommandRepository;

    public CreateResourceCommandService(CreateResourceCommandRepository createResourceCommandRepository) {
        this.createResourceCommandRepository = createResourceCommandRepository;
    }

    /**
     * Save a createResourceCommand.
     *
     * @param createResourceCommand the entity to save.
     * @return the persisted entity.
     */
    public Mono<CreateResourceCommand> save(CreateResourceCommand createResourceCommand) {
        log.debug("Request to save CreateResourceCommand : {}", createResourceCommand);
        return createResourceCommandRepository.save(createResourceCommand);
    }

    /**
     * Update a createResourceCommand.
     *
     * @param createResourceCommand the entity to save.
     * @return the persisted entity.
     */
    public Mono<CreateResourceCommand> update(CreateResourceCommand createResourceCommand) {
        log.debug("Request to update CreateResourceCommand : {}", createResourceCommand);
        return createResourceCommandRepository.save(createResourceCommand);
    }

    /**
     * Partially update a createResourceCommand.
     *
     * @param createResourceCommand the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<CreateResourceCommand> partialUpdate(CreateResourceCommand createResourceCommand) {
        log.debug("Request to partially update CreateResourceCommand : {}", createResourceCommand);

        return createResourceCommandRepository
            .findById(createResourceCommand.getId())
            .map(existingCreateResourceCommand -> {
                return existingCreateResourceCommand;
            })
            .flatMap(createResourceCommandRepository::save);
    }

    /**
     * Get all the createResourceCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<CreateResourceCommand> findAll() {
        log.debug("Request to get all CreateResourceCommands");
        return createResourceCommandRepository.findAll();
    }

    /**
     * Returns the number of createResourceCommands available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return createResourceCommandRepository.count();
    }

    /**
     * Get one createResourceCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<CreateResourceCommand> findOne(Long id) {
        log.debug("Request to get CreateResourceCommand : {}", id);
        return createResourceCommandRepository.findById(id);
    }

    /**
     * Delete the createResourceCommand by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete CreateResourceCommand : {}", id);
        return createResourceCommandRepository.deleteById(id);
    }
}
