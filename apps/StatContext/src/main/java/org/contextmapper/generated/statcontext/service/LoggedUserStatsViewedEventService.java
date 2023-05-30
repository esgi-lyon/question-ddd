package org.contextmapper.generated.statcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.statcontext.domain.LoggedUserStatsViewedEvent;
import org.contextmapper.generated.statcontext.repository.LoggedUserStatsViewedEventRepository;
import org.contextmapper.generated.statcontext.service.dto.LoggedUserStatsViewedEventDTO;
import org.contextmapper.generated.statcontext.service.mapper.LoggedUserStatsViewedEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link LoggedUserStatsViewedEvent}.
 */
@Service
@Transactional
public class LoggedUserStatsViewedEventService {

    private final Logger log = LoggerFactory.getLogger(LoggedUserStatsViewedEventService.class);

    private final LoggedUserStatsViewedEventRepository loggedUserStatsViewedEventRepository;

    private final LoggedUserStatsViewedEventMapper loggedUserStatsViewedEventMapper;

    public LoggedUserStatsViewedEventService(
        LoggedUserStatsViewedEventRepository loggedUserStatsViewedEventRepository,
        LoggedUserStatsViewedEventMapper loggedUserStatsViewedEventMapper
    ) {
        this.loggedUserStatsViewedEventRepository = loggedUserStatsViewedEventRepository;
        this.loggedUserStatsViewedEventMapper = loggedUserStatsViewedEventMapper;
    }

    /**
     * Save a loggedUserStatsViewedEvent.
     *
     * @param loggedUserStatsViewedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public LoggedUserStatsViewedEventDTO save(LoggedUserStatsViewedEventDTO loggedUserStatsViewedEventDTO) {
        log.debug("Request to save LoggedUserStatsViewedEvent : {}", loggedUserStatsViewedEventDTO);
        LoggedUserStatsViewedEvent loggedUserStatsViewedEvent = loggedUserStatsViewedEventMapper.toEntity(loggedUserStatsViewedEventDTO);
        loggedUserStatsViewedEvent = loggedUserStatsViewedEventRepository.save(loggedUserStatsViewedEvent);
        return loggedUserStatsViewedEventMapper.toDto(loggedUserStatsViewedEvent);
    }

    /**
     * Update a loggedUserStatsViewedEvent.
     *
     * @param loggedUserStatsViewedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public LoggedUserStatsViewedEventDTO update(LoggedUserStatsViewedEventDTO loggedUserStatsViewedEventDTO) {
        log.debug("Request to update LoggedUserStatsViewedEvent : {}", loggedUserStatsViewedEventDTO);
        LoggedUserStatsViewedEvent loggedUserStatsViewedEvent = loggedUserStatsViewedEventMapper.toEntity(loggedUserStatsViewedEventDTO);
        loggedUserStatsViewedEvent = loggedUserStatsViewedEventRepository.save(loggedUserStatsViewedEvent);
        return loggedUserStatsViewedEventMapper.toDto(loggedUserStatsViewedEvent);
    }

    /**
     * Partially update a loggedUserStatsViewedEvent.
     *
     * @param loggedUserStatsViewedEventDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<LoggedUserStatsViewedEventDTO> partialUpdate(LoggedUserStatsViewedEventDTO loggedUserStatsViewedEventDTO) {
        log.debug("Request to partially update LoggedUserStatsViewedEvent : {}", loggedUserStatsViewedEventDTO);

        return loggedUserStatsViewedEventRepository
            .findById(loggedUserStatsViewedEventDTO.getId())
            .map(existingLoggedUserStatsViewedEvent -> {
                loggedUserStatsViewedEventMapper.partialUpdate(existingLoggedUserStatsViewedEvent, loggedUserStatsViewedEventDTO);

                return existingLoggedUserStatsViewedEvent;
            })
            .map(loggedUserStatsViewedEventRepository::save)
            .map(loggedUserStatsViewedEventMapper::toDto);
    }

    /**
     * Get all the loggedUserStatsViewedEvents.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<LoggedUserStatsViewedEventDTO> findAll() {
        log.debug("Request to get all LoggedUserStatsViewedEvents");
        return loggedUserStatsViewedEventRepository
            .findAll()
            .stream()
            .map(loggedUserStatsViewedEventMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one loggedUserStatsViewedEvent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<LoggedUserStatsViewedEventDTO> findOne(Long id) {
        log.debug("Request to get LoggedUserStatsViewedEvent : {}", id);
        return loggedUserStatsViewedEventRepository.findById(id).map(loggedUserStatsViewedEventMapper::toDto);
    }

    /**
     * Delete the loggedUserStatsViewedEvent by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete LoggedUserStatsViewedEvent : {}", id);
        loggedUserStatsViewedEventRepository.deleteById(id);
    }
}
