package org.contextmapper.generated.evaluationcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.evaluationcontext.domain.TagEvaluationViewedEvent;
import org.contextmapper.generated.evaluationcontext.repository.TagEvaluationViewedEventRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.TagEvaluationViewedEventDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.TagEvaluationViewedEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TagEvaluationViewedEvent}.
 */
@Service
@Transactional
public class TagEvaluationViewedEventService {

    private final Logger log = LoggerFactory.getLogger(TagEvaluationViewedEventService.class);

    private final TagEvaluationViewedEventRepository tagEvaluationViewedEventRepository;

    private final TagEvaluationViewedEventMapper tagEvaluationViewedEventMapper;

    public TagEvaluationViewedEventService(
        TagEvaluationViewedEventRepository tagEvaluationViewedEventRepository,
        TagEvaluationViewedEventMapper tagEvaluationViewedEventMapper
    ) {
        this.tagEvaluationViewedEventRepository = tagEvaluationViewedEventRepository;
        this.tagEvaluationViewedEventMapper = tagEvaluationViewedEventMapper;
    }

    /**
     * Save a tagEvaluationViewedEvent.
     *
     * @param tagEvaluationViewedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public TagEvaluationViewedEventDTO save(TagEvaluationViewedEventDTO tagEvaluationViewedEventDTO) {
        log.debug("Request to save TagEvaluationViewedEvent : {}", tagEvaluationViewedEventDTO);
        TagEvaluationViewedEvent tagEvaluationViewedEvent = tagEvaluationViewedEventMapper.toEntity(tagEvaluationViewedEventDTO);
        tagEvaluationViewedEvent = tagEvaluationViewedEventRepository.save(tagEvaluationViewedEvent);
        return tagEvaluationViewedEventMapper.toDto(tagEvaluationViewedEvent);
    }

    /**
     * Update a tagEvaluationViewedEvent.
     *
     * @param tagEvaluationViewedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public TagEvaluationViewedEventDTO update(TagEvaluationViewedEventDTO tagEvaluationViewedEventDTO) {
        log.debug("Request to update TagEvaluationViewedEvent : {}", tagEvaluationViewedEventDTO);
        TagEvaluationViewedEvent tagEvaluationViewedEvent = tagEvaluationViewedEventMapper.toEntity(tagEvaluationViewedEventDTO);
        tagEvaluationViewedEvent = tagEvaluationViewedEventRepository.save(tagEvaluationViewedEvent);
        return tagEvaluationViewedEventMapper.toDto(tagEvaluationViewedEvent);
    }

    /**
     * Partially update a tagEvaluationViewedEvent.
     *
     * @param tagEvaluationViewedEventDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<TagEvaluationViewedEventDTO> partialUpdate(TagEvaluationViewedEventDTO tagEvaluationViewedEventDTO) {
        log.debug("Request to partially update TagEvaluationViewedEvent : {}", tagEvaluationViewedEventDTO);

        return tagEvaluationViewedEventRepository
            .findById(tagEvaluationViewedEventDTO.getId())
            .map(existingTagEvaluationViewedEvent -> {
                tagEvaluationViewedEventMapper.partialUpdate(existingTagEvaluationViewedEvent, tagEvaluationViewedEventDTO);

                return existingTagEvaluationViewedEvent;
            })
            .map(tagEvaluationViewedEventRepository::save)
            .map(tagEvaluationViewedEventMapper::toDto);
    }

    /**
     * Get all the tagEvaluationViewedEvents.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TagEvaluationViewedEventDTO> findAll() {
        log.debug("Request to get all TagEvaluationViewedEvents");
        return tagEvaluationViewedEventRepository
            .findAll()
            .stream()
            .map(tagEvaluationViewedEventMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one tagEvaluationViewedEvent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TagEvaluationViewedEventDTO> findOne(Long id) {
        log.debug("Request to get TagEvaluationViewedEvent : {}", id);
        return tagEvaluationViewedEventRepository.findById(id).map(tagEvaluationViewedEventMapper::toDto);
    }

    /**
     * Delete the tagEvaluationViewedEvent by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TagEvaluationViewedEvent : {}", id);
        tagEvaluationViewedEventRepository.deleteById(id);
    }
}
