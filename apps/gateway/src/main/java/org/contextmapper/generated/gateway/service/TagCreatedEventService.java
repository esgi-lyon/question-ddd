package org.contextmapper.generated.gateway.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.contextmapper.generated.gateway.domain.TagCreatedEvent;
import org.contextmapper.generated.gateway.repository.TagCreatedEventRepository;
import org.contextmapper.generated.gateway.service.dto.TagCreatedEventDTO;
import org.contextmapper.generated.gateway.service.mapper.TagCreatedEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link TagCreatedEvent}.
 */
@Service
@Transactional
public class TagCreatedEventService {

    private final Logger log = LoggerFactory.getLogger(TagCreatedEventService.class);

    private final TagCreatedEventRepository tagCreatedEventRepository;

    private final TagCreatedEventMapper tagCreatedEventMapper;

    public TagCreatedEventService(TagCreatedEventRepository tagCreatedEventRepository, TagCreatedEventMapper tagCreatedEventMapper) {
        this.tagCreatedEventRepository = tagCreatedEventRepository;
        this.tagCreatedEventMapper = tagCreatedEventMapper;
    }

    /**
     * Save a tagCreatedEvent.
     *
     * @param tagCreatedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<TagCreatedEventDTO> save(TagCreatedEventDTO tagCreatedEventDTO) {
        log.debug("Request to save TagCreatedEvent : {}", tagCreatedEventDTO);
        return tagCreatedEventRepository.save(tagCreatedEventMapper.toEntity(tagCreatedEventDTO)).map(tagCreatedEventMapper::toDto);
    }

    /**
     * Update a tagCreatedEvent.
     *
     * @param tagCreatedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<TagCreatedEventDTO> update(TagCreatedEventDTO tagCreatedEventDTO) {
        log.debug("Request to update TagCreatedEvent : {}", tagCreatedEventDTO);
        return tagCreatedEventRepository.save(tagCreatedEventMapper.toEntity(tagCreatedEventDTO)).map(tagCreatedEventMapper::toDto);
    }

    /**
     * Partially update a tagCreatedEvent.
     *
     * @param tagCreatedEventDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<TagCreatedEventDTO> partialUpdate(TagCreatedEventDTO tagCreatedEventDTO) {
        log.debug("Request to partially update TagCreatedEvent : {}", tagCreatedEventDTO);

        return tagCreatedEventRepository
            .findById(tagCreatedEventDTO.getId())
            .map(existingTagCreatedEvent -> {
                tagCreatedEventMapper.partialUpdate(existingTagCreatedEvent, tagCreatedEventDTO);

                return existingTagCreatedEvent;
            })
            .flatMap(tagCreatedEventRepository::save)
            .map(tagCreatedEventMapper::toDto);
    }

    /**
     * Get all the tagCreatedEvents.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<TagCreatedEventDTO> findAll() {
        log.debug("Request to get all TagCreatedEvents");
        return tagCreatedEventRepository.findAll().map(tagCreatedEventMapper::toDto);
    }

    /**
     * Returns the number of tagCreatedEvents available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return tagCreatedEventRepository.count();
    }

    /**
     * Get one tagCreatedEvent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<TagCreatedEventDTO> findOne(Long id) {
        log.debug("Request to get TagCreatedEvent : {}", id);
        return tagCreatedEventRepository.findById(id).map(tagCreatedEventMapper::toDto);
    }

    /**
     * Delete the tagCreatedEvent by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete TagCreatedEvent : {}", id);
        return tagCreatedEventRepository.deleteById(id);
    }
}
