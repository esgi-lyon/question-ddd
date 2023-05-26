package org.contextmapper.generated.sendquestioncontext.service;

import java.util.List;
import java.util.Optional;
import org.contextmapper.generated.sendquestioncontext.domain.SendByPreferencesCommand;
import org.contextmapper.generated.sendquestioncontext.repository.SendByPreferencesCommandRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link SendByPreferencesCommand}.
 */
@Service
@Transactional
public class SendByPreferencesCommandService {

    private final Logger log = LoggerFactory.getLogger(SendByPreferencesCommandService.class);

    private final SendByPreferencesCommandRepository sendByPreferencesCommandRepository;

    public SendByPreferencesCommandService(SendByPreferencesCommandRepository sendByPreferencesCommandRepository) {
        this.sendByPreferencesCommandRepository = sendByPreferencesCommandRepository;
    }

    /**
     * Save a sendByPreferencesCommand.
     *
     * @param sendByPreferencesCommand the entity to save.
     * @return the persisted entity.
     */
    public SendByPreferencesCommand save(SendByPreferencesCommand sendByPreferencesCommand) {
        log.debug("Request to save SendByPreferencesCommand : {}", sendByPreferencesCommand);
        return sendByPreferencesCommandRepository.save(sendByPreferencesCommand);
    }

    /**
     * Update a sendByPreferencesCommand.
     *
     * @param sendByPreferencesCommand the entity to save.
     * @return the persisted entity.
     */
    public SendByPreferencesCommand update(SendByPreferencesCommand sendByPreferencesCommand) {
        log.debug("Request to update SendByPreferencesCommand : {}", sendByPreferencesCommand);
        return sendByPreferencesCommandRepository.save(sendByPreferencesCommand);
    }

    /**
     * Partially update a sendByPreferencesCommand.
     *
     * @param sendByPreferencesCommand the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<SendByPreferencesCommand> partialUpdate(SendByPreferencesCommand sendByPreferencesCommand) {
        log.debug("Request to partially update SendByPreferencesCommand : {}", sendByPreferencesCommand);

        return sendByPreferencesCommandRepository
            .findById(sendByPreferencesCommand.getId())
            .map(existingSendByPreferencesCommand -> {
                return existingSendByPreferencesCommand;
            })
            .map(sendByPreferencesCommandRepository::save);
    }

    /**
     * Get all the sendByPreferencesCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<SendByPreferencesCommand> findAll() {
        log.debug("Request to get all SendByPreferencesCommands");
        return sendByPreferencesCommandRepository.findAll();
    }

    /**
     * Get one sendByPreferencesCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SendByPreferencesCommand> findOne(Long id) {
        log.debug("Request to get SendByPreferencesCommand : {}", id);
        return sendByPreferencesCommandRepository.findById(id);
    }

    /**
     * Delete the sendByPreferencesCommand by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete SendByPreferencesCommand : {}", id);
        sendByPreferencesCommandRepository.deleteById(id);
    }
}
