package org.contextmapper.generated.sendquestioncontext.service;

import java.util.List;
import java.util.Optional;
import org.contextmapper.generated.sendquestioncontext.domain.CreateQuestionCommand;
import org.contextmapper.generated.sendquestioncontext.repository.CreateQuestionCommandRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link CreateQuestionCommand}.
 */
@Service
@Transactional
public class CreateQuestionCommandService {

    private final Logger log = LoggerFactory.getLogger(CreateQuestionCommandService.class);

    private final CreateQuestionCommandRepository createQuestionCommandRepository;

    public CreateQuestionCommandService(CreateQuestionCommandRepository createQuestionCommandRepository) {
        this.createQuestionCommandRepository = createQuestionCommandRepository;
    }

    /**
     * Save a createQuestionCommand.
     *
     * @param createQuestionCommand the entity to save.
     * @return the persisted entity.
     */
    public CreateQuestionCommand save(CreateQuestionCommand createQuestionCommand) {
        log.debug("Request to save CreateQuestionCommand : {}", createQuestionCommand);
        return createQuestionCommandRepository.save(createQuestionCommand);
    }

    /**
     * Update a createQuestionCommand.
     *
     * @param createQuestionCommand the entity to save.
     * @return the persisted entity.
     */
    public CreateQuestionCommand update(CreateQuestionCommand createQuestionCommand) {
        log.debug("Request to update CreateQuestionCommand : {}", createQuestionCommand);
        // no save call needed as we have no fields that can be updated
        return createQuestionCommand;
    }

    /**
     * Partially update a createQuestionCommand.
     *
     * @param createQuestionCommand the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CreateQuestionCommand> partialUpdate(CreateQuestionCommand createQuestionCommand) {
        log.debug("Request to partially update CreateQuestionCommand : {}", createQuestionCommand);

        return createQuestionCommandRepository
            .findById(createQuestionCommand.getId())
            .map(existingCreateQuestionCommand -> {
                return existingCreateQuestionCommand;
            })// .map(createQuestionCommandRepository::save)
        ;
    }

    /**
     * Get all the createQuestionCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CreateQuestionCommand> findAll() {
        log.debug("Request to get all CreateQuestionCommands");
        return createQuestionCommandRepository.findAll();
    }

    /**
     * Get one createQuestionCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CreateQuestionCommand> findOne(Long id) {
        log.debug("Request to get CreateQuestionCommand : {}", id);
        return createQuestionCommandRepository.findById(id);
    }

    /**
     * Delete the createQuestionCommand by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CreateQuestionCommand : {}", id);
        createQuestionCommandRepository.deleteById(id);
    }
}
