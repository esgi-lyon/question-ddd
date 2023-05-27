package org.contextmapper.generated.statcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.statcontext.domain.ViewStatsCommand;
import org.contextmapper.generated.statcontext.repository.ViewStatsCommandRepository;
import org.contextmapper.generated.statcontext.service.dto.ViewStatsCommandDTO;
import org.contextmapper.generated.statcontext.service.mapper.ViewStatsCommandMapper;
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

    private final ViewStatsCommandMapper viewStatsCommandMapper;

    public ViewStatsCommandService(ViewStatsCommandRepository viewStatsCommandRepository, ViewStatsCommandMapper viewStatsCommandMapper) {
        this.viewStatsCommandRepository = viewStatsCommandRepository;
        this.viewStatsCommandMapper = viewStatsCommandMapper;
    }

    /**
     * Save a viewStatsCommand.
     *
     * @param viewStatsCommandDTO the entity to save.
     * @return the persisted entity.
     */
    public ViewStatsCommandDTO save(ViewStatsCommandDTO viewStatsCommandDTO) {
        log.debug("Request to save ViewStatsCommand : {}", viewStatsCommandDTO);
        ViewStatsCommand viewStatsCommand = viewStatsCommandMapper.toEntity(viewStatsCommandDTO);
        viewStatsCommand = viewStatsCommandRepository.save(viewStatsCommand);
        return viewStatsCommandMapper.toDto(viewStatsCommand);
    }

    /**
     * Update a viewStatsCommand.
     *
     * @param viewStatsCommandDTO the entity to save.
     * @return the persisted entity.
     */
    public ViewStatsCommandDTO update(ViewStatsCommandDTO viewStatsCommandDTO) {
        log.debug("Request to update ViewStatsCommand : {}", viewStatsCommandDTO);
        ViewStatsCommand viewStatsCommand = viewStatsCommandMapper.toEntity(viewStatsCommandDTO);
        viewStatsCommand = viewStatsCommandRepository.save(viewStatsCommand);
        return viewStatsCommandMapper.toDto(viewStatsCommand);
    }

    /**
     * Partially update a viewStatsCommand.
     *
     * @param viewStatsCommandDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ViewStatsCommandDTO> partialUpdate(ViewStatsCommandDTO viewStatsCommandDTO) {
        log.debug("Request to partially update ViewStatsCommand : {}", viewStatsCommandDTO);

        return viewStatsCommandRepository
            .findById(viewStatsCommandDTO.getId())
            .map(existingViewStatsCommand -> {
                viewStatsCommandMapper.partialUpdate(existingViewStatsCommand, viewStatsCommandDTO);

                return existingViewStatsCommand;
            })
            .map(viewStatsCommandRepository::save)
            .map(viewStatsCommandMapper::toDto);
    }

    /**
     * Get all the viewStatsCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ViewStatsCommandDTO> findAll() {
        log.debug("Request to get all ViewStatsCommands");
        return viewStatsCommandRepository
            .findAll()
            .stream()
            .map(viewStatsCommandMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one viewStatsCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ViewStatsCommandDTO> findOne(Long id) {
        log.debug("Request to get ViewStatsCommand : {}", id);
        return viewStatsCommandRepository.findById(id).map(viewStatsCommandMapper::toDto);
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
