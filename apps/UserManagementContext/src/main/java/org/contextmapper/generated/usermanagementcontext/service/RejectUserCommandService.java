package org.contextmapper.generated.usermanagementcontext.service;

import java.util.List;
import java.util.Optional;
import org.contextmapper.generated.usermanagementcontext.domain.RejectUserCommand;
import org.contextmapper.generated.usermanagementcontext.repository.RejectUserCommandRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link RejectUserCommand}.
 */
@Service
@Transactional
public class RejectUserCommandService {

    private final Logger log = LoggerFactory.getLogger(RejectUserCommandService.class);

    private final RejectUserCommandRepository rejectUserCommandRepository;

    public RejectUserCommandService(RejectUserCommandRepository rejectUserCommandRepository) {
        this.rejectUserCommandRepository = rejectUserCommandRepository;
    }

    /**
     * Save a rejectUserCommand.
     *
     * @param rejectUserCommand the entity to save.
     * @return the persisted entity.
     */
    public RejectUserCommand save(RejectUserCommand rejectUserCommand) {
        log.debug("Request to save RejectUserCommand : {}", rejectUserCommand);
        return rejectUserCommandRepository.save(rejectUserCommand);
    }

    /**
     * Update a rejectUserCommand.
     *
     * @param rejectUserCommand the entity to save.
     * @return the persisted entity.
     */
    public RejectUserCommand update(RejectUserCommand rejectUserCommand) {
        log.debug("Request to update RejectUserCommand : {}", rejectUserCommand);
        return rejectUserCommandRepository.save(rejectUserCommand);
    }

    /**
     * Partially update a rejectUserCommand.
     *
     * @param rejectUserCommand the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<RejectUserCommand> partialUpdate(RejectUserCommand rejectUserCommand) {
        log.debug("Request to partially update RejectUserCommand : {}", rejectUserCommand);

        return rejectUserCommandRepository
            .findById(rejectUserCommand.getId())
            .map(existingRejectUserCommand -> {
                return existingRejectUserCommand;
            })
            .map(rejectUserCommandRepository::save);
    }

    /**
     * Get all the rejectUserCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<RejectUserCommand> findAll() {
        log.debug("Request to get all RejectUserCommands");
        return rejectUserCommandRepository.findAll();
    }

    /**
     * Get one rejectUserCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RejectUserCommand> findOne(Long id) {
        log.debug("Request to get RejectUserCommand : {}", id);
        return rejectUserCommandRepository.findById(id);
    }

    /**
     * Delete the rejectUserCommand by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete RejectUserCommand : {}", id);
        rejectUserCommandRepository.deleteById(id);
    }
}
