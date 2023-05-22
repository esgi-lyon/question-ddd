package org.contextmapper.generated.gateway.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.contextmapper.generated.gateway.domain.ResourceRefusedAssociationEvent;
import org.contextmapper.generated.gateway.repository.ResourceRefusedAssociationEventRepository;
import org.contextmapper.generated.gateway.service.dto.ResourceRefusedAssociationEventDTO;
import org.contextmapper.generated.gateway.service.mapper.ResourceRefusedAssociationEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link ResourceRefusedAssociationEvent}.
 */
@Service
@Transactional
public class ResourceRefusedAssociationEventService {

    private final Logger log = LoggerFactory.getLogger(ResourceRefusedAssociationEventService.class);

    private final ResourceRefusedAssociationEventRepository resourceRefusedAssociationEventRepository;

    private final ResourceRefusedAssociationEventMapper resourceRefusedAssociationEventMapper;

    public ResourceRefusedAssociationEventService(
        ResourceRefusedAssociationEventRepository resourceRefusedAssociationEventRepository,
        ResourceRefusedAssociationEventMapper resourceRefusedAssociationEventMapper
    ) {
        this.resourceRefusedAssociationEventRepository = resourceRefusedAssociationEventRepository;
        this.resourceRefusedAssociationEventMapper = resourceRefusedAssociationEventMapper;
    }

    /**
     * Save a resourceRefusedAssociationEvent.
     *
     * @param resourceRefusedAssociationEventDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<ResourceRefusedAssociationEventDTO> save(ResourceRefusedAssociationEventDTO resourceRefusedAssociationEventDTO) {
        log.debug("Request to save ResourceRefusedAssociationEvent : {}", resourceRefusedAssociationEventDTO);
        return resourceRefusedAssociationEventRepository
            .save(resourceRefusedAssociationEventMapper.toEntity(resourceRefusedAssociationEventDTO))
            .map(resourceRefusedAssociationEventMapper::toDto);
    }

    /**
     * Update a resourceRefusedAssociationEvent.
     *
     * @param resourceRefusedAssociationEventDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<ResourceRefusedAssociationEventDTO> update(ResourceRefusedAssociationEventDTO resourceRefusedAssociationEventDTO) {
        log.debug("Request to update ResourceRefusedAssociationEvent : {}", resourceRefusedAssociationEventDTO);
        return resourceRefusedAssociationEventRepository
            .save(resourceRefusedAssociationEventMapper.toEntity(resourceRefusedAssociationEventDTO))
            .map(resourceRefusedAssociationEventMapper::toDto);
    }

    /**
     * Partially update a resourceRefusedAssociationEvent.
     *
     * @param resourceRefusedAssociationEventDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<ResourceRefusedAssociationEventDTO> partialUpdate(ResourceRefusedAssociationEventDTO resourceRefusedAssociationEventDTO) {
        log.debug("Request to partially update ResourceRefusedAssociationEvent : {}", resourceRefusedAssociationEventDTO);

        return resourceRefusedAssociationEventRepository
            .findById(resourceRefusedAssociationEventDTO.getId())
            .map(existingResourceRefusedAssociationEvent -> {
                resourceRefusedAssociationEventMapper.partialUpdate(
                    existingResourceRefusedAssociationEvent,
                    resourceRefusedAssociationEventDTO
                );

                return existingResourceRefusedAssociationEvent;
            })
            .flatMap(resourceRefusedAssociationEventRepository::save)
            .map(resourceRefusedAssociationEventMapper::toDto);
    }

    /**
     * Get all the resourceRefusedAssociationEvents.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<ResourceRefusedAssociationEventDTO> findAll() {
        log.debug("Request to get all ResourceRefusedAssociationEvents");
        return resourceRefusedAssociationEventRepository.findAll().map(resourceRefusedAssociationEventMapper::toDto);
    }

    /**
     * Returns the number of resourceRefusedAssociationEvents available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return resourceRefusedAssociationEventRepository.count();
    }

    /**
     * Get one resourceRefusedAssociationEvent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<ResourceRefusedAssociationEventDTO> findOne(Long id) {
        log.debug("Request to get ResourceRefusedAssociationEvent : {}", id);
        return resourceRefusedAssociationEventRepository.findById(id).map(resourceRefusedAssociationEventMapper::toDto);
    }

    /**
     * Delete the resourceRefusedAssociationEvent by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete ResourceRefusedAssociationEvent : {}", id);
        return resourceRefusedAssociationEventRepository.deleteById(id);
    }
}
