package org.contextmapper.generated.gateway.service;

import java.util.List;
import org.contextmapper.generated.gateway.domain.RejectResourceTagCommand;
import org.contextmapper.generated.gateway.repository.RejectResourceTagCommandRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link RejectResourceTagCommand}.
 */
@Service
@Transactional
public class RejectResourceTagCommandService {

    private final Logger log = LoggerFactory.getLogger(RejectResourceTagCommandService.class);

    private final RejectResourceTagCommandRepository rejectResourceTagCommandRepository;

    public RejectResourceTagCommandService(RejectResourceTagCommandRepository rejectResourceTagCommandRepository) {
        this.rejectResourceTagCommandRepository = rejectResourceTagCommandRepository;
    }

    /**
     * Save a rejectResourceTagCommand.
     *
     * @param rejectResourceTagCommand the entity to save.
     * @return the persisted entity.
     */
    public Mono<RejectResourceTagCommand> save(RejectResourceTagCommand rejectResourceTagCommand) {
        log.debug("Request to save RejectResourceTagCommand : {}", rejectResourceTagCommand);
        return rejectResourceTagCommandRepository.save(rejectResourceTagCommand);
    }

    /**
     * Update a rejectResourceTagCommand.
     *
     * @param rejectResourceTagCommand the entity to save.
     * @return the persisted entity.
     */
    public Mono<RejectResourceTagCommand> update(RejectResourceTagCommand rejectResourceTagCommand) {
        log.debug("Request to update RejectResourceTagCommand : {}", rejectResourceTagCommand);
        return rejectResourceTagCommandRepository.save(rejectResourceTagCommand);
    }

    /**
     * Partially update a rejectResourceTagCommand.
     *
     * @param rejectResourceTagCommand the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<RejectResourceTagCommand> partialUpdate(RejectResourceTagCommand rejectResourceTagCommand) {
        log.debug("Request to partially update RejectResourceTagCommand : {}", rejectResourceTagCommand);

        return rejectResourceTagCommandRepository
            .findById(rejectResourceTagCommand.getId())
            .map(existingRejectResourceTagCommand -> {
                return existingRejectResourceTagCommand;
            })
            .flatMap(rejectResourceTagCommandRepository::save);
    }

    /**
     * Get all the rejectResourceTagCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<RejectResourceTagCommand> findAll() {
        log.debug("Request to get all RejectResourceTagCommands");
        return rejectResourceTagCommandRepository.findAll();
    }

    /**
     * Returns the number of rejectResourceTagCommands available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return rejectResourceTagCommandRepository.count();
    }

    /**
     * Get one rejectResourceTagCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<RejectResourceTagCommand> findOne(Long id) {
        log.debug("Request to get RejectResourceTagCommand : {}", id);
        return rejectResourceTagCommandRepository.findById(id);
    }

    /**
     * Delete the rejectResourceTagCommand by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete RejectResourceTagCommand : {}", id);
        return rejectResourceTagCommandRepository.deleteById(id);
    }
}
