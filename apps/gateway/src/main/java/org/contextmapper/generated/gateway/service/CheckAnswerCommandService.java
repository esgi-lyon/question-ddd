package org.contextmapper.generated.gateway.service;

import java.util.List;
import org.contextmapper.generated.gateway.domain.CheckAnswerCommand;
import org.contextmapper.generated.gateway.repository.CheckAnswerCommandRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link CheckAnswerCommand}.
 */
@Service
@Transactional
public class CheckAnswerCommandService {

    private final Logger log = LoggerFactory.getLogger(CheckAnswerCommandService.class);

    private final CheckAnswerCommandRepository checkAnswerCommandRepository;

    public CheckAnswerCommandService(CheckAnswerCommandRepository checkAnswerCommandRepository) {
        this.checkAnswerCommandRepository = checkAnswerCommandRepository;
    }

    /**
     * Save a checkAnswerCommand.
     *
     * @param checkAnswerCommand the entity to save.
     * @return the persisted entity.
     */
    public Mono<CheckAnswerCommand> save(CheckAnswerCommand checkAnswerCommand) {
        log.debug("Request to save CheckAnswerCommand : {}", checkAnswerCommand);
        return checkAnswerCommandRepository.save(checkAnswerCommand);
    }

    /**
     * Update a checkAnswerCommand.
     *
     * @param checkAnswerCommand the entity to save.
     * @return the persisted entity.
     */
    public Mono<CheckAnswerCommand> update(CheckAnswerCommand checkAnswerCommand) {
        log.debug("Request to update CheckAnswerCommand : {}", checkAnswerCommand);
        return checkAnswerCommandRepository.save(checkAnswerCommand);
    }

    /**
     * Partially update a checkAnswerCommand.
     *
     * @param checkAnswerCommand the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<CheckAnswerCommand> partialUpdate(CheckAnswerCommand checkAnswerCommand) {
        log.debug("Request to partially update CheckAnswerCommand : {}", checkAnswerCommand);

        return checkAnswerCommandRepository
            .findById(checkAnswerCommand.getId())
            .map(existingCheckAnswerCommand -> {
                return existingCheckAnswerCommand;
            })
            .flatMap(checkAnswerCommandRepository::save);
    }

    /**
     * Get all the checkAnswerCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<CheckAnswerCommand> findAll() {
        log.debug("Request to get all CheckAnswerCommands");
        return checkAnswerCommandRepository.findAll();
    }

    /**
     * Returns the number of checkAnswerCommands available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return checkAnswerCommandRepository.count();
    }

    /**
     * Get one checkAnswerCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<CheckAnswerCommand> findOne(Long id) {
        log.debug("Request to get CheckAnswerCommand : {}", id);
        return checkAnswerCommandRepository.findById(id);
    }

    /**
     * Delete the checkAnswerCommand by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete CheckAnswerCommand : {}", id);
        return checkAnswerCommandRepository.deleteById(id);
    }
}
