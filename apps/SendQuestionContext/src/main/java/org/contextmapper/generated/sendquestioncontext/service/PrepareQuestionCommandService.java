package org.contextmapper.generated.sendquestioncontext.service;

import java.util.List;
import java.util.Optional;
import org.contextmapper.generated.sendquestioncontext.domain.PrepareQuestionCommand;
import org.contextmapper.generated.sendquestioncontext.repository.PrepareQuestionCommandRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link PrepareQuestionCommand}.
 */
@Service
@Transactional
public class PrepareQuestionCommandService {

    private final Logger log = LoggerFactory.getLogger(PrepareQuestionCommandService.class);

    private final PrepareQuestionCommandRepository prepareQuestionCommandRepository;

    public PrepareQuestionCommandService(PrepareQuestionCommandRepository prepareQuestionCommandRepository) {
        this.prepareQuestionCommandRepository = prepareQuestionCommandRepository;
    }

    /**
     * Save a prepareQuestionCommand.
     *
     * @param prepareQuestionCommand the entity to save.
     * @return the persisted entity.
     */
    public PrepareQuestionCommand save(PrepareQuestionCommand prepareQuestionCommand) {
        log.debug("Request to save PrepareQuestionCommand : {}", prepareQuestionCommand);
        return prepareQuestionCommandRepository.save(prepareQuestionCommand);
    }

    /**
     * Update a prepareQuestionCommand.
     *
     * @param prepareQuestionCommand the entity to save.
     * @return the persisted entity.
     */
    public PrepareQuestionCommand update(PrepareQuestionCommand prepareQuestionCommand) {
        log.debug("Request to update PrepareQuestionCommand : {}", prepareQuestionCommand);
        return prepareQuestionCommandRepository.save(prepareQuestionCommand);
    }

    /**
     * Partially update a prepareQuestionCommand.
     *
     * @param prepareQuestionCommand the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<PrepareQuestionCommand> partialUpdate(PrepareQuestionCommand prepareQuestionCommand) {
        log.debug("Request to partially update PrepareQuestionCommand : {}", prepareQuestionCommand);

        return prepareQuestionCommandRepository
            .findById(prepareQuestionCommand.getId())
            .map(existingPrepareQuestionCommand -> {
                if (prepareQuestionCommand.getResourceId() != null) {
                    existingPrepareQuestionCommand.setResourceId(prepareQuestionCommand.getResourceId());
                }

                return existingPrepareQuestionCommand;
            })
            .map(prepareQuestionCommandRepository::save);
    }

    /**
     * Get all the prepareQuestionCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PrepareQuestionCommand> findAll() {
        log.debug("Request to get all PrepareQuestionCommands");
        return prepareQuestionCommandRepository.findAll();
    }

    /**
     * Get one prepareQuestionCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PrepareQuestionCommand> findOne(Long id) {
        log.debug("Request to get PrepareQuestionCommand : {}", id);
        return prepareQuestionCommandRepository.findById(id);
    }

    /**
     * Delete the prepareQuestionCommand by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PrepareQuestionCommand : {}", id);
        prepareQuestionCommandRepository.deleteById(id);
    }
}
