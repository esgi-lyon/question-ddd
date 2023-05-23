package org.contextmapper.generated.usermanagementcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.usermanagementcontext.domain.UserValidatedEvent;
import org.contextmapper.generated.usermanagementcontext.repository.UserValidatedEventRepository;
import org.contextmapper.generated.usermanagementcontext.service.dto.UserValidatedEventDTO;
import org.contextmapper.generated.usermanagementcontext.service.mapper.UserValidatedEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link UserValidatedEvent}.
 */
@Service
@Transactional
public class UserValidatedEventService {

    private final Logger log = LoggerFactory.getLogger(UserValidatedEventService.class);

    private final UserValidatedEventRepository userValidatedEventRepository;

    private final UserValidatedEventMapper userValidatedEventMapper;

    public UserValidatedEventService(
        UserValidatedEventRepository userValidatedEventRepository,
        UserValidatedEventMapper userValidatedEventMapper
    ) {
        this.userValidatedEventRepository = userValidatedEventRepository;
        this.userValidatedEventMapper = userValidatedEventMapper;
    }

    /**
     * Save a userValidatedEvent.
     *
     * @param userValidatedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public UserValidatedEventDTO save(UserValidatedEventDTO userValidatedEventDTO) {
        log.debug("Request to save UserValidatedEvent : {}", userValidatedEventDTO);
        UserValidatedEvent userValidatedEvent = userValidatedEventMapper.toEntity(userValidatedEventDTO);
        userValidatedEvent = userValidatedEventRepository.save(userValidatedEvent);
        return userValidatedEventMapper.toDto(userValidatedEvent);
    }

    /**
     * Update a userValidatedEvent.
     *
     * @param userValidatedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public UserValidatedEventDTO update(UserValidatedEventDTO userValidatedEventDTO) {
        log.debug("Request to update UserValidatedEvent : {}", userValidatedEventDTO);
        UserValidatedEvent userValidatedEvent = userValidatedEventMapper.toEntity(userValidatedEventDTO);
        userValidatedEvent = userValidatedEventRepository.save(userValidatedEvent);
        return userValidatedEventMapper.toDto(userValidatedEvent);
    }

    /**
     * Partially update a userValidatedEvent.
     *
     * @param userValidatedEventDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<UserValidatedEventDTO> partialUpdate(UserValidatedEventDTO userValidatedEventDTO) {
        log.debug("Request to partially update UserValidatedEvent : {}", userValidatedEventDTO);

        return userValidatedEventRepository
            .findById(userValidatedEventDTO.getId())
            .map(existingUserValidatedEvent -> {
                userValidatedEventMapper.partialUpdate(existingUserValidatedEvent, userValidatedEventDTO);

                return existingUserValidatedEvent;
            })
            .map(userValidatedEventRepository::save)
            .map(userValidatedEventMapper::toDto);
    }

    /**
     * Get all the userValidatedEvents.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<UserValidatedEventDTO> findAll() {
        log.debug("Request to get all UserValidatedEvents");
        return userValidatedEventRepository
            .findAll()
            .stream()
            .map(userValidatedEventMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one userValidatedEvent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<UserValidatedEventDTO> findOne(Long id) {
        log.debug("Request to get UserValidatedEvent : {}", id);
        return userValidatedEventRepository.findById(id).map(userValidatedEventMapper::toDto);
    }

    /**
     * Delete the userValidatedEvent by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete UserValidatedEvent : {}", id);
        userValidatedEventRepository.deleteById(id);
    }
}
