package org.contextmapper.generated.questioncontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.questioncontext.domain.CreateResource;
import org.contextmapper.generated.questioncontext.repository.CreateResourceRepository;
import org.contextmapper.generated.questioncontext.service.dto.CreateResourceDTO;
import org.contextmapper.generated.questioncontext.service.mapper.CreateResourceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link CreateResource}.
 */
@Service
@Transactional
public class CreateResourceService {

    private final Logger log = LoggerFactory.getLogger(CreateResourceService.class);

    private final CreateResourceRepository createResourceRepository;

    private final CreateResourceMapper createResourceMapper;

    public CreateResourceService(CreateResourceRepository createResourceRepository, CreateResourceMapper createResourceMapper) {
        this.createResourceRepository = createResourceRepository;
        this.createResourceMapper = createResourceMapper;
    }

    /**
     * Save a createResource.
     *
     * @param createResourceDTO the entity to save.
     * @return the persisted entity.
     */
    public CreateResourceDTO save(CreateResourceDTO createResourceDTO) {
        log.debug("Request to save CreateResource : {}", createResourceDTO);
        CreateResource createResource = createResourceMapper.toEntity(createResourceDTO);
        createResource = createResourceRepository.save(createResource);
        return createResourceMapper.toDto(createResource);
    }

    /**
     * Update a createResource.
     *
     * @param createResourceDTO the entity to save.
     * @return the persisted entity.
     */
    public CreateResourceDTO update(CreateResourceDTO createResourceDTO) {
        log.debug("Request to update CreateResource : {}", createResourceDTO);
        CreateResource createResource = createResourceMapper.toEntity(createResourceDTO);
        createResource = createResourceRepository.save(createResource);
        return createResourceMapper.toDto(createResource);
    }

    /**
     * Partially update a createResource.
     *
     * @param createResourceDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CreateResourceDTO> partialUpdate(CreateResourceDTO createResourceDTO) {
        log.debug("Request to partially update CreateResource : {}", createResourceDTO);

        return createResourceRepository
            .findById(createResourceDTO.getId())
            .map(existingCreateResource -> {
                createResourceMapper.partialUpdate(existingCreateResource, createResourceDTO);

                return existingCreateResource;
            })
            .map(createResourceRepository::save)
            .map(createResourceMapper::toDto);
    }

    /**
     * Get all the createResources.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CreateResourceDTO> findAll() {
        log.debug("Request to get all CreateResources");
        return createResourceRepository
            .findAll()
            .stream()
            .map(createResourceMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one createResource by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CreateResourceDTO> findOne(Long id) {
        log.debug("Request to get CreateResource : {}", id);
        return createResourceRepository.findById(id).map(createResourceMapper::toDto);
    }

    /**
     * Delete the createResource by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CreateResource : {}", id);
        createResourceRepository.deleteById(id);
    }
}
