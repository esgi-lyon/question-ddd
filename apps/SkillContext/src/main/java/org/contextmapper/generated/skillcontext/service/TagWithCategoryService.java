package org.contextmapper.generated.skillcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.skillcontext.domain.TagWithCategory;
import org.contextmapper.generated.skillcontext.repository.TagWithCategoryRepository;
import org.contextmapper.generated.skillcontext.service.dto.TagWithCategoryDTO;
import org.contextmapper.generated.skillcontext.service.mapper.TagWithCategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TagWithCategory}.
 */
@Service
@Transactional
public class TagWithCategoryService {

    private final Logger log = LoggerFactory.getLogger(TagWithCategoryService.class);

    private final TagWithCategoryRepository tagWithCategoryRepository;

    private final TagWithCategoryMapper tagWithCategoryMapper;

    public TagWithCategoryService(TagWithCategoryRepository tagWithCategoryRepository, TagWithCategoryMapper tagWithCategoryMapper) {
        this.tagWithCategoryRepository = tagWithCategoryRepository;
        this.tagWithCategoryMapper = tagWithCategoryMapper;
    }

    /**
     * Save a tagWithCategory.
     *
     * @param tagWithCategoryDTO the entity to save.
     * @return the persisted entity.
     */
    public TagWithCategoryDTO save(TagWithCategoryDTO tagWithCategoryDTO) {
        log.debug("Request to save TagWithCategory : {}", tagWithCategoryDTO);
        TagWithCategory tagWithCategory = tagWithCategoryMapper.toEntity(tagWithCategoryDTO);
        tagWithCategory = tagWithCategoryRepository.save(tagWithCategory);
        return tagWithCategoryMapper.toDto(tagWithCategory);
    }

    /**
     * Update a tagWithCategory.
     *
     * @param tagWithCategoryDTO the entity to save.
     * @return the persisted entity.
     */
    public TagWithCategoryDTO update(TagWithCategoryDTO tagWithCategoryDTO) {
        log.debug("Request to update TagWithCategory : {}", tagWithCategoryDTO);
        TagWithCategory tagWithCategory = tagWithCategoryMapper.toEntity(tagWithCategoryDTO);
        tagWithCategory = tagWithCategoryRepository.save(tagWithCategory);
        return tagWithCategoryMapper.toDto(tagWithCategory);
    }

    /**
     * Partially update a tagWithCategory.
     *
     * @param tagWithCategoryDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<TagWithCategoryDTO> partialUpdate(TagWithCategoryDTO tagWithCategoryDTO) {
        log.debug("Request to partially update TagWithCategory : {}", tagWithCategoryDTO);

        return tagWithCategoryRepository
            .findById(tagWithCategoryDTO.getId())
            .map(existingTagWithCategory -> {
                tagWithCategoryMapper.partialUpdate(existingTagWithCategory, tagWithCategoryDTO);

                return existingTagWithCategory;
            })
            .map(tagWithCategoryRepository::save)
            .map(tagWithCategoryMapper::toDto);
    }

    /**
     * Get all the tagWithCategories.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TagWithCategoryDTO> findAll() {
        log.debug("Request to get all TagWithCategories");
        return tagWithCategoryRepository
            .findAll()
            .stream()
            .map(tagWithCategoryMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one tagWithCategory by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TagWithCategoryDTO> findOne(Long id) {
        log.debug("Request to get TagWithCategory : {}", id);
        return tagWithCategoryRepository.findById(id).map(tagWithCategoryMapper::toDto);
    }

    /**
     * Delete the tagWithCategory by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TagWithCategory : {}", id);
        tagWithCategoryRepository.deleteById(id);
    }
}
