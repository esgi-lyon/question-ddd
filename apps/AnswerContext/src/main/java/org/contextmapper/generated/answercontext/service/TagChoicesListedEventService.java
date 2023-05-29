package org.contextmapper.generated.answercontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.answercontext.domain.TagChoicesListedEvent;
import org.contextmapper.generated.answercontext.repository.TagChoicesListedEventRepository;
import org.contextmapper.generated.answercontext.service.dto.TagChoicesListedEventDTO;
import org.contextmapper.generated.answercontext.service.mapper.TagChoicesListedEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public TagChoicesListedEventDTO save(TagChoicesListedEventDTO tagChoicesListedEventDTO) {
        log.debug("Request to save TagChoicesListedEvent : {}", tagChoicesListedEventDTO);
        TagChoicesListedEvent tagChoicesListedEvent = tagChoicesListedEventMapper.toEntity(tagChoicesListedEventDTO);
        tagChoicesListedEvent = tagChoicesListedEventRepository.save(tagChoicesListedEvent);
        return tagChoicesListedEventMapper.toDto(tagChoicesListedEvent);
    }

    /**
     * Update a tagChoicesListedEvent.
     *
     * @param tagChoicesListedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public TagChoicesListedEventDTO update(TagChoicesListedEventDTO tagChoicesListedEventDTO) {
        log.debug("Request to update TagChoicesListedEvent : {}", tagChoicesListedEventDTO);
        TagChoicesListedEvent tagChoicesListedEvent = tagChoicesListedEventMapper.toEntity(tagChoicesListedEventDTO);
        tagChoicesListedEvent = tagChoicesListedEventRepository.save(tagChoicesListedEvent);
        return tagChoicesListedEventMapper.toDto(tagChoicesListedEvent);
    }

    /**
     * Partially update a tagChoicesListedEvent.
     *
     * @param tagChoicesListedEventDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<TagChoicesListedEventDTO> partialUpdate(TagChoicesListedEventDTO tagChoicesListedEventDTO) {
        log.debug("Request to partially update TagChoicesListedEvent : {}", tagChoicesListedEventDTO);

        return tagChoicesListedEventRepository
            .findById(tagChoicesListedEventDTO.getId())
            .map(existingTagChoicesListedEvent -> {
                tagChoicesListedEventMapper.partialUpdate(existingTagChoicesListedEvent, tagChoicesListedEventDTO);

                return existingTagChoicesListedEvent;
            })
            .map(tagChoicesListedEventRepository::save)
            .map(tagChoicesListedEventMapper::toDto);
    }

    /**
     * Get all the tagChoicesListedEvents.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TagChoicesListedEventDTO> findAll() {
        log.debug("Request to get all TagChoicesListedEvents");
        return tagChoicesListedEventRepository
            .findAll()
            .stream()
            .map(tagChoicesListedEventMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one tagChoicesListedEvent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TagChoicesListedEventDTO> findOne(Long id) {
        log.debug("Request to get TagChoicesListedEvent : {}", id);
        return tagChoicesListedEventRepository.findById(id).map(tagChoicesListedEventMapper::toDto);
    }

    /**
     * Delete the tagChoicesListedEvent by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TagChoicesListedEvent : {}", id);
        tagChoicesListedEventRepository.deleteById(id);
    }
}
