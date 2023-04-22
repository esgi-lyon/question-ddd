package org.contextmapper.generated.answercontext.service;

import java.util.List;
import java.util.Optional;
import org.contextmapper.generated.answercontext.domain.TagChoicesListCommand;
import org.contextmapper.generated.answercontext.repository.TagChoicesListCommandRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TagChoicesListCommand}.
 */
@Service
@Transactional
public class TagChoicesListCommandService {

    private final Logger log = LoggerFactory.getLogger(TagChoicesListCommandService.class);

    private final TagChoicesListCommandRepository tagChoicesListCommandRepository;

    public TagChoicesListCommandService(TagChoicesListCommandRepository tagChoicesListCommandRepository) {
        this.tagChoicesListCommandRepository = tagChoicesListCommandRepository;
    }

    /**
     * Save a tagChoicesListCommand.
     *
     * @param tagChoicesListCommand the entity to save.
     * @return the persisted entity.
     */
    public TagChoicesListCommand save(TagChoicesListCommand tagChoicesListCommand) {
        log.debug("Request to save TagChoicesListCommand : {}", tagChoicesListCommand);
        return tagChoicesListCommandRepository.save(tagChoicesListCommand);
    }

    /**
     * Update a tagChoicesListCommand.
     *
     * @param tagChoicesListCommand the entity to save.
     * @return the persisted entity.
     */
    public TagChoicesListCommand update(TagChoicesListCommand tagChoicesListCommand) {
        log.debug("Request to update TagChoicesListCommand : {}", tagChoicesListCommand);
        // no save call needed as we have no fields that can be updated
        return tagChoicesListCommand;
    }

    /**
     * Partially update a tagChoicesListCommand.
     *
     * @param tagChoicesListCommand the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<TagChoicesListCommand> partialUpdate(TagChoicesListCommand tagChoicesListCommand) {
        log.debug("Request to partially update TagChoicesListCommand : {}", tagChoicesListCommand);

        return tagChoicesListCommandRepository
            .findById(tagChoicesListCommand.getId())
            .map(existingTagChoicesListCommand -> {
                return existingTagChoicesListCommand;
            })// .map(tagChoicesListCommandRepository::save)
        ;
    }

    /**
     * Get all the tagChoicesListCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TagChoicesListCommand> findAll() {
        log.debug("Request to get all TagChoicesListCommands");
        return tagChoicesListCommandRepository.findAll();
    }

    /**
     * Get one tagChoicesListCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TagChoicesListCommand> findOne(Long id) {
        log.debug("Request to get TagChoicesListCommand : {}", id);
        return tagChoicesListCommandRepository.findById(id);
    }

    /**
     * Delete the tagChoicesListCommand by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TagChoicesListCommand : {}", id);
        tagChoicesListCommandRepository.deleteById(id);
    }
}
