package org.contextmapper.generated.sendquestioncontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.sendquestioncontext.domain.PreferencesAddedEvent;
import org.contextmapper.generated.sendquestioncontext.repository.PreferencesAddedEventRepository;
import org.contextmapper.generated.sendquestioncontext.service.dto.PreferencesAddedEventDTO;
import org.contextmapper.generated.sendquestioncontext.service.mapper.PreferencesAddedEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link PreferencesAddedEvent}.
 */
@Service
@Transactional
public class PreferencesAddedEventService {

    private final Logger log = LoggerFactory.getLogger(PreferencesAddedEventService.class);

    private final PreferencesAddedEventRepository preferencesAddedEventRepository;

    private final PreferencesAddedEventMapper preferencesAddedEventMapper;

    public PreferencesAddedEventService(
        PreferencesAddedEventRepository preferencesAddedEventRepository,
        PreferencesAddedEventMapper preferencesAddedEventMapper
    ) {
        this.preferencesAddedEventRepository = preferencesAddedEventRepository;
        this.preferencesAddedEventMapper = preferencesAddedEventMapper;
    }

    /**
     * Save a preferencesAddedEvent.
     *
     * @param preferencesAddedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public PreferencesAddedEventDTO save(PreferencesAddedEventDTO preferencesAddedEventDTO) {
        log.debug("Request to save PreferencesAddedEvent : {}", preferencesAddedEventDTO);
        PreferencesAddedEvent preferencesAddedEvent = preferencesAddedEventMapper.toEntity(preferencesAddedEventDTO);
        preferencesAddedEvent = preferencesAddedEventRepository.save(preferencesAddedEvent);
        return preferencesAddedEventMapper.toDto(preferencesAddedEvent);
    }

    /**
     * Update a preferencesAddedEvent.
     *
     * @param preferencesAddedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public PreferencesAddedEventDTO update(PreferencesAddedEventDTO preferencesAddedEventDTO) {
        log.debug("Request to update PreferencesAddedEvent : {}", preferencesAddedEventDTO);
        PreferencesAddedEvent preferencesAddedEvent = preferencesAddedEventMapper.toEntity(preferencesAddedEventDTO);
        preferencesAddedEvent = preferencesAddedEventRepository.save(preferencesAddedEvent);
        return preferencesAddedEventMapper.toDto(preferencesAddedEvent);
    }

    /**
     * Partially update a preferencesAddedEvent.
     *
     * @param preferencesAddedEventDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<PreferencesAddedEventDTO> partialUpdate(PreferencesAddedEventDTO preferencesAddedEventDTO) {
        log.debug("Request to partially update PreferencesAddedEvent : {}", preferencesAddedEventDTO);

        return preferencesAddedEventRepository
            .findById(preferencesAddedEventDTO.getId())
            .map(existingPreferencesAddedEvent -> {
                preferencesAddedEventMapper.partialUpdate(existingPreferencesAddedEvent, preferencesAddedEventDTO);

                return existingPreferencesAddedEvent;
            })
            .map(preferencesAddedEventRepository::save)
            .map(preferencesAddedEventMapper::toDto);
    }

    /**
     * Get all the preferencesAddedEvents.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PreferencesAddedEventDTO> findAll() {
        log.debug("Request to get all PreferencesAddedEvents");
        return preferencesAddedEventRepository
            .findAll()
            .stream()
            .map(preferencesAddedEventMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one preferencesAddedEvent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PreferencesAddedEventDTO> findOne(Long id) {
        log.debug("Request to get PreferencesAddedEvent : {}", id);
        return preferencesAddedEventRepository.findById(id).map(preferencesAddedEventMapper::toDto);
    }

    /**
     * Delete the preferencesAddedEvent by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PreferencesAddedEvent : {}", id);
        preferencesAddedEventRepository.deleteById(id);
    }
}
