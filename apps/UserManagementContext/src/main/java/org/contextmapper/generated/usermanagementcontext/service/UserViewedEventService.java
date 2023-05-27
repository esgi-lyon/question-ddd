package org.contextmapper.generated.usermanagementcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.usermanagementcontext.domain.UserViewedEvent;
import org.contextmapper.generated.usermanagementcontext.repository.UserViewedEventRepository;
import org.contextmapper.generated.usermanagementcontext.service.dto.UserViewedEventDTO;
import org.contextmapper.generated.usermanagementcontext.service.mapper.UserViewedEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link UserViewedEvent}.
 */
@Service
@Transactional
public class UserViewedEventService {

    private final Logger log = LoggerFactory.getLogger(UserViewedEventService.class);

    private final UserViewedEventRepository userViewedEventRepository;

    private final UserViewedEventMapper userViewedEventMapper;

    public UserViewedEventService(UserViewedEventRepository userViewedEventRepository, UserViewedEventMapper userViewedEventMapper) {
        this.userViewedEventRepository = userViewedEventRepository;
        this.userViewedEventMapper = userViewedEventMapper;
    }

    /**
     * Save a userViewedEvent.
     *
     * @param userViewedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public UserViewedEventDTO save(UserViewedEventDTO userViewedEventDTO) {
        log.debug("Request to save UserViewedEvent : {}", userViewedEventDTO);
        UserViewedEvent userViewedEvent = userViewedEventMapper.toEntity(userViewedEventDTO);
        userViewedEvent = userViewedEventRepository.save(userViewedEvent);
        return userViewedEventMapper.toDto(userViewedEvent);
    }

    /**
     * Update a userViewedEvent.
     *
     * @param userViewedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public UserViewedEventDTO update(UserViewedEventDTO userViewedEventDTO) {
        log.debug("Request to update UserViewedEvent : {}", userViewedEventDTO);
        UserViewedEvent userViewedEvent = userViewedEventMapper.toEntity(userViewedEventDTO);
        userViewedEvent = userViewedEventRepository.save(userViewedEvent);
        return userViewedEventMapper.toDto(userViewedEvent);
    }

    /**
     * Partially update a userViewedEvent.
     *
     * @param userViewedEventDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<UserViewedEventDTO> partialUpdate(UserViewedEventDTO userViewedEventDTO) {
        log.debug("Request to partially update UserViewedEvent : {}", userViewedEventDTO);

        return userViewedEventRepository
            .findById(userViewedEventDTO.getId())
            .map(existingUserViewedEvent -> {
                userViewedEventMapper.partialUpdate(existingUserViewedEvent, userViewedEventDTO);

                return existingUserViewedEvent;
            })
            .map(userViewedEventRepository::save)
            .map(userViewedEventMapper::toDto);
    }

    /**
     * Get all the userViewedEvents.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<UserViewedEventDTO> findAll() {
        log.debug("Request to get all UserViewedEvents");
        return userViewedEventRepository
            .findAll()
            .stream()
            .map(userViewedEventMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one userViewedEvent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<UserViewedEventDTO> findOne(Long id) {
        log.debug("Request to get UserViewedEvent : {}", id);
        return userViewedEventRepository.findById(id).map(userViewedEventMapper::toDto);
    }

    /**
     * Delete the userViewedEvent by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete UserViewedEvent : {}", id);
        userViewedEventRepository.deleteById(id);
    }
}
