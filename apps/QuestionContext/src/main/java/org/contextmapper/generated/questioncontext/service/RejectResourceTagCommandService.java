package org.contextmapper.generated.questioncontext.service;

import java.util.List;
import java.util.Optional;
import org.contextmapper.generated.questioncontext.domain.RejectResourceTagCommand;
import org.contextmapper.generated.questioncontext.repository.RejectResourceTagCommandRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public RejectResourceTagCommand save(RejectResourceTagCommand rejectResourceTagCommand) {
        log.debug("Request to save RejectResourceTagCommand : {}", rejectResourceTagCommand);
        return rejectResourceTagCommandRepository.save(rejectResourceTagCommand);
    }

    /**
     * Update a rejectResourceTagCommand.
     *
     * @param rejectResourceTagCommand the entity to save.
     * @return the persisted entity.
     */
    public RejectResourceTagCommand update(RejectResourceTagCommand rejectResourceTagCommand) {
        log.debug("Request to update RejectResourceTagCommand : {}", rejectResourceTagCommand);
        // no save call needed as we have no fields that can be updated
        return rejectResourceTagCommand;
    }

    /**
     * Partially update a rejectResourceTagCommand.
     *
     * @param rejectResourceTagCommand the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<RejectResourceTagCommand> partialUpdate(RejectResourceTagCommand rejectResourceTagCommand) {
        log.debug("Request to partially update RejectResourceTagCommand : {}", rejectResourceTagCommand);

        return rejectResourceTagCommandRepository
            .findById(rejectResourceTagCommand.getId())
            .map(existingRejectResourceTagCommand -> {
                return existingRejectResourceTagCommand;
            })// .map(rejectResourceTagCommandRepository::save)
        ;
    }

    /**
     * Get all the rejectResourceTagCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<RejectResourceTagCommand> findAll() {
        log.debug("Request to get all RejectResourceTagCommands");
        return rejectResourceTagCommandRepository.findAll();
    }

    /**
     * Get one rejectResourceTagCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RejectResourceTagCommand> findOne(Long id) {
        log.debug("Request to get RejectResourceTagCommand : {}", id);
        return rejectResourceTagCommandRepository.findById(id);
    }

    /**
     * Delete the rejectResourceTagCommand by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete RejectResourceTagCommand : {}", id);
        rejectResourceTagCommandRepository.deleteById(id);
    }
}
