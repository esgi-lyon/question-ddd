package org.contextmapper.generated.skillcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.skillcontext.domain.TagCreatedEvent;
import org.contextmapper.generated.skillcontext.repository.TagCreatedEventRepository;
import org.contextmapper.generated.skillcontext.service.dto.TagCreatedEventDTO;
import org.contextmapper.generated.skillcontext.service.mapper.TagCreatedEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public TagCreatedEventDTO save(TagCreatedEventDTO tagCreatedEventDTO) {
        log.debug("Request to save TagCreatedEvent : {}", tagCreatedEventDTO);
        TagCreatedEvent tagCreatedEvent = tagCreatedEventMapper.toEntity(tagCreatedEventDTO);
        tagCreatedEvent = tagCreatedEventRepository.save(tagCreatedEvent);
        return tagCreatedEventMapper.toDto(tagCreatedEvent);
    }

    /**
     * Update a tagCreatedEvent.
     *
     * @param tagCreatedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public TagCreatedEventDTO update(TagCreatedEventDTO tagCreatedEventDTO) {
        log.debug("Request to update TagCreatedEvent : {}", tagCreatedEventDTO);
        TagCreatedEvent tagCreatedEvent = tagCreatedEventMapper.toEntity(tagCreatedEventDTO);
        tagCreatedEvent = tagCreatedEventRepository.save(tagCreatedEvent);
        return tagCreatedEventMapper.toDto(tagCreatedEvent);
    }

    /**
     * Partially update a tagCreatedEvent.
     *
     * @param tagCreatedEventDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<TagCreatedEventDTO> partialUpdate(TagCreatedEventDTO tagCreatedEventDTO) {
        log.debug("Request to partially update TagCreatedEvent : {}", tagCreatedEventDTO);

        return tagCreatedEventRepository
            .findById(tagCreatedEventDTO.getId())
            .map(existingTagCreatedEvent -> {
                tagCreatedEventMapper.partialUpdate(existingTagCreatedEvent, tagCreatedEventDTO);

                return existingTagCreatedEvent;
            })
            .map(tagCreatedEventRepository::save)
            .map(tagCreatedEventMapper::toDto);
    }

    /**
     * Get all the tagCreatedEvents.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TagCreatedEventDTO> findAll() {
        log.debug("Request to get all TagCreatedEvents");
        return tagCreatedEventRepository
            .findAll()
            .stream()
            .map(tagCreatedEventMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one tagCreatedEvent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TagCreatedEventDTO> findOne(Long id) {
        log.debug("Request to get TagCreatedEvent : {}", id);
        return tagCreatedEventRepository.findById(id).map(tagCreatedEventMapper::toDto);
    }

    /**
     * Delete the tagCreatedEvent by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TagCreatedEvent : {}", id);
        tagCreatedEventRepository.deleteById(id);
    }
}
