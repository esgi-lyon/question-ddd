package org.contextmapper.generated.gateway.service;

import java.util.List;
import org.contextmapper.generated.gateway.domain.TagChoicesListCommand;
import org.contextmapper.generated.gateway.repository.TagChoicesListCommandRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    public Mono<TagChoicesListCommand> save(TagChoicesListCommand tagChoicesListCommand) {
        log.debug("Request to save TagChoicesListCommand : {}", tagChoicesListCommand);
        return tagChoicesListCommandRepository.save(tagChoicesListCommand);
    }

    /**
     * Update a tagChoicesListCommand.
     *
     * @param tagChoicesListCommand the entity to save.
     * @return the persisted entity.
     */
    public Mono<TagChoicesListCommand> update(TagChoicesListCommand tagChoicesListCommand) {
        log.debug("Request to update TagChoicesListCommand : {}", tagChoicesListCommand);
        // no save call needed as we have no fields that can be updated
        return tagChoicesListCommandRepository.findById(tagChoicesListCommand.getId());
    }

    /**
     * Partially update a tagChoicesListCommand.
     *
     * @param tagChoicesListCommand the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<TagChoicesListCommand> partialUpdate(TagChoicesListCommand tagChoicesListCommand) {
        log.debug("Request to partially update TagChoicesListCommand : {}", tagChoicesListCommand);

        return tagChoicesListCommandRepository
            .findById(tagChoicesListCommand.getId())
            .map(existingTagChoicesListCommand -> {
                return existingTagChoicesListCommand;
            })// .flatMap(tagChoicesListCommandRepository::save)
        ;
    }

    /**
     * Get all the tagChoicesListCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<TagChoicesListCommand> findAll() {
        log.debug("Request to get all TagChoicesListCommands");
        return tagChoicesListCommandRepository.findAll();
    }

    /**
     * Returns the number of tagChoicesListCommands available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return tagChoicesListCommandRepository.count();
    }

    /**
     * Get one tagChoicesListCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<TagChoicesListCommand> findOne(Long id) {
        log.debug("Request to get TagChoicesListCommand : {}", id);
        return tagChoicesListCommandRepository.findById(id);
    }

    /**
     * Delete the tagChoicesListCommand by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete TagChoicesListCommand : {}", id);
        return tagChoicesListCommandRepository.deleteById(id);
    }
}
