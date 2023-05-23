package org.contextmapper.generated.usermanagementcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.usermanagementcontext.domain.UserWaitingForValidationEvent;
import org.contextmapper.generated.usermanagementcontext.repository.UserWaitingForValidationEventRepository;
import org.contextmapper.generated.usermanagementcontext.service.dto.UserWaitingForValidationEventDTO;
import org.contextmapper.generated.usermanagementcontext.service.mapper.UserWaitingForValidationEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link UserWaitingForValidationEvent}.
 */
@Service
@Transactional
public class UserWaitingForValidationEventService {

    private final Logger log = LoggerFactory.getLogger(UserWaitingForValidationEventService.class);

    private final UserWaitingForValidationEventRepository userWaitingForValidationEventRepository;

    private final UserWaitingForValidationEventMapper userWaitingForValidationEventMapper;

    public UserWaitingForValidationEventService(
        UserWaitingForValidationEventRepository userWaitingForValidationEventRepository,
        UserWaitingForValidationEventMapper userWaitingForValidationEventMapper
    ) {
        this.userWaitingForValidationEventRepository = userWaitingForValidationEventRepository;
        this.userWaitingForValidationEventMapper = userWaitingForValidationEventMapper;
    }

    /**
     * Save a userWaitingForValidationEvent.
     *
     * @param userWaitingForValidationEventDTO the entity to save.
     * @return the persisted entity.
     */
    public UserWaitingForValidationEventDTO save(UserWaitingForValidationEventDTO userWaitingForValidationEventDTO) {
        log.debug("Request to save UserWaitingForValidationEvent : {}", userWaitingForValidationEventDTO);
        UserWaitingForValidationEvent userWaitingForValidationEvent = userWaitingForValidationEventMapper.toEntity(
            userWaitingForValidationEventDTO
        );
        userWaitingForValidationEvent = userWaitingForValidationEventRepository.save(userWaitingForValidationEvent);
        return userWaitingForValidationEventMapper.toDto(userWaitingForValidationEvent);
    }

    /**
     * Update a userWaitingForValidationEvent.
     *
     * @param userWaitingForValidationEventDTO the entity to save.
     * @return the persisted entity.
     */
    public UserWaitingForValidationEventDTO update(UserWaitingForValidationEventDTO userWaitingForValidationEventDTO) {
        log.debug("Request to update UserWaitingForValidationEvent : {}", userWaitingForValidationEventDTO);
        UserWaitingForValidationEvent userWaitingForValidationEvent = userWaitingForValidationEventMapper.toEntity(
            userWaitingForValidationEventDTO
        );
        userWaitingForValidationEvent = userWaitingForValidationEventRepository.save(userWaitingForValidationEvent);
        return userWaitingForValidationEventMapper.toDto(userWaitingForValidationEvent);
    }

    /**
     * Partially update a userWaitingForValidationEvent.
     *
     * @param userWaitingForValidationEventDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<UserWaitingForValidationEventDTO> partialUpdate(UserWaitingForValidationEventDTO userWaitingForValidationEventDTO) {
        log.debug("Request to partially update UserWaitingForValidationEvent : {}", userWaitingForValidationEventDTO);

        return userWaitingForValidationEventRepository
            .findById(userWaitingForValidationEventDTO.getId())
            .map(existingUserWaitingForValidationEvent -> {
                userWaitingForValidationEventMapper.partialUpdate(existingUserWaitingForValidationEvent, userWaitingForValidationEventDTO);

                return existingUserWaitingForValidationEvent;
            })
            .map(userWaitingForValidationEventRepository::save)
            .map(userWaitingForValidationEventMapper::toDto);
    }

    /**
     * Get all the userWaitingForValidationEvents.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<UserWaitingForValidationEventDTO> findAll() {
        log.debug("Request to get all UserWaitingForValidationEvents");
        return userWaitingForValidationEventRepository
            .findAll()
            .stream()
            .map(userWaitingForValidationEventMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one userWaitingForValidationEvent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<UserWaitingForValidationEventDTO> findOne(Long id) {
        log.debug("Request to get UserWaitingForValidationEvent : {}", id);
        return userWaitingForValidationEventRepository.findById(id).map(userWaitingForValidationEventMapper::toDto);
    }

    /**
     * Delete the userWaitingForValidationEvent by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete UserWaitingForValidationEvent : {}", id);
        userWaitingForValidationEventRepository.deleteById(id);
    }
}
