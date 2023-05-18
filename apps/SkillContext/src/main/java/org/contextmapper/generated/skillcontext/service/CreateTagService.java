package org.contextmapper.generated.skillcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.skillcontext.domain.CreateTag;
import org.contextmapper.generated.skillcontext.repository.CreateTagRepository;
import org.contextmapper.generated.skillcontext.service.dto.CreateTagDTO;
import org.contextmapper.generated.skillcontext.service.mapper.CreateTagMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link CreateTag}.
 */
@Service
@Transactional
public class CreateTagService {

    private final Logger log = LoggerFactory.getLogger(CreateTagService.class);

    private final CreateTagRepository createTagRepository;

    private final CreateTagMapper createTagMapper;

    public CreateTagService(CreateTagRepository createTagRepository, CreateTagMapper createTagMapper) {
        this.createTagRepository = createTagRepository;
        this.createTagMapper = createTagMapper;
    }

    /**
     * Save a createTag.
     *
     * @param createTagDTO the entity to save.
     * @return the persisted entity.
     */
    public CreateTagDTO save(CreateTagDTO createTagDTO) {
        log.debug("Request to save CreateTag : {}", createTagDTO);
        CreateTag createTag = createTagMapper.toEntity(createTagDTO);
        createTag = createTagRepository.save(createTag);
        return createTagMapper.toDto(createTag);
    }

    /**
     * Update a createTag.
     *
     * @param createTagDTO the entity to save.
     * @return the persisted entity.
     */
    public CreateTagDTO update(CreateTagDTO createTagDTO) {
        log.debug("Request to update CreateTag : {}", createTagDTO);
        CreateTag createTag = createTagMapper.toEntity(createTagDTO);
        createTag = createTagRepository.save(createTag);
        return createTagMapper.toDto(createTag);
    }

    /**
     * Partially update a createTag.
     *
     * @param createTagDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CreateTagDTO> partialUpdate(CreateTagDTO createTagDTO) {
        log.debug("Request to partially update CreateTag : {}", createTagDTO);

        return createTagRepository
            .findById(createTagDTO.getId())
            .map(existingCreateTag -> {
                createTagMapper.partialUpdate(existingCreateTag, createTagDTO);

                return existingCreateTag;
            })
            .map(createTagRepository::save)
            .map(createTagMapper::toDto);
    }

    /**
     * Get all the createTags.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CreateTagDTO> findAll() {
        log.debug("Request to get all CreateTags");
        return createTagRepository.findAll().stream().map(createTagMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one createTag by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CreateTagDTO> findOne(Long id) {
        log.debug("Request to get CreateTag : {}", id);
        return createTagRepository.findById(id).map(createTagMapper::toDto);
    }

    /**
     * Delete the createTag by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CreateTag : {}", id);
        createTagRepository.deleteById(id);
    }
}
