package org.contextmapper.generated.gateway.service;

import java.util.List;
import org.contextmapper.generated.gateway.domain.AnswerSubmitCommand;
import org.contextmapper.generated.gateway.repository.AnswerSubmitCommandRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link AnswerSubmitCommand}.
 */
@Service
@Transactional
public class AnswerSubmitCommandService {

    private final Logger log = LoggerFactory.getLogger(AnswerSubmitCommandService.class);

    private final AnswerSubmitCommandRepository answerSubmitCommandRepository;

    public AnswerSubmitCommandService(AnswerSubmitCommandRepository answerSubmitCommandRepository) {
        this.answerSubmitCommandRepository = answerSubmitCommandRepository;
    }

    /**
     * Save a answerSubmitCommand.
     *
     * @param answerSubmitCommand the entity to save.
     * @return the persisted entity.
     */
    public Mono<AnswerSubmitCommand> save(AnswerSubmitCommand answerSubmitCommand) {
        log.debug("Request to save AnswerSubmitCommand : {}", answerSubmitCommand);
        return answerSubmitCommandRepository.save(answerSubmitCommand);
    }

    /**
     * Update a answerSubmitCommand.
     *
     * @param answerSubmitCommand the entity to save.
     * @return the persisted entity.
     */
    public Mono<AnswerSubmitCommand> update(AnswerSubmitCommand answerSubmitCommand) {
        log.debug("Request to update AnswerSubmitCommand : {}", answerSubmitCommand);
        return answerSubmitCommandRepository.save(answerSubmitCommand);
    }

    /**
     * Partially update a answerSubmitCommand.
     *
     * @param answerSubmitCommand the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<AnswerSubmitCommand> partialUpdate(AnswerSubmitCommand answerSubmitCommand) {
        log.debug("Request to partially update AnswerSubmitCommand : {}", answerSubmitCommand);

        return answerSubmitCommandRepository
            .findById(answerSubmitCommand.getId())
            .map(existingAnswerSubmitCommand -> {
                return existingAnswerSubmitCommand;
            })
            .flatMap(answerSubmitCommandRepository::save);
    }

    /**
     * Get all the answerSubmitCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<AnswerSubmitCommand> findAll() {
        log.debug("Request to get all AnswerSubmitCommands");
        return answerSubmitCommandRepository.findAll();
    }

    /**
     * Returns the number of answerSubmitCommands available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return answerSubmitCommandRepository.count();
    }

    /**
     * Get one answerSubmitCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<AnswerSubmitCommand> findOne(Long id) {
        log.debug("Request to get AnswerSubmitCommand : {}", id);
        return answerSubmitCommandRepository.findById(id);
    }

    /**
     * Delete the answerSubmitCommand by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete AnswerSubmitCommand : {}", id);
        return answerSubmitCommandRepository.deleteById(id);
    }
}
