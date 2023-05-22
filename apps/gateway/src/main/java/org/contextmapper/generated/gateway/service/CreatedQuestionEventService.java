package org.contextmapper.generated.gateway.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.contextmapper.generated.gateway.domain.CreatedQuestionEvent;
import org.contextmapper.generated.gateway.repository.CreatedQuestionEventRepository;
import org.contextmapper.generated.gateway.service.dto.CreatedQuestionEventDTO;
import org.contextmapper.generated.gateway.service.mapper.CreatedQuestionEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    public Mono<CreatedQuestionEventDTO> save(CreatedQuestionEventDTO createdQuestionEventDTO) {
        log.debug("Request to save CreatedQuestionEvent : {}", createdQuestionEventDTO);
        return createdQuestionEventRepository
            .save(createdQuestionEventMapper.toEntity(createdQuestionEventDTO))
            .map(createdQuestionEventMapper::toDto);
    }

    /**
     * Update a createdQuestionEvent.
     *
     * @param createdQuestionEventDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<CreatedQuestionEventDTO> update(CreatedQuestionEventDTO createdQuestionEventDTO) {
        log.debug("Request to update CreatedQuestionEvent : {}", createdQuestionEventDTO);
        return createdQuestionEventRepository
            .save(createdQuestionEventMapper.toEntity(createdQuestionEventDTO))
            .map(createdQuestionEventMapper::toDto);
    }

    /**
     * Partially update a createdQuestionEvent.
     *
     * @param createdQuestionEventDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<CreatedQuestionEventDTO> partialUpdate(CreatedQuestionEventDTO createdQuestionEventDTO) {
        log.debug("Request to partially update CreatedQuestionEvent : {}", createdQuestionEventDTO);

        return createdQuestionEventRepository
            .findById(createdQuestionEventDTO.getId())
            .map(existingCreatedQuestionEvent -> {
                createdQuestionEventMapper.partialUpdate(existingCreatedQuestionEvent, createdQuestionEventDTO);

                return existingCreatedQuestionEvent;
            })
            .flatMap(createdQuestionEventRepository::save)
            .map(createdQuestionEventMapper::toDto);
    }

    /**
     * Get all the createdQuestionEvents.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<CreatedQuestionEventDTO> findAll() {
        log.debug("Request to get all CreatedQuestionEvents");
        return createdQuestionEventRepository.findAll().map(createdQuestionEventMapper::toDto);
    }

    /**
     * Returns the number of createdQuestionEvents available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return createdQuestionEventRepository.count();
    }

    /**
     * Get one createdQuestionEvent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<CreatedQuestionEventDTO> findOne(Long id) {
        log.debug("Request to get CreatedQuestionEvent : {}", id);
        return createdQuestionEventRepository.findById(id).map(createdQuestionEventMapper::toDto);
    }

    /**
     * Delete the createdQuestionEvent by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete CreatedQuestionEvent : {}", id);
        return createdQuestionEventRepository.deleteById(id);
    }
}
