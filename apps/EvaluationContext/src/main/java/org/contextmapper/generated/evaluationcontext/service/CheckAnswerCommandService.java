package org.contextmapper.generated.evaluationcontext.service;

import java.util.List;
import java.util.Optional;
import org.contextmapper.generated.evaluationcontext.domain.CheckAnswerCommand;
import org.contextmapper.generated.evaluationcontext.repository.CheckAnswerCommandRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public CheckAnswerCommand save(CheckAnswerCommand checkAnswerCommand) {
        log.debug("Request to save CheckAnswerCommand : {}", checkAnswerCommand);
        return checkAnswerCommandRepository.save(checkAnswerCommand);
    }

    /**
     * Update a checkAnswerCommand.
     *
     * @param checkAnswerCommand the entity to save.
     * @return the persisted entity.
     */
    public CheckAnswerCommand update(CheckAnswerCommand checkAnswerCommand) {
        log.debug("Request to update CheckAnswerCommand : {}", checkAnswerCommand);
        // no save call needed as we have no fields that can be updated
        return checkAnswerCommand;
    }

    /**
     * Partially update a checkAnswerCommand.
     *
     * @param checkAnswerCommand the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CheckAnswerCommand> partialUpdate(CheckAnswerCommand checkAnswerCommand) {
        log.debug("Request to partially update CheckAnswerCommand : {}", checkAnswerCommand);

        return checkAnswerCommandRepository
            .findById(checkAnswerCommand.getId())
            .map(existingCheckAnswerCommand -> {
                return existingCheckAnswerCommand;
            })// .map(checkAnswerCommandRepository::save)
        ;
    }

    /**
     * Get all the checkAnswerCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CheckAnswerCommand> findAll() {
        log.debug("Request to get all CheckAnswerCommands");
        return checkAnswerCommandRepository.findAll();
    }

    /**
     * Get one checkAnswerCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CheckAnswerCommand> findOne(Long id) {
        log.debug("Request to get CheckAnswerCommand : {}", id);
        return checkAnswerCommandRepository.findById(id);
    }

    /**
     * Delete the checkAnswerCommand by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CheckAnswerCommand : {}", id);
        checkAnswerCommandRepository.deleteById(id);
    }
}
