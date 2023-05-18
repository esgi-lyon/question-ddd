package org.contextmapper.generated.evaluationcontext.service;

import java.util.List;
import java.util.Optional;
import org.contextmapper.generated.evaluationcontext.domain.CreateEvaluationCommand;
import org.contextmapper.generated.evaluationcontext.repository.CreateEvaluationCommandRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link CreateEvaluationCommand}.
 */
@Service
@Transactional
public class CreateEvaluationCommandService {

    private final Logger log = LoggerFactory.getLogger(CreateEvaluationCommandService.class);

    private final CreateEvaluationCommandRepository createEvaluationCommandRepository;

    public CreateEvaluationCommandService(CreateEvaluationCommandRepository createEvaluationCommandRepository) {
        this.createEvaluationCommandRepository = createEvaluationCommandRepository;
    }

    /**
     * Save a createEvaluationCommand.
     *
     * @param createEvaluationCommand the entity to save.
     * @return the persisted entity.
     */
    public CreateEvaluationCommand save(CreateEvaluationCommand createEvaluationCommand) {
        log.debug("Request to save CreateEvaluationCommand : {}", createEvaluationCommand);
        return createEvaluationCommandRepository.save(createEvaluationCommand);
    }

    /**
     * Update a createEvaluationCommand.
     *
     * @param createEvaluationCommand the entity to save.
     * @return the persisted entity.
     */
    public CreateEvaluationCommand update(CreateEvaluationCommand createEvaluationCommand) {
        log.debug("Request to update CreateEvaluationCommand : {}", createEvaluationCommand);
        return createEvaluationCommandRepository.save(createEvaluationCommand);
    }

    /**
     * Partially update a createEvaluationCommand.
     *
     * @param createEvaluationCommand the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CreateEvaluationCommand> partialUpdate(CreateEvaluationCommand createEvaluationCommand) {
        log.debug("Request to partially update CreateEvaluationCommand : {}", createEvaluationCommand);

        return createEvaluationCommandRepository
            .findById(createEvaluationCommand.getId())
            .map(existingCreateEvaluationCommand -> {
                return existingCreateEvaluationCommand;
            })
            .map(createEvaluationCommandRepository::save);
    }

    /**
     * Get all the createEvaluationCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CreateEvaluationCommand> findAll() {
        log.debug("Request to get all CreateEvaluationCommands");
        return createEvaluationCommandRepository.findAll();
    }

    /**
     * Get one createEvaluationCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CreateEvaluationCommand> findOne(Long id) {
        log.debug("Request to get CreateEvaluationCommand : {}", id);
        return createEvaluationCommandRepository.findById(id);
    }

    /**
     * Delete the createEvaluationCommand by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CreateEvaluationCommand : {}", id);
        createEvaluationCommandRepository.deleteById(id);
    }
}
