package org.contextmapper.generated.evaluationcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.evaluationcontext.domain.UserEvaluationViewedEvent;
import org.contextmapper.generated.evaluationcontext.repository.UserEvaluationViewedEventRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.UserEvaluationViewedEventDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.UserEvaluationViewedEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link UserEvaluationViewedEvent}.
 */
@Service
@Transactional
public class UserEvaluationViewedEventService {

    private final Logger log = LoggerFactory.getLogger(UserEvaluationViewedEventService.class);

    private final UserEvaluationViewedEventRepository userEvaluationViewedEventRepository;

    private final UserEvaluationViewedEventMapper userEvaluationViewedEventMapper;

    public UserEvaluationViewedEventService(
        UserEvaluationViewedEventRepository userEvaluationViewedEventRepository,
        UserEvaluationViewedEventMapper userEvaluationViewedEventMapper
    ) {
        this.userEvaluationViewedEventRepository = userEvaluationViewedEventRepository;
        this.userEvaluationViewedEventMapper = userEvaluationViewedEventMapper;
    }

    /**
     * Save a userEvaluationViewedEvent.
     *
     * @param userEvaluationViewedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public UserEvaluationViewedEventDTO save(UserEvaluationViewedEventDTO userEvaluationViewedEventDTO) {
        log.debug("Request to save UserEvaluationViewedEvent : {}", userEvaluationViewedEventDTO);
        UserEvaluationViewedEvent userEvaluationViewedEvent = userEvaluationViewedEventMapper.toEntity(userEvaluationViewedEventDTO);
        userEvaluationViewedEvent = userEvaluationViewedEventRepository.save(userEvaluationViewedEvent);
        return userEvaluationViewedEventMapper.toDto(userEvaluationViewedEvent);
    }

    /**
     * Update a userEvaluationViewedEvent.
     *
     * @param userEvaluationViewedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public UserEvaluationViewedEventDTO update(UserEvaluationViewedEventDTO userEvaluationViewedEventDTO) {
        log.debug("Request to update UserEvaluationViewedEvent : {}", userEvaluationViewedEventDTO);
        UserEvaluationViewedEvent userEvaluationViewedEvent = userEvaluationViewedEventMapper.toEntity(userEvaluationViewedEventDTO);
        userEvaluationViewedEvent = userEvaluationViewedEventRepository.save(userEvaluationViewedEvent);
        return userEvaluationViewedEventMapper.toDto(userEvaluationViewedEvent);
    }

    /**
     * Partially update a userEvaluationViewedEvent.
     *
     * @param userEvaluationViewedEventDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<UserEvaluationViewedEventDTO> partialUpdate(UserEvaluationViewedEventDTO userEvaluationViewedEventDTO) {
        log.debug("Request to partially update UserEvaluationViewedEvent : {}", userEvaluationViewedEventDTO);

        return userEvaluationViewedEventRepository
            .findById(userEvaluationViewedEventDTO.getId())
            .map(existingUserEvaluationViewedEvent -> {
                userEvaluationViewedEventMapper.partialUpdate(existingUserEvaluationViewedEvent, userEvaluationViewedEventDTO);

                return existingUserEvaluationViewedEvent;
            })
            .map(userEvaluationViewedEventRepository::save)
            .map(userEvaluationViewedEventMapper::toDto);
    }

    /**
     * Get all the userEvaluationViewedEvents.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<UserEvaluationViewedEventDTO> findAll() {
        log.debug("Request to get all UserEvaluationViewedEvents");
        return userEvaluationViewedEventRepository
            .findAll()
            .stream()
            .map(userEvaluationViewedEventMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one userEvaluationViewedEvent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<UserEvaluationViewedEventDTO> findOne(Long id) {
        log.debug("Request to get UserEvaluationViewedEvent : {}", id);
        return userEvaluationViewedEventRepository.findById(id).map(userEvaluationViewedEventMapper::toDto);
    }

    /**
     * Delete the userEvaluationViewedEvent by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete UserEvaluationViewedEvent : {}", id);
        userEvaluationViewedEventRepository.deleteById(id);
    }
}
