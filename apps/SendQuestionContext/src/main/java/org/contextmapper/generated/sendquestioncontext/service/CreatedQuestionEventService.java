package org.contextmapper.generated.sendquestioncontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.sendquestioncontext.domain.CreatedQuestionEvent;
import org.contextmapper.generated.sendquestioncontext.repository.CreatedQuestionEventRepository;
import org.contextmapper.generated.sendquestioncontext.service.dto.CreatedQuestionEventDTO;
import org.contextmapper.generated.sendquestioncontext.service.mapper.CreatedQuestionEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link CreatedQuestionEvent}.
 */
@Service
@Transactional
public class CreatedQuestionEventService {

    private final Logger log = LoggerFactory.getLogger(CreatedQuestionEventService.class);

    private final CreatedQuestionEventRepository createdQuestionEventRepository;

    private final CreatedQuestionEventMapper createdQuestionEventMapper;

    public CreatedQuestionEventService(
        CreatedQuestionEventRepository createdQuestionEventRepository,
        CreatedQuestionEventMapper createdQuestionEventMapper
    ) {
        this.createdQuestionEventRepository = createdQuestionEventRepository;
        this.createdQuestionEventMapper = createdQuestionEventMapper;
    }

    /**
     * Save a createdQuestionEvent.
     *
     * @param createdQuestionEventDTO the entity to save.
     * @return the persisted entity.
     */
    public CreatedQuestionEventDTO save(CreatedQuestionEventDTO createdQuestionEventDTO) {
        log.debug("Request to save CreatedQuestionEvent : {}", createdQuestionEventDTO);
        CreatedQuestionEvent createdQuestionEvent = createdQuestionEventMapper.toEntity(createdQuestionEventDTO);
        createdQuestionEvent = createdQuestionEventRepository.save(createdQuestionEvent);
        return createdQuestionEventMapper.toDto(createdQuestionEvent);
    }

    /**
     * Update a createdQuestionEvent.
     *
     * @param createdQuestionEventDTO the entity to save.
     * @return the persisted entity.
     */
    public CreatedQuestionEventDTO update(CreatedQuestionEventDTO createdQuestionEventDTO) {
        log.debug("Request to update CreatedQuestionEvent : {}", createdQuestionEventDTO);
        CreatedQuestionEvent createdQuestionEvent = createdQuestionEventMapper.toEntity(createdQuestionEventDTO);
        createdQuestionEvent = createdQuestionEventRepository.save(createdQuestionEvent);
        return createdQuestionEventMapper.toDto(createdQuestionEvent);
    }

    /**
     * Partially update a createdQuestionEvent.
     *
     * @param createdQuestionEventDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CreatedQuestionEventDTO> partialUpdate(CreatedQuestionEventDTO createdQuestionEventDTO) {
        log.debug("Request to partially update CreatedQuestionEvent : {}", createdQuestionEventDTO);

        return createdQuestionEventRepository
            .findById(createdQuestionEventDTO.getId())
            .map(existingCreatedQuestionEvent -> {
                createdQuestionEventMapper.partialUpdate(existingCreatedQuestionEvent, createdQuestionEventDTO);

                return existingCreatedQuestionEvent;
            })
            .map(createdQuestionEventRepository::save)
            .map(createdQuestionEventMapper::toDto);
    }

    /**
     * Get all the createdQuestionEvents.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CreatedQuestionEventDTO> findAll() {
        log.debug("Request to get all CreatedQuestionEvents");
        return createdQuestionEventRepository
            .findAll()
            .stream()
            .map(createdQuestionEventMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one createdQuestionEvent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CreatedQuestionEventDTO> findOne(Long id) {
        log.debug("Request to get CreatedQuestionEvent : {}", id);
        return createdQuestionEventRepository.findById(id).map(createdQuestionEventMapper::toDto);
    }

    /**
     * Delete the createdQuestionEvent by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CreatedQuestionEvent : {}", id);
        createdQuestionEventRepository.deleteById(id);
    }
}
