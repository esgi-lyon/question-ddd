package org.contextmapper.generated.statcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.statcontext.domain.TagStatsViewedEvent;
import org.contextmapper.generated.statcontext.repository.TagStatsViewedEventRepository;
import org.contextmapper.generated.statcontext.service.dto.TagStatsViewedEventDTO;
import org.contextmapper.generated.statcontext.service.mapper.TagStatsViewedEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TagStatsViewedEvent}.
 */
@Service
@Transactional
public class TagStatsViewedEventService {

    private final Logger log = LoggerFactory.getLogger(TagStatsViewedEventService.class);

    private final TagStatsViewedEventRepository tagStatsViewedEventRepository;

    private final TagStatsViewedEventMapper tagStatsViewedEventMapper;

    public TagStatsViewedEventService(
        TagStatsViewedEventRepository tagStatsViewedEventRepository,
        TagStatsViewedEventMapper tagStatsViewedEventMapper
    ) {
        this.tagStatsViewedEventRepository = tagStatsViewedEventRepository;
        this.tagStatsViewedEventMapper = tagStatsViewedEventMapper;
    }

    /**
     * Save a tagStatsViewedEvent.
     *
     * @param tagStatsViewedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public TagStatsViewedEventDTO save(TagStatsViewedEventDTO tagStatsViewedEventDTO) {
        log.debug("Request to save TagStatsViewedEvent : {}", tagStatsViewedEventDTO);
        TagStatsViewedEvent tagStatsViewedEvent = tagStatsViewedEventMapper.toEntity(tagStatsViewedEventDTO);
        tagStatsViewedEvent = tagStatsViewedEventRepository.save(tagStatsViewedEvent);
        return tagStatsViewedEventMapper.toDto(tagStatsViewedEvent);
    }

    /**
     * Update a tagStatsViewedEvent.
     *
     * @param tagStatsViewedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public TagStatsViewedEventDTO update(TagStatsViewedEventDTO tagStatsViewedEventDTO) {
        log.debug("Request to update TagStatsViewedEvent : {}", tagStatsViewedEventDTO);
        TagStatsViewedEvent tagStatsViewedEvent = tagStatsViewedEventMapper.toEntity(tagStatsViewedEventDTO);
        // no save call needed as we have no fields that can be updated
        return tagStatsViewedEventMapper.toDto(tagStatsViewedEvent);
    }

    /**
     * Partially update a tagStatsViewedEvent.
     *
     * @param tagStatsViewedEventDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<TagStatsViewedEventDTO> partialUpdate(TagStatsViewedEventDTO tagStatsViewedEventDTO) {
        log.debug("Request to partially update TagStatsViewedEvent : {}", tagStatsViewedEventDTO);

        return tagStatsViewedEventRepository
            .findById(tagStatsViewedEventDTO.getId())
            .map(existingTagStatsViewedEvent -> {
                tagStatsViewedEventMapper.partialUpdate(existingTagStatsViewedEvent, tagStatsViewedEventDTO);

                return existingTagStatsViewedEvent;
            })
            // .map(tagStatsViewedEventRepository::save)
            .map(tagStatsViewedEventMapper::toDto);
    }

    /**
     * Get all the tagStatsViewedEvents.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TagStatsViewedEventDTO> findAll() {
        log.debug("Request to get all TagStatsViewedEvents");
        return tagStatsViewedEventRepository
            .findAll()
            .stream()
            .map(tagStatsViewedEventMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one tagStatsViewedEvent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TagStatsViewedEventDTO> findOne(Long id) {
        log.debug("Request to get TagStatsViewedEvent : {}", id);
        return tagStatsViewedEventRepository.findById(id).map(tagStatsViewedEventMapper::toDto);
    }

    /**
     * Delete the tagStatsViewedEvent by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TagStatsViewedEvent : {}", id);
        tagStatsViewedEventRepository.deleteById(id);
    }
}
