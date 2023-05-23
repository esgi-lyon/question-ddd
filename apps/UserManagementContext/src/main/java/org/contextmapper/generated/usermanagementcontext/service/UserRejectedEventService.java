package org.contextmapper.generated.usermanagementcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.usermanagementcontext.domain.UserRejectedEvent;
import org.contextmapper.generated.usermanagementcontext.repository.UserRejectedEventRepository;
import org.contextmapper.generated.usermanagementcontext.service.dto.UserRejectedEventDTO;
import org.contextmapper.generated.usermanagementcontext.service.mapper.UserRejectedEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link UserRejectedEvent}.
 */
@Service
@Transactional
public class UserRejectedEventService {

    private final Logger log = LoggerFactory.getLogger(UserRejectedEventService.class);

    private final UserRejectedEventRepository userRejectedEventRepository;

    private final UserRejectedEventMapper userRejectedEventMapper;

    public UserRejectedEventService(
        UserRejectedEventRepository userRejectedEventRepository,
        UserRejectedEventMapper userRejectedEventMapper
    ) {
        this.userRejectedEventRepository = userRejectedEventRepository;
        this.userRejectedEventMapper = userRejectedEventMapper;
    }

    /**
     * Save a userRejectedEvent.
     *
     * @param userRejectedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public UserRejectedEventDTO save(UserRejectedEventDTO userRejectedEventDTO) {
        log.debug("Request to save UserRejectedEvent : {}", userRejectedEventDTO);
        UserRejectedEvent userRejectedEvent = userRejectedEventMapper.toEntity(userRejectedEventDTO);
        userRejectedEvent = userRejectedEventRepository.save(userRejectedEvent);
        return userRejectedEventMapper.toDto(userRejectedEvent);
    }

    /**
     * Update a userRejectedEvent.
     *
     * @param userRejectedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public UserRejectedEventDTO update(UserRejectedEventDTO userRejectedEventDTO) {
        log.debug("Request to update UserRejectedEvent : {}", userRejectedEventDTO);
        UserRejectedEvent userRejectedEvent = userRejectedEventMapper.toEntity(userRejectedEventDTO);
        userRejectedEvent = userRejectedEventRepository.save(userRejectedEvent);
        return userRejectedEventMapper.toDto(userRejectedEvent);
    }

    /**
     * Partially update a userRejectedEvent.
     *
     * @param userRejectedEventDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<UserRejectedEventDTO> partialUpdate(UserRejectedEventDTO userRejectedEventDTO) {
        log.debug("Request to partially update UserRejectedEvent : {}", userRejectedEventDTO);

        return userRejectedEventRepository
            .findById(userRejectedEventDTO.getId())
            .map(existingUserRejectedEvent -> {
                userRejectedEventMapper.partialUpdate(existingUserRejectedEvent, userRejectedEventDTO);

                return existingUserRejectedEvent;
            })
            .map(userRejectedEventRepository::save)
            .map(userRejectedEventMapper::toDto);
    }

    /**
     * Get all the userRejectedEvents.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<UserRejectedEventDTO> findAll() {
        log.debug("Request to get all UserRejectedEvents");
        return userRejectedEventRepository
            .findAll()
            .stream()
            .map(userRejectedEventMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one userRejectedEvent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<UserRejectedEventDTO> findOne(Long id) {
        log.debug("Request to get UserRejectedEvent : {}", id);
        return userRejectedEventRepository.findById(id).map(userRejectedEventMapper::toDto);
    }

    /**
     * Delete the userRejectedEvent by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete UserRejectedEvent : {}", id);
        userRejectedEventRepository.deleteById(id);
    }
}
