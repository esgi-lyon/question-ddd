package org.contextmapper.generated.statcontext.service;

import java.util.List;
import java.util.Optional;
import org.contextmapper.generated.statcontext.domain.ViewStatsCommand;
import org.contextmapper.generated.statcontext.repository.ViewStatsCommandRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ViewStatsCommand}.
 */
@Service
@Transactional
public class ViewStatsCommandService {

    private final Logger log = LoggerFactory.getLogger(ViewStatsCommandService.class);

    private final ViewStatsCommandRepository viewStatsCommandRepository;

    public ViewStatsCommandService(ViewStatsCommandRepository viewStatsCommandRepository) {
        this.viewStatsCommandRepository = viewStatsCommandRepository;
    }

    /**
     * Save a viewStatsCommand.
     *
     * @param viewStatsCommand the entity to save.
     * @return the persisted entity.
     */
    public ViewStatsCommand save(ViewStatsCommand viewStatsCommand) {
        log.debug("Request to save ViewStatsCommand : {}", viewStatsCommand);
        return viewStatsCommandRepository.save(viewStatsCommand);
    }

    /**
     * Update a viewStatsCommand.
     *
     * @param viewStatsCommand the entity to save.
     * @return the persisted entity.
     */
    public ViewStatsCommand update(ViewStatsCommand viewStatsCommand) {
        log.debug("Request to update ViewStatsCommand : {}", viewStatsCommand);
        return viewStatsCommandRepository.save(viewStatsCommand);
    }

    /**
     * Partially update a viewStatsCommand.
     *
     * @param viewStatsCommand the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ViewStatsCommand> partialUpdate(ViewStatsCommand viewStatsCommand) {
        log.debug("Request to partially update ViewStatsCommand : {}", viewStatsCommand);

        return viewStatsCommandRepository
            .findById(viewStatsCommand.getId())
            .map(existingViewStatsCommand -> {
                return existingViewStatsCommand;
            })
            .map(viewStatsCommandRepository::save);
    }

    /**
     * Get all the viewStatsCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ViewStatsCommand> findAll() {
        log.debug("Request to get all ViewStatsCommands");
        return viewStatsCommandRepository.findAll();
    }

    /**
     * Get one viewStatsCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ViewStatsCommand> findOne(Long id) {
        log.debug("Request to get ViewStatsCommand : {}", id);
        return viewStatsCommandRepository.findById(id);
    }

    /**
     * Delete the viewStatsCommand by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ViewStatsCommand : {}", id);
        viewStatsCommandRepository.deleteById(id);
    }
}
