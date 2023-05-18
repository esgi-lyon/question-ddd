package org.contextmapper.generated.statcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.statcontext.domain.ViewStats;
import org.contextmapper.generated.statcontext.repository.ViewStatsRepository;
import org.contextmapper.generated.statcontext.service.dto.ViewStatsDTO;
import org.contextmapper.generated.statcontext.service.mapper.ViewStatsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ViewStats}.
 */
@Service
@Transactional
public class ViewStatsService {

    private final Logger log = LoggerFactory.getLogger(ViewStatsService.class);

    private final ViewStatsRepository viewStatsRepository;

    private final ViewStatsMapper viewStatsMapper;

    public ViewStatsService(ViewStatsRepository viewStatsRepository, ViewStatsMapper viewStatsMapper) {
        this.viewStatsRepository = viewStatsRepository;
        this.viewStatsMapper = viewStatsMapper;
    }

    /**
     * Save a viewStats.
     *
     * @param viewStatsDTO the entity to save.
     * @return the persisted entity.
     */
    public ViewStatsDTO save(ViewStatsDTO viewStatsDTO) {
        log.debug("Request to save ViewStats : {}", viewStatsDTO);
        ViewStats viewStats = viewStatsMapper.toEntity(viewStatsDTO);
        viewStats = viewStatsRepository.save(viewStats);
        return viewStatsMapper.toDto(viewStats);
    }

    /**
     * Update a viewStats.
     *
     * @param viewStatsDTO the entity to save.
     * @return the persisted entity.
     */
    public ViewStatsDTO update(ViewStatsDTO viewStatsDTO) {
        log.debug("Request to update ViewStats : {}", viewStatsDTO);
        ViewStats viewStats = viewStatsMapper.toEntity(viewStatsDTO);
        viewStats = viewStatsRepository.save(viewStats);
        return viewStatsMapper.toDto(viewStats);
    }

    /**
     * Partially update a viewStats.
     *
     * @param viewStatsDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ViewStatsDTO> partialUpdate(ViewStatsDTO viewStatsDTO) {
        log.debug("Request to partially update ViewStats : {}", viewStatsDTO);

        return viewStatsRepository
            .findById(viewStatsDTO.getId())
            .map(existingViewStats -> {
                viewStatsMapper.partialUpdate(existingViewStats, viewStatsDTO);

                return existingViewStats;
            })
            .map(viewStatsRepository::save)
            .map(viewStatsMapper::toDto);
    }

    /**
     * Get all the viewStats.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ViewStatsDTO> findAll() {
        log.debug("Request to get all ViewStats");
        return viewStatsRepository.findAll().stream().map(viewStatsMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one viewStats by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ViewStatsDTO> findOne(Long id) {
        log.debug("Request to get ViewStats : {}", id);
        return viewStatsRepository.findById(id).map(viewStatsMapper::toDto);
    }

    /**
     * Delete the viewStats by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ViewStats : {}", id);
        viewStatsRepository.deleteById(id);
    }
}
