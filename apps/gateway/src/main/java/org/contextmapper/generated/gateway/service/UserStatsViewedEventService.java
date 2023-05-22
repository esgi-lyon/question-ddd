package org.contextmapper.generated.gateway.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.contextmapper.generated.gateway.domain.UserStatsViewedEvent;
import org.contextmapper.generated.gateway.repository.UserStatsViewedEventRepository;
import org.contextmapper.generated.gateway.service.dto.UserStatsViewedEventDTO;
import org.contextmapper.generated.gateway.service.mapper.UserStatsViewedEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    public Mono<UserStatsViewedEventDTO> save(UserStatsViewedEventDTO userStatsViewedEventDTO) {
        log.debug("Request to save UserStatsViewedEvent : {}", userStatsViewedEventDTO);
        return userStatsViewedEventRepository
            .save(userStatsViewedEventMapper.toEntity(userStatsViewedEventDTO))
            .map(userStatsViewedEventMapper::toDto);
    }

    /**
     * Update a userStatsViewedEvent.
     *
     * @param userStatsViewedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<UserStatsViewedEventDTO> update(UserStatsViewedEventDTO userStatsViewedEventDTO) {
        log.debug("Request to update UserStatsViewedEvent : {}", userStatsViewedEventDTO);
        return userStatsViewedEventRepository
            .save(userStatsViewedEventMapper.toEntity(userStatsViewedEventDTO))
            .map(userStatsViewedEventMapper::toDto);
    }

    /**
     * Partially update a userStatsViewedEvent.
     *
     * @param userStatsViewedEventDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<UserStatsViewedEventDTO> partialUpdate(UserStatsViewedEventDTO userStatsViewedEventDTO) {
        log.debug("Request to partially update UserStatsViewedEvent : {}", userStatsViewedEventDTO);

        return userStatsViewedEventRepository
            .findById(userStatsViewedEventDTO.getId())
            .map(existingUserStatsViewedEvent -> {
                userStatsViewedEventMapper.partialUpdate(existingUserStatsViewedEvent, userStatsViewedEventDTO);

                return existingUserStatsViewedEvent;
            })
            .flatMap(userStatsViewedEventRepository::save)
            .map(userStatsViewedEventMapper::toDto);
    }

    /**
     * Get all the userStatsViewedEvents.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<UserStatsViewedEventDTO> findAll() {
        log.debug("Request to get all UserStatsViewedEvents");
        return userStatsViewedEventRepository.findAll().map(userStatsViewedEventMapper::toDto);
    }

    /**
     * Returns the number of userStatsViewedEvents available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return userStatsViewedEventRepository.count();
    }

    /**
     * Get one userStatsViewedEvent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<UserStatsViewedEventDTO> findOne(Long id) {
        log.debug("Request to get UserStatsViewedEvent : {}", id);
        return userStatsViewedEventRepository.findById(id).map(userStatsViewedEventMapper::toDto);
    }

    /**
     * Delete the userStatsViewedEvent by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete UserStatsViewedEvent : {}", id);
        return userStatsViewedEventRepository.deleteById(id);
    }
}
