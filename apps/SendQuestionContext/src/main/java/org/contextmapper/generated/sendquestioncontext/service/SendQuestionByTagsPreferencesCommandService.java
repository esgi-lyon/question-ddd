package org.contextmapper.generated.sendquestioncontext.service;

import java.util.List;
import java.util.Optional;
import org.contextmapper.generated.sendquestioncontext.domain.SendQuestionByTagsPreferencesCommand;
import org.contextmapper.generated.sendquestioncontext.repository.SendQuestionByTagsPreferencesCommandRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public SendQuestionByTagsPreferencesCommand save(SendQuestionByTagsPreferencesCommand sendQuestionByTagsPreferencesCommand) {
        log.debug("Request to save SendQuestionByTagsPreferencesCommand : {}", sendQuestionByTagsPreferencesCommand);
        return sendQuestionByTagsPreferencesCommandRepository.save(sendQuestionByTagsPreferencesCommand);
    }

    /**
     * Update a sendQuestionByTagsPreferencesCommand.
     *
     * @param sendQuestionByTagsPreferencesCommand the entity to save.
     * @return the persisted entity.
     */
    public SendQuestionByTagsPreferencesCommand update(SendQuestionByTagsPreferencesCommand sendQuestionByTagsPreferencesCommand) {
        log.debug("Request to update SendQuestionByTagsPreferencesCommand : {}", sendQuestionByTagsPreferencesCommand);
        // no save call needed as we have no fields that can be updated
        return sendQuestionByTagsPreferencesCommand;
    }

    /**
     * Partially update a sendQuestionByTagsPreferencesCommand.
     *
     * @param sendQuestionByTagsPreferencesCommand the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<SendQuestionByTagsPreferencesCommand> partialUpdate(
        SendQuestionByTagsPreferencesCommand sendQuestionByTagsPreferencesCommand
    ) {
        log.debug("Request to partially update SendQuestionByTagsPreferencesCommand : {}", sendQuestionByTagsPreferencesCommand);

        return sendQuestionByTagsPreferencesCommandRepository
            .findById(sendQuestionByTagsPreferencesCommand.getId())
            .map(existingSendQuestionByTagsPreferencesCommand -> {
                return existingSendQuestionByTagsPreferencesCommand;
            })// .map(sendQuestionByTagsPreferencesCommandRepository::save)
        ;
    }

    /**
     * Get all the sendQuestionByTagsPreferencesCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<SendQuestionByTagsPreferencesCommand> findAll() {
        log.debug("Request to get all SendQuestionByTagsPreferencesCommands");
        return sendQuestionByTagsPreferencesCommandRepository.findAll();
    }

    /**
     * Get one sendQuestionByTagsPreferencesCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SendQuestionByTagsPreferencesCommand> findOne(Long id) {
        log.debug("Request to get SendQuestionByTagsPreferencesCommand : {}", id);
        return sendQuestionByTagsPreferencesCommandRepository.findById(id);
    }

    /**
     * Delete the sendQuestionByTagsPreferencesCommand by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete SendQuestionByTagsPreferencesCommand : {}", id);
        sendQuestionByTagsPreferencesCommandRepository.deleteById(id);
    }
}
