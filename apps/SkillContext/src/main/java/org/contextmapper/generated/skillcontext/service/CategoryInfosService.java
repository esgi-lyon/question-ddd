package org.contextmapper.generated.skillcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.skillcontext.domain.CategoryInfos;
import org.contextmapper.generated.skillcontext.repository.CategoryInfosRepository;
import org.contextmapper.generated.skillcontext.service.dto.CategoryInfosDTO;
import org.contextmapper.generated.skillcontext.service.mapper.CategoryInfosMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link CategoryInfos}.
 */
@Service
@Transactional
public class CategoryInfosService {

    private final Logger log = LoggerFactory.getLogger(CategoryInfosService.class);

    private final CategoryInfosRepository categoryInfosRepository;

    private final CategoryInfosMapper categoryInfosMapper;

    public CategoryInfosService(CategoryInfosRepository categoryInfosRepository, CategoryInfosMapper categoryInfosMapper) {
        this.categoryInfosRepository = categoryInfosRepository;
        this.categoryInfosMapper = categoryInfosMapper;
    }

    /**
     * Save a categoryInfos.
     *
     * @param categoryInfosDTO the entity to save.
     * @return the persisted entity.
     */
    public CategoryInfosDTO save(CategoryInfosDTO categoryInfosDTO) {
        log.debug("Request to save CategoryInfos : {}", categoryInfosDTO);
        CategoryInfos categoryInfos = categoryInfosMapper.toEntity(categoryInfosDTO);
        categoryInfos = categoryInfosRepository.save(categoryInfos);
        return categoryInfosMapper.toDto(categoryInfos);
    }

    /**
     * Update a categoryInfos.
     *
     * @param categoryInfosDTO the entity to save.
     * @return the persisted entity.
     */
    public CategoryInfosDTO update(CategoryInfosDTO categoryInfosDTO) {
        log.debug("Request to update CategoryInfos : {}", categoryInfosDTO);
        CategoryInfos categoryInfos = categoryInfosMapper.toEntity(categoryInfosDTO);
        categoryInfos = categoryInfosRepository.save(categoryInfos);
        return categoryInfosMapper.toDto(categoryInfos);
    }

    /**
     * Partially update a categoryInfos.
     *
     * @param categoryInfosDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CategoryInfosDTO> partialUpdate(CategoryInfosDTO categoryInfosDTO) {
        log.debug("Request to partially update CategoryInfos : {}", categoryInfosDTO);

        return categoryInfosRepository
            .findById(categoryInfosDTO.getId())
            .map(existingCategoryInfos -> {
                categoryInfosMapper.partialUpdate(existingCategoryInfos, categoryInfosDTO);

                return existingCategoryInfos;
            })
            .map(categoryInfosRepository::save)
            .map(categoryInfosMapper::toDto);
    }

    /**
     * Get all the categoryInfos.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CategoryInfosDTO> findAll() {
        log.debug("Request to get all CategoryInfos");
        return categoryInfosRepository.findAll().stream().map(categoryInfosMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one categoryInfos by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CategoryInfosDTO> findOne(Long id) {
        log.debug("Request to get CategoryInfos : {}", id);
        return categoryInfosRepository.findById(id).map(categoryInfosMapper::toDto);
    }

    /**
     * Delete the categoryInfos by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CategoryInfos : {}", id);
        categoryInfosRepository.deleteById(id);
    }
}
