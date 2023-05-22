package org.contextmapper.generated.gateway.service;

import java.util.List;
import org.contextmapper.generated.gateway.domain.CreateCategoryCommand;
import org.contextmapper.generated.gateway.repository.CreateCategoryCommandRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link CreateCategoryCommand}.
 */
@Service
@Transactional
public class CreateCategoryCommandService {

    private final Logger log = LoggerFactory.getLogger(CreateCategoryCommandService.class);

    private final CreateCategoryCommandRepository createCategoryCommandRepository;

    public CreateCategoryCommandService(CreateCategoryCommandRepository createCategoryCommandRepository) {
        this.createCategoryCommandRepository = createCategoryCommandRepository;
    }

    /**
     * Save a createCategoryCommand.
     *
     * @param createCategoryCommand the entity to save.
     * @return the persisted entity.
     */
    public Mono<CreateCategoryCommand> save(CreateCategoryCommand createCategoryCommand) {
        log.debug("Request to save CreateCategoryCommand : {}", createCategoryCommand);
        return createCategoryCommandRepository.save(createCategoryCommand);
    }

    /**
     * Update a createCategoryCommand.
     *
     * @param createCategoryCommand the entity to save.
     * @return the persisted entity.
     */
    public Mono<CreateCategoryCommand> update(CreateCategoryCommand createCategoryCommand) {
        log.debug("Request to update CreateCategoryCommand : {}", createCategoryCommand);
        return createCategoryCommandRepository.save(createCategoryCommand);
    }

    /**
     * Partially update a createCategoryCommand.
     *
     * @param createCategoryCommand the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<CreateCategoryCommand> partialUpdate(CreateCategoryCommand createCategoryCommand) {
        log.debug("Request to partially update CreateCategoryCommand : {}", createCategoryCommand);

        return createCategoryCommandRepository
            .findById(createCategoryCommand.getId())
            .map(existingCreateCategoryCommand -> {
                return existingCreateCategoryCommand;
            })
            .flatMap(createCategoryCommandRepository::save);
    }

    /**
     * Get all the createCategoryCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<CreateCategoryCommand> findAll() {
        log.debug("Request to get all CreateCategoryCommands");
        return createCategoryCommandRepository.findAll();
    }

    /**
     * Returns the number of createCategoryCommands available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return createCategoryCommandRepository.count();
    }

    /**
     * Get one createCategoryCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<CreateCategoryCommand> findOne(Long id) {
        log.debug("Request to get CreateCategoryCommand : {}", id);
        return createCategoryCommandRepository.findById(id);
    }

    /**
     * Delete the createCategoryCommand by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete CreateCategoryCommand : {}", id);
        return createCategoryCommandRepository.deleteById(id);
    }
}
