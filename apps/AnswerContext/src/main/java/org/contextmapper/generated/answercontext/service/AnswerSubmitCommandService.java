package org.contextmapper.generated.answercontext.service;

import java.util.List;
import java.util.Optional;
import org.contextmapper.generated.answercontext.domain.AnswerSubmitCommand;
import org.contextmapper.generated.answercontext.repository.AnswerSubmitCommandRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public AnswerSubmitCommand save(AnswerSubmitCommand answerSubmitCommand) {
        log.debug("Request to save AnswerSubmitCommand : {}", answerSubmitCommand);
        return answerSubmitCommandRepository.save(answerSubmitCommand);
    }

    /**
     * Update a answerSubmitCommand.
     *
     * @param answerSubmitCommand the entity to save.
     * @return the persisted entity.
     */
    public AnswerSubmitCommand update(AnswerSubmitCommand answerSubmitCommand) {
        log.debug("Request to update AnswerSubmitCommand : {}", answerSubmitCommand);
        return answerSubmitCommandRepository.save(answerSubmitCommand);
    }

    /**
     * Partially update a answerSubmitCommand.
     *
     * @param answerSubmitCommand the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<AnswerSubmitCommand> partialUpdate(AnswerSubmitCommand answerSubmitCommand) {
        log.debug("Request to partially update AnswerSubmitCommand : {}", answerSubmitCommand);

        return answerSubmitCommandRepository
            .findById(answerSubmitCommand.getId())
            .map(existingAnswerSubmitCommand -> {
                return existingAnswerSubmitCommand;
            })
            .map(answerSubmitCommandRepository::save);
    }

    /**
     * Get all the answerSubmitCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AnswerSubmitCommand> findAll() {
        log.debug("Request to get all AnswerSubmitCommands");
        return answerSubmitCommandRepository.findAll();
    }

    /**
     * Get one answerSubmitCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AnswerSubmitCommand> findOne(Long id) {
        log.debug("Request to get AnswerSubmitCommand : {}", id);
        return answerSubmitCommandRepository.findById(id);
    }

    /**
     * Delete the answerSubmitCommand by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AnswerSubmitCommand : {}", id);
        answerSubmitCommandRepository.deleteById(id);
    }
}
