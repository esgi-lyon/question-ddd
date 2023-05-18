package org.contextmapper.generated.statcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.statcontext.domain.UserStatsViewedEvent;
import org.contextmapper.generated.statcontext.repository.UserStatsViewedEventRepository;
import org.contextmapper.generated.statcontext.service.dto.UserStatsViewedEventDTO;
import org.contextmapper.generated.statcontext.service.mapper.UserStatsViewedEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link UserStatsViewedEvent}.
 */
@Service
@Transactional
public class UserStatsViewedEventService {

    private final Logger log = LoggerFactory.getLogger(UserStatsViewedEventService.class);

    private final UserStatsViewedEventRepository userStatsViewedEventRepository;

    private final UserStatsViewedEventMapper userStatsViewedEventMapper;

    public UserStatsViewedEventService(
        UserStatsViewedEventRepository userStatsViewedEventRepository,
        UserStatsViewedEventMapper userStatsViewedEventMapper
    ) {
        this.userStatsViewedEventRepository = userStatsViewedEventRepository;
        this.userStatsViewedEventMapper = userStatsViewedEventMapper;
    }

    /**
     * Save a userStatsViewedEvent.
     *
     * @param userStatsViewedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public UserStatsViewedEventDTO save(UserStatsViewedEventDTO userStatsViewedEventDTO) {
        log.debug("Request to save UserStatsViewedEvent : {}", userStatsViewedEventDTO);
        UserStatsViewedEvent userStatsViewedEvent = userStatsViewedEventMapper.toEntity(userStatsViewedEventDTO);
        userStatsViewedEvent = userStatsViewedEventRepository.save(userStatsViewedEvent);
        return userStatsViewedEventMapper.toDto(userStatsViewedEvent);
    }

    /**
     * Update a userStatsViewedEvent.
     *
     * @param userStatsViewedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public UserStatsViewedEventDTO update(UserStatsViewedEventDTO userStatsViewedEventDTO) {
        log.debug("Request to update UserStatsViewedEvent : {}", userStatsViewedEventDTO);
        UserStatsViewedEvent userStatsViewedEvent = userStatsViewedEventMapper.toEntity(userStatsViewedEventDTO);
        userStatsViewedEvent = userStatsViewedEventRepository.save(userStatsViewedEvent);
        return userStatsViewedEventMapper.toDto(userStatsViewedEvent);
    }

    /**
     * Partially update a userStatsViewedEvent.
     *
     * @param userStatsViewedEventDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<UserStatsViewedEventDTO> partialUpdate(UserStatsViewedEventDTO userStatsViewedEventDTO) {
        log.debug("Request to partially update UserStatsViewedEvent : {}", userStatsViewedEventDTO);

        return userStatsViewedEventRepository
            .findById(userStatsViewedEventDTO.getId())
            .map(existingUserStatsViewedEvent -> {
                userStatsViewedEventMapper.partialUpdate(existingUserStatsViewedEvent, userStatsViewedEventDTO);

                return existingUserStatsViewedEvent;
            })
            .map(userStatsViewedEventRepository::save)
            .map(userStatsViewedEventMapper::toDto);
    }

    /**
     * Get all the userStatsViewedEvents.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<UserStatsViewedEventDTO> findAll() {
        log.debug("Request to get all UserStatsViewedEvents");
        return userStatsViewedEventRepository
            .findAll()
            .stream()
            .map(userStatsViewedEventMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one userStatsViewedEvent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<UserStatsViewedEventDTO> findOne(Long id) {
        log.debug("Request to get UserStatsViewedEvent : {}", id);
        return userStatsViewedEventRepository.findById(id).map(userStatsViewedEventMapper::toDto);
    }

    /**
     * Delete the userStatsViewedEvent by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete UserStatsViewedEvent : {}", id);
        userStatsViewedEventRepository.deleteById(id);
    }
}
