package org.contextmapper.generated.gateway.service;

import java.util.List;
import org.contextmapper.generated.gateway.domain.CreateQuestionCommand;
import org.contextmapper.generated.gateway.repository.CreateQuestionCommandRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link CreateQuestionCommand}.
 */
@Service
@Transactional
public class CreateQuestionCommandService {

    private final Logger log = LoggerFactory.getLogger(CreateQuestionCommandService.class);

    private final CreateQuestionCommandRepository createQuestionCommandRepository;

    public CreateQuestionCommandService(CreateQuestionCommandRepository createQuestionCommandRepository) {
        this.createQuestionCommandRepository = createQuestionCommandRepository;
    }

    /**
     * Save a createQuestionCommand.
     *
     * @param createQuestionCommand the entity to save.
     * @return the persisted entity.
     */
    public Mono<CreateQuestionCommand> save(CreateQuestionCommand createQuestionCommand) {
        log.debug("Request to save CreateQuestionCommand : {}", createQuestionCommand);
        return createQuestionCommandRepository.save(createQuestionCommand);
    }

    /**
     * Update a createQuestionCommand.
     *
     * @param createQuestionCommand the entity to save.
     * @return the persisted entity.
     */
    public Mono<CreateQuestionCommand> update(CreateQuestionCommand createQuestionCommand) {
        log.debug("Request to update CreateQuestionCommand : {}", createQuestionCommand);
        return createQuestionCommandRepository.save(createQuestionCommand);
    }

    /**
     * Partially update a createQuestionCommand.
     *
     * @param createQuestionCommand the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<CreateQuestionCommand> partialUpdate(CreateQuestionCommand createQuestionCommand) {
        log.debug("Request to partially update CreateQuestionCommand : {}", createQuestionCommand);

        return createQuestionCommandRepository
            .findById(createQuestionCommand.getId())
            .map(existingCreateQuestionCommand -> {
                return existingCreateQuestionCommand;
            })
            .flatMap(createQuestionCommandRepository::save);
    }

    /**
     * Get all the createQuestionCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<CreateQuestionCommand> findAll() {
        log.debug("Request to get all CreateQuestionCommands");
        return createQuestionCommandRepository.findAll();
    }

    /**
     * Returns the number of createQuestionCommands available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return createQuestionCommandRepository.count();
    }

    /**
     * Get one createQuestionCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<CreateQuestionCommand> findOne(Long id) {
        log.debug("Request to get CreateQuestionCommand : {}", id);
        return createQuestionCommandRepository.findById(id);
    }

    /**
     * Delete the createQuestionCommand by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete CreateQuestionCommand : {}", id);
        return createQuestionCommandRepository.deleteById(id);
    }
}
