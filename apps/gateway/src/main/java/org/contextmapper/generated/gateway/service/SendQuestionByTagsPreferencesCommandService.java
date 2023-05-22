package org.contextmapper.generated.gateway.service;

import java.util.List;
import org.contextmapper.generated.gateway.domain.SendQuestionByTagsPreferencesCommand;
import org.contextmapper.generated.gateway.repository.SendQuestionByTagsPreferencesCommandRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link SendQuestionByTagsPreferencesCommand}.
 */
@Service
@Transactional
public class SendQuestionByTagsPreferencesCommandService {

    private final Logger log = LoggerFactory.getLogger(SendQuestionByTagsPreferencesCommandService.class);

    private final SendQuestionByTagsPreferencesCommandRepository sendQuestionByTagsPreferencesCommandRepository;

    public SendQuestionByTagsPreferencesCommandService(
        SendQuestionByTagsPreferencesCommandRepository sendQuestionByTagsPreferencesCommandRepository
    ) {
        this.sendQuestionByTagsPreferencesCommandRepository = sendQuestionByTagsPreferencesCommandRepository;
    }

    /**
     * Save a sendQuestionByTagsPreferencesCommand.
     *
     * @param sendQuestionByTagsPreferencesCommand the entity to save.
     * @return the persisted entity.
     */
    public Mono<SendQuestionByTagsPreferencesCommand> save(SendQuestionByTagsPreferencesCommand sendQuestionByTagsPreferencesCommand) {
        log.debug("Request to save SendQuestionByTagsPreferencesCommand : {}", sendQuestionByTagsPreferencesCommand);
        return sendQuestionByTagsPreferencesCommandRepository.save(sendQuestionByTagsPreferencesCommand);
    }

    /**
     * Update a sendQuestionByTagsPreferencesCommand.
     *
     * @param sendQuestionByTagsPreferencesCommand the entity to save.
     * @return the persisted entity.
     */
    public Mono<SendQuestionByTagsPreferencesCommand> update(SendQuestionByTagsPreferencesCommand sendQuestionByTagsPreferencesCommand) {
        log.debug("Request to update SendQuestionByTagsPreferencesCommand : {}", sendQuestionByTagsPreferencesCommand);
        return sendQuestionByTagsPreferencesCommandRepository.save(sendQuestionByTagsPreferencesCommand);
    }

    /**
     * Partially update a sendQuestionByTagsPreferencesCommand.
     *
     * @param sendQuestionByTagsPreferencesCommand the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<SendQuestionByTagsPreferencesCommand> partialUpdate(
        SendQuestionByTagsPreferencesCommand sendQuestionByTagsPreferencesCommand
    ) {
        log.debug("Request to partially update SendQuestionByTagsPreferencesCommand : {}", sendQuestionByTagsPreferencesCommand);

        return sendQuestionByTagsPreferencesCommandRepository
            .findById(sendQuestionByTagsPreferencesCommand.getId())
            .map(existingSendQuestionByTagsPreferencesCommand -> {
                return existingSendQuestionByTagsPreferencesCommand;
            })
            .flatMap(sendQuestionByTagsPreferencesCommandRepository::save);
    }

    /**
     * Get all the sendQuestionByTagsPreferencesCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<SendQuestionByTagsPreferencesCommand> findAll() {
        log.debug("Request to get all SendQuestionByTagsPreferencesCommands");
        return sendQuestionByTagsPreferencesCommandRepository.findAll();
    }

    /**
     * Returns the number of sendQuestionByTagsPreferencesCommands available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return sendQuestionByTagsPreferencesCommandRepository.count();
    }

    /**
     * Get one sendQuestionByTagsPreferencesCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<SendQuestionByTagsPreferencesCommand> findOne(Long id) {
        log.debug("Request to get SendQuestionByTagsPreferencesCommand : {}", id);
        return sendQuestionByTagsPreferencesCommandRepository.findById(id);
    }

    /**
     * Delete the sendQuestionByTagsPreferencesCommand by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete SendQuestionByTagsPreferencesCommand : {}", id);
        return sendQuestionByTagsPreferencesCommandRepository.deleteById(id);
    }
}
