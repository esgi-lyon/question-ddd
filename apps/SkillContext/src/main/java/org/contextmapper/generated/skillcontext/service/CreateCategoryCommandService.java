package org.contextmapper.generated.skillcontext.service;

import java.util.List;
import java.util.Optional;
import org.contextmapper.generated.skillcontext.domain.CreateCategoryCommand;
import org.contextmapper.generated.skillcontext.repository.CreateCategoryCommandRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link CreateCategoryCommand}.
 */
@Service
@Transactional
public class CreateCategoryCommandService {

    private final Logger log = LoggerFactory.getLogger(CreateCategoryCommandService.class);

    private final CreateCategoryCommandRepository createCategoryCommandRepository;

    public CreateCategoryCommandService(CreateCategoryCommandRepository createCategoryCommandRepository) {
        this.createCategoryCommandRepository = createCategoryCommandRepository;
    }

    /**
     * Save a createCategoryCommand.
     *
     * @param createCategoryCommand the entity to save.
     * @return the persisted entity.
     */
    public CreateCategoryCommand save(CreateCategoryCommand createCategoryCommand) {
        log.debug("Request to save CreateCategoryCommand : {}", createCategoryCommand);
        return createCategoryCommandRepository.save(createCategoryCommand);
    }

    /**
     * Update a createCategoryCommand.
     *
     * @param createCategoryCommand the entity to save.
     * @return the persisted entity.
     */
    public CreateCategoryCommand update(CreateCategoryCommand createCategoryCommand) {
        log.debug("Request to update CreateCategoryCommand : {}", createCategoryCommand);
        // no save call needed as we have no fields that can be updated
        return createCategoryCommand;
    }

    /**
     * Partially update a createCategoryCommand.
     *
     * @param createCategoryCommand the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CreateCategoryCommand> partialUpdate(CreateCategoryCommand createCategoryCommand) {
        log.debug("Request to partially update CreateCategoryCommand : {}", createCategoryCommand);

        return createCategoryCommandRepository
            .findById(createCategoryCommand.getId())
            .map(existingCreateCategoryCommand -> {
                return existingCreateCategoryCommand;
            })// .map(createCategoryCommandRepository::save)
        ;
    }

    /**
     * Get all the createCategoryCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CreateCategoryCommand> findAll() {
        log.debug("Request to get all CreateCategoryCommands");
        return createCategoryCommandRepository.findAll();
    }

    /**
     * Get one createCategoryCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CreateCategoryCommand> findOne(Long id) {
        log.debug("Request to get CreateCategoryCommand : {}", id);
        return createCategoryCommandRepository.findById(id);
    }

    /**
     * Delete the createCategoryCommand by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CreateCategoryCommand : {}", id);
        createCategoryCommandRepository.deleteById(id);
    }
}
