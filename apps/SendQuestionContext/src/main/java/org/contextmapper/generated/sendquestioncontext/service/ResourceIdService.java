package org.contextmapper.generated.sendquestioncontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.sendquestioncontext.domain.ResourceId;
import org.contextmapper.generated.sendquestioncontext.repository.ResourceIdRepository;
import org.contextmapper.generated.sendquestioncontext.service.dto.ResourceIdDTO;
import org.contextmapper.generated.sendquestioncontext.service.mapper.ResourceIdMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ResourceId}.
 */
@Service
@Transactional
public class ResourceIdService {

    private final Logger log = LoggerFactory.getLogger(ResourceIdService.class);

    private final ResourceIdRepository resourceIdRepository;

    private final ResourceIdMapper resourceIdMapper;

    public ResourceIdService(ResourceIdRepository resourceIdRepository, ResourceIdMapper resourceIdMapper) {
        this.resourceIdRepository = resourceIdRepository;
        this.resourceIdMapper = resourceIdMapper;
    }

    /**
     * Save a resourceId.
     *
     * @param resourceIdDTO the entity to save.
     * @return the persisted entity.
     */
    public ResourceIdDTO save(ResourceIdDTO resourceIdDTO) {
        log.debug("Request to save ResourceId : {}", resourceIdDTO);
        ResourceId resourceId = resourceIdMapper.toEntity(resourceIdDTO);
        resourceId = resourceIdRepository.save(resourceId);
        return resourceIdMapper.toDto(resourceId);
    }

    /**
     * Update a resourceId.
     *
     * @param resourceIdDTO the entity to save.
     * @return the persisted entity.
     */
    public ResourceIdDTO update(ResourceIdDTO resourceIdDTO) {
        log.debug("Request to update ResourceId : {}", resourceIdDTO);
        ResourceId resourceId = resourceIdMapper.toEntity(resourceIdDTO);
        // no save call needed as we have no fields that can be updated
        return resourceIdMapper.toDto(resourceId);
    }

    /**
     * Partially update a resourceId.
     *
     * @param resourceIdDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ResourceIdDTO> partialUpdate(ResourceIdDTO resourceIdDTO) {
        log.debug("Request to partially update ResourceId : {}", resourceIdDTO);

        return resourceIdRepository
            .findById(resourceIdDTO.getId())
            .map(existingResourceId -> {
                resourceIdMapper.partialUpdate(existingResourceId, resourceIdDTO);

                return existingResourceId;
            })
            // .map(resourceIdRepository::save)
            .map(resourceIdMapper::toDto);
    }

    /**
     * Get all the resourceIds.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ResourceIdDTO> findAll() {
        log.debug("Request to get all ResourceIds");
        return resourceIdRepository.findAll().stream().map(resourceIdMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one resourceId by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ResourceIdDTO> findOne(Long id) {
        log.debug("Request to get ResourceId : {}", id);
        return resourceIdRepository.findById(id).map(resourceIdMapper::toDto);
    }

    /**
     * Delete the resourceId by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ResourceId : {}", id);
        resourceIdRepository.deleteById(id);
    }
}
