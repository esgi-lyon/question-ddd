package org.contextmapper.generated.sendquestioncontext.service;

import java.util.List;
import java.util.Optional;
import org.contextmapper.generated.sendquestioncontext.domain.PrepareQuestionsCommand;
import org.contextmapper.generated.sendquestioncontext.repository.PrepareQuestionsCommandRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link PrepareQuestionsCommand}.
 */
@Service
@Transactional
public class PrepareQuestionsCommandService {

    private final Logger log = LoggerFactory.getLogger(PrepareQuestionsCommandService.class);

    private final PrepareQuestionsCommandRepository prepareQuestionsCommandRepository;

    public PrepareQuestionsCommandService(PrepareQuestionsCommandRepository prepareQuestionsCommandRepository) {
        this.prepareQuestionsCommandRepository = prepareQuestionsCommandRepository;
    }

    /**
     * Save a prepareQuestionsCommand.
     *
     * @param prepareQuestionsCommand the entity to save.
     * @return the persisted entity.
     */
    public PrepareQuestionsCommand save(PrepareQuestionsCommand prepareQuestionsCommand) {
        log.debug("Request to save PrepareQuestionsCommand : {}", prepareQuestionsCommand);
        return prepareQuestionsCommandRepository.save(prepareQuestionsCommand);
    }

    /**
     * Update a prepareQuestionsCommand.
     *
     * @param prepareQuestionsCommand the entity to save.
     * @return the persisted entity.
     */
    public PrepareQuestionsCommand update(PrepareQuestionsCommand prepareQuestionsCommand) {
        log.debug("Request to update PrepareQuestionsCommand : {}", prepareQuestionsCommand);
        return prepareQuestionsCommandRepository.save(prepareQuestionsCommand);
    }

    /**
     * Partially update a prepareQuestionsCommand.
     *
     * @param prepareQuestionsCommand the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<PrepareQuestionsCommand> partialUpdate(PrepareQuestionsCommand prepareQuestionsCommand) {
        log.debug("Request to partially update PrepareQuestionsCommand : {}", prepareQuestionsCommand);

        return prepareQuestionsCommandRepository
            .findById(prepareQuestionsCommand.getId())
            .map(existingPrepareQuestionsCommand -> {
                if (prepareQuestionsCommand.getTagToPrepareQuestions() != null) {
                    existingPrepareQuestionsCommand.setTagToPrepareQuestions(prepareQuestionsCommand.getTagToPrepareQuestions());
                }

                return existingPrepareQuestionsCommand;
            })
            .map(prepareQuestionsCommandRepository::save);
    }

    /**
     * Get all the prepareQuestionsCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PrepareQuestionsCommand> findAll() {
        log.debug("Request to get all PrepareQuestionsCommands");
        return prepareQuestionsCommandRepository.findAll();
    }

    /**
     * Get one prepareQuestionsCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PrepareQuestionsCommand> findOne(Long id) {
        log.debug("Request to get PrepareQuestionsCommand : {}", id);
        return prepareQuestionsCommandRepository.findById(id);
    }

    /**
     * Delete the prepareQuestionsCommand by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PrepareQuestionsCommand : {}", id);
        prepareQuestionsCommandRepository.deleteById(id);
    }
}
