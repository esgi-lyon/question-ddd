package org.contextmapper.generated.evaluationcontext.service;

import java.util.List;
import java.util.Optional;
import org.contextmapper.generated.evaluationcontext.domain.NotifyNewAnswerCommand;
import org.contextmapper.generated.evaluationcontext.repository.NotifyNewAnswerCommandRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link NotifyNewAnswerCommand}.
 */
@Service
@Transactional
public class NotifyNewAnswerCommandService {

    private final Logger log = LoggerFactory.getLogger(NotifyNewAnswerCommandService.class);

    private final NotifyNewAnswerCommandRepository notifyNewAnswerCommandRepository;

    public NotifyNewAnswerCommandService(NotifyNewAnswerCommandRepository notifyNewAnswerCommandRepository) {
        this.notifyNewAnswerCommandRepository = notifyNewAnswerCommandRepository;
    }

    /**
     * Save a notifyNewAnswerCommand.
     *
     * @param notifyNewAnswerCommand the entity to save.
     * @return the persisted entity.
     */
    public NotifyNewAnswerCommand save(NotifyNewAnswerCommand notifyNewAnswerCommand) {
        log.debug("Request to save NotifyNewAnswerCommand : {}", notifyNewAnswerCommand);
        return notifyNewAnswerCommandRepository.save(notifyNewAnswerCommand);
    }

    /**
     * Update a notifyNewAnswerCommand.
     *
     * @param notifyNewAnswerCommand the entity to save.
     * @return the persisted entity.
     */
    public NotifyNewAnswerCommand update(NotifyNewAnswerCommand notifyNewAnswerCommand) {
        log.debug("Request to update NotifyNewAnswerCommand : {}", notifyNewAnswerCommand);
        return notifyNewAnswerCommandRepository.save(notifyNewAnswerCommand);
    }

    /**
     * Partially update a notifyNewAnswerCommand.
     *
     * @param notifyNewAnswerCommand the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<NotifyNewAnswerCommand> partialUpdate(NotifyNewAnswerCommand notifyNewAnswerCommand) {
        log.debug("Request to partially update NotifyNewAnswerCommand : {}", notifyNewAnswerCommand);

        return notifyNewAnswerCommandRepository
            .findById(notifyNewAnswerCommand.getId())
            .map(existingNotifyNewAnswerCommand -> {
                return existingNotifyNewAnswerCommand;
            })
            .map(notifyNewAnswerCommandRepository::save);
    }

    /**
     * Get all the notifyNewAnswerCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<NotifyNewAnswerCommand> findAll() {
        log.debug("Request to get all NotifyNewAnswerCommands");
        return notifyNewAnswerCommandRepository.findAll();
    }

    /**
     * Get one notifyNewAnswerCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<NotifyNewAnswerCommand> findOne(Long id) {
        log.debug("Request to get NotifyNewAnswerCommand : {}", id);
        return notifyNewAnswerCommandRepository.findById(id);
    }

    /**
     * Delete the notifyNewAnswerCommand by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete NotifyNewAnswerCommand : {}", id);
        notifyNewAnswerCommandRepository.deleteById(id);
    }
}
