package org.contextmapper.generated.statcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.statcontext.domain.ViewLeaderBoardCommand;
import org.contextmapper.generated.statcontext.repository.ViewLeaderBoardCommandRepository;
import org.contextmapper.generated.statcontext.service.dto.ViewLeaderBoardCommandDTO;
import org.contextmapper.generated.statcontext.service.mapper.ViewLeaderBoardCommandMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ViewLeaderBoardCommand}.
 */
@Service
@Transactional
public class ViewLeaderBoardCommandService {

    private final Logger log = LoggerFactory.getLogger(ViewLeaderBoardCommandService.class);

    private final ViewLeaderBoardCommandRepository viewLeaderBoardCommandRepository;

    private final ViewLeaderBoardCommandMapper viewLeaderBoardCommandMapper;

    public ViewLeaderBoardCommandService(
        ViewLeaderBoardCommandRepository viewLeaderBoardCommandRepository,
        ViewLeaderBoardCommandMapper viewLeaderBoardCommandMapper
    ) {
        this.viewLeaderBoardCommandRepository = viewLeaderBoardCommandRepository;
        this.viewLeaderBoardCommandMapper = viewLeaderBoardCommandMapper;
    }

    /**
     * Save a viewLeaderBoardCommand.
     *
     * @param viewLeaderBoardCommandDTO the entity to save.
     * @return the persisted entity.
     */
    public ViewLeaderBoardCommandDTO save(ViewLeaderBoardCommandDTO viewLeaderBoardCommandDTO) {
        log.debug("Request to save ViewLeaderBoardCommand : {}", viewLeaderBoardCommandDTO);
        ViewLeaderBoardCommand viewLeaderBoardCommand = viewLeaderBoardCommandMapper.toEntity(viewLeaderBoardCommandDTO);
        viewLeaderBoardCommand = viewLeaderBoardCommandRepository.save(viewLeaderBoardCommand);
        return viewLeaderBoardCommandMapper.toDto(viewLeaderBoardCommand);
    }

    /**
     * Update a viewLeaderBoardCommand.
     *
     * @param viewLeaderBoardCommandDTO the entity to save.
     * @return the persisted entity.
     */
    public ViewLeaderBoardCommandDTO update(ViewLeaderBoardCommandDTO viewLeaderBoardCommandDTO) {
        log.debug("Request to update ViewLeaderBoardCommand : {}", viewLeaderBoardCommandDTO);
        ViewLeaderBoardCommand viewLeaderBoardCommand = viewLeaderBoardCommandMapper.toEntity(viewLeaderBoardCommandDTO);
        viewLeaderBoardCommand = viewLeaderBoardCommandRepository.save(viewLeaderBoardCommand);
        return viewLeaderBoardCommandMapper.toDto(viewLeaderBoardCommand);
    }

    /**
     * Partially update a viewLeaderBoardCommand.
     *
     * @param viewLeaderBoardCommandDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ViewLeaderBoardCommandDTO> partialUpdate(ViewLeaderBoardCommandDTO viewLeaderBoardCommandDTO) {
        log.debug("Request to partially update ViewLeaderBoardCommand : {}", viewLeaderBoardCommandDTO);

        return viewLeaderBoardCommandRepository
            .findById(viewLeaderBoardCommandDTO.getId())
            .map(existingViewLeaderBoardCommand -> {
                viewLeaderBoardCommandMapper.partialUpdate(existingViewLeaderBoardCommand, viewLeaderBoardCommandDTO);

                return existingViewLeaderBoardCommand;
            })
            .map(viewLeaderBoardCommandRepository::save)
            .map(viewLeaderBoardCommandMapper::toDto);
    }

    /**
     * Get all the viewLeaderBoardCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ViewLeaderBoardCommandDTO> findAll() {
        log.debug("Request to get all ViewLeaderBoardCommands");
        return viewLeaderBoardCommandRepository
            .findAll()
            .stream()
            .map(viewLeaderBoardCommandMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one viewLeaderBoardCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ViewLeaderBoardCommandDTO> findOne(Long id) {
        log.debug("Request to get ViewLeaderBoardCommand : {}", id);
        return viewLeaderBoardCommandRepository.findById(id).map(viewLeaderBoardCommandMapper::toDto);
    }

    /**
     * Delete the viewLeaderBoardCommand by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ViewLeaderBoardCommand : {}", id);
        viewLeaderBoardCommandRepository.deleteById(id);
    }
}
