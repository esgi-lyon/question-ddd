package org.contextmapper.generated.gateway.service;

import java.util.List;
import org.contextmapper.generated.gateway.domain.CreateEvaluationCommand;
import org.contextmapper.generated.gateway.repository.CreateEvaluationCommandRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link CreateEvaluationCommand}.
 */
@Service
@Transactional
public class CreateEvaluationCommandService {

    private final Logger log = LoggerFactory.getLogger(CreateEvaluationCommandService.class);

    private final CreateEvaluationCommandRepository createEvaluationCommandRepository;

    public CreateEvaluationCommandService(CreateEvaluationCommandRepository createEvaluationCommandRepository) {
        this.createEvaluationCommandRepository = createEvaluationCommandRepository;
    }

    /**
     * Save a createEvaluationCommand.
     *
     * @param createEvaluationCommand the entity to save.
     * @return the persisted entity.
     */
    public Mono<CreateEvaluationCommand> save(CreateEvaluationCommand createEvaluationCommand) {
        log.debug("Request to save CreateEvaluationCommand : {}", createEvaluationCommand);
        return createEvaluationCommandRepository.save(createEvaluationCommand);
    }

    /**
     * Update a createEvaluationCommand.
     *
     * @param createEvaluationCommand the entity to save.
     * @return the persisted entity.
     */
    public Mono<CreateEvaluationCommand> update(CreateEvaluationCommand createEvaluationCommand) {
        log.debug("Request to update CreateEvaluationCommand : {}", createEvaluationCommand);
        return createEvaluationCommandRepository.save(createEvaluationCommand);
    }

    /**
     * Partially update a createEvaluationCommand.
     *
     * @param createEvaluationCommand the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<CreateEvaluationCommand> partialUpdate(CreateEvaluationCommand createEvaluationCommand) {
        log.debug("Request to partially update CreateEvaluationCommand : {}", createEvaluationCommand);

        return createEvaluationCommandRepository
            .findById(createEvaluationCommand.getId())
            .map(existingCreateEvaluationCommand -> {
                return existingCreateEvaluationCommand;
            })
            .flatMap(createEvaluationCommandRepository::save);
    }

    /**
     * Get all the createEvaluationCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<CreateEvaluationCommand> findAll() {
        log.debug("Request to get all CreateEvaluationCommands");
        return createEvaluationCommandRepository.findAll();
    }

    /**
     * Returns the number of createEvaluationCommands available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return createEvaluationCommandRepository.count();
    }

    /**
     * Get one createEvaluationCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<CreateEvaluationCommand> findOne(Long id) {
        log.debug("Request to get CreateEvaluationCommand : {}", id);
        return createEvaluationCommandRepository.findById(id);
    }

    /**
     * Delete the createEvaluationCommand by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete CreateEvaluationCommand : {}", id);
        return createEvaluationCommandRepository.deleteById(id);
    }
}
