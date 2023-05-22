package org.contextmapper.generated.gateway.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.contextmapper.generated.gateway.domain.TagStatsViewedEvent;
import org.contextmapper.generated.gateway.repository.TagStatsViewedEventRepository;
import org.contextmapper.generated.gateway.service.dto.TagStatsViewedEventDTO;
import org.contextmapper.generated.gateway.service.mapper.TagStatsViewedEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    public Mono<TagStatsViewedEventDTO> save(TagStatsViewedEventDTO tagStatsViewedEventDTO) {
        log.debug("Request to save TagStatsViewedEvent : {}", tagStatsViewedEventDTO);
        return tagStatsViewedEventRepository
            .save(tagStatsViewedEventMapper.toEntity(tagStatsViewedEventDTO))
            .map(tagStatsViewedEventMapper::toDto);
    }

    /**
     * Update a tagStatsViewedEvent.
     *
     * @param tagStatsViewedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<TagStatsViewedEventDTO> update(TagStatsViewedEventDTO tagStatsViewedEventDTO) {
        log.debug("Request to update TagStatsViewedEvent : {}", tagStatsViewedEventDTO);
        return tagStatsViewedEventRepository
            .save(tagStatsViewedEventMapper.toEntity(tagStatsViewedEventDTO))
            .map(tagStatsViewedEventMapper::toDto);
    }

    /**
     * Partially update a tagStatsViewedEvent.
     *
     * @param tagStatsViewedEventDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<TagStatsViewedEventDTO> partialUpdate(TagStatsViewedEventDTO tagStatsViewedEventDTO) {
        log.debug("Request to partially update TagStatsViewedEvent : {}", tagStatsViewedEventDTO);

        return tagStatsViewedEventRepository
            .findById(tagStatsViewedEventDTO.getId())
            .map(existingTagStatsViewedEvent -> {
                tagStatsViewedEventMapper.partialUpdate(existingTagStatsViewedEvent, tagStatsViewedEventDTO);

                return existingTagStatsViewedEvent;
            })
            .flatMap(tagStatsViewedEventRepository::save)
            .map(tagStatsViewedEventMapper::toDto);
    }

    /**
     * Get all the tagStatsViewedEvents.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<TagStatsViewedEventDTO> findAll() {
        log.debug("Request to get all TagStatsViewedEvents");
        return tagStatsViewedEventRepository.findAll().map(tagStatsViewedEventMapper::toDto);
    }

    /**
     * Returns the number of tagStatsViewedEvents available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return tagStatsViewedEventRepository.count();
    }

    /**
     * Get one tagStatsViewedEvent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<TagStatsViewedEventDTO> findOne(Long id) {
        log.debug("Request to get TagStatsViewedEvent : {}", id);
        return tagStatsViewedEventRepository.findById(id).map(tagStatsViewedEventMapper::toDto);
    }

    /**
     * Delete the tagStatsViewedEvent by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete TagStatsViewedEvent : {}", id);
        return tagStatsViewedEventRepository.deleteById(id);
    }
}
