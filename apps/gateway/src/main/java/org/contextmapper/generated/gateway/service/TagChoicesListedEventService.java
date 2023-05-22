package org.contextmapper.generated.gateway.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.contextmapper.generated.gateway.domain.TagChoicesListedEvent;
import org.contextmapper.generated.gateway.repository.TagChoicesListedEventRepository;
import org.contextmapper.generated.gateway.service.dto.TagChoicesListedEventDTO;
import org.contextmapper.generated.gateway.service.mapper.TagChoicesListedEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link TagChoicesListedEvent}.
 */
@Service
@Transactional
public class TagChoicesListedEventService {

    private final Logger log = LoggerFactory.getLogger(TagChoicesListedEventService.class);

    private final TagChoicesListedEventRepository tagChoicesListedEventRepository;

    private final TagChoicesListedEventMapper tagChoicesListedEventMapper;

    public TagChoicesListedEventService(
        TagChoicesListedEventRepository tagChoicesListedEventRepository,
        TagChoicesListedEventMapper tagChoicesListedEventMapper
    ) {
        this.tagChoicesListedEventRepository = tagChoicesListedEventRepository;
        this.tagChoicesListedEventMapper = tagChoicesListedEventMapper;
    }

    /**
     * Save a tagChoicesListedEvent.
     *
     * @param tagChoicesListedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<TagChoicesListedEventDTO> save(TagChoicesListedEventDTO tagChoicesListedEventDTO) {
        log.debug("Request to save TagChoicesListedEvent : {}", tagChoicesListedEventDTO);
        return tagChoicesListedEventRepository
            .save(tagChoicesListedEventMapper.toEntity(tagChoicesListedEventDTO))
            .map(tagChoicesListedEventMapper::toDto);
    }

    /**
     * Update a tagChoicesListedEvent.
     *
     * @param tagChoicesListedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<TagChoicesListedEventDTO> update(TagChoicesListedEventDTO tagChoicesListedEventDTO) {
        log.debug("Request to update TagChoicesListedEvent : {}", tagChoicesListedEventDTO);
        // no save call needed as we have no fields that can be updated
        return tagChoicesListedEventRepository.findById(tagChoicesListedEventDTO.getId()).map(tagChoicesListedEventMapper::toDto);
    }

    /**
     * Partially update a tagChoicesListedEvent.
     *
     * @param tagChoicesListedEventDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<TagChoicesListedEventDTO> partialUpdate(TagChoicesListedEventDTO tagChoicesListedEventDTO) {
        log.debug("Request to partially update TagChoicesListedEvent : {}", tagChoicesListedEventDTO);

        return tagChoicesListedEventRepository
            .findById(tagChoicesListedEventDTO.getId())
            .map(existingTagChoicesListedEvent -> {
                tagChoicesListedEventMapper.partialUpdate(existingTagChoicesListedEvent, tagChoicesListedEventDTO);

                return existingTagChoicesListedEvent;
            })
            // .flatMap(tagChoicesListedEventRepository::save)
            .map(tagChoicesListedEventMapper::toDto);
    }

    /**
     * Get all the tagChoicesListedEvents.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<TagChoicesListedEventDTO> findAll() {
        log.debug("Request to get all TagChoicesListedEvents");
        return tagChoicesListedEventRepository.findAll().map(tagChoicesListedEventMapper::toDto);
    }

    /**
     * Returns the number of tagChoicesListedEvents available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return tagChoicesListedEventRepository.count();
    }

    /**
     * Get one tagChoicesListedEvent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<TagChoicesListedEventDTO> findOne(Long id) {
        log.debug("Request to get TagChoicesListedEvent : {}", id);
        return tagChoicesListedEventRepository.findById(id).map(tagChoicesListedEventMapper::toDto);
    }

    /**
     * Delete the tagChoicesListedEvent by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete TagChoicesListedEvent : {}", id);
        return tagChoicesListedEventRepository.deleteById(id);
    }
}
