package org.contextmapper.generated.skillcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.skillcontext.domain.TagCreated;
import org.contextmapper.generated.skillcontext.repository.TagCreatedRepository;
import org.contextmapper.generated.skillcontext.service.dto.TagCreatedDTO;
import org.contextmapper.generated.skillcontext.service.mapper.TagCreatedMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TagCreated}.
 */
@Service
@Transactional
public class TagCreatedService {

    private final Logger log = LoggerFactory.getLogger(TagCreatedService.class);

    private final TagCreatedRepository tagCreatedRepository;

    private final TagCreatedMapper tagCreatedMapper;

    public TagCreatedService(TagCreatedRepository tagCreatedRepository, TagCreatedMapper tagCreatedMapper) {
        this.tagCreatedRepository = tagCreatedRepository;
        this.tagCreatedMapper = tagCreatedMapper;
    }

    /**
     * Save a tagCreated.
     *
     * @param tagCreatedDTO the entity to save.
     * @return the persisted entity.
     */
    public TagCreatedDTO save(TagCreatedDTO tagCreatedDTO) {
        log.debug("Request to save TagCreated : {}", tagCreatedDTO);
        TagCreated tagCreated = tagCreatedMapper.toEntity(tagCreatedDTO);
        tagCreated = tagCreatedRepository.save(tagCreated);
        return tagCreatedMapper.toDto(tagCreated);
    }

    /**
     * Update a tagCreated.
     *
     * @param tagCreatedDTO the entity to save.
     * @return the persisted entity.
     */
    public TagCreatedDTO update(TagCreatedDTO tagCreatedDTO) {
        log.debug("Request to update TagCreated : {}", tagCreatedDTO);
        TagCreated tagCreated = tagCreatedMapper.toEntity(tagCreatedDTO);
        tagCreated = tagCreatedRepository.save(tagCreated);
        return tagCreatedMapper.toDto(tagCreated);
    }

    /**
     * Partially update a tagCreated.
     *
     * @param tagCreatedDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<TagCreatedDTO> partialUpdate(TagCreatedDTO tagCreatedDTO) {
        log.debug("Request to partially update TagCreated : {}", tagCreatedDTO);

        return tagCreatedRepository
            .findById(tagCreatedDTO.getId())
            .map(existingTagCreated -> {
                tagCreatedMapper.partialUpdate(existingTagCreated, tagCreatedDTO);

                return existingTagCreated;
            })
            .map(tagCreatedRepository::save)
            .map(tagCreatedMapper::toDto);
    }

    /**
     * Get all the tagCreateds.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TagCreatedDTO> findAll() {
        log.debug("Request to get all TagCreateds");
        return tagCreatedRepository.findAll().stream().map(tagCreatedMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one tagCreated by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TagCreatedDTO> findOne(Long id) {
        log.debug("Request to get TagCreated : {}", id);
        return tagCreatedRepository.findById(id).map(tagCreatedMapper::toDto);
    }

    /**
     * Delete the tagCreated by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TagCreated : {}", id);
        tagCreatedRepository.deleteById(id);
    }
}
