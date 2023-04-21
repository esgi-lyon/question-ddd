package org.contextmapper.generated.skillcontext.service;

import java.util.List;
import java.util.Optional;
import org.contextmapper.generated.skillcontext.service.dto.CategoryIdDTO;

/**
 * Service Interface for managing {@link org.contextmapper.generated.skillcontext.domain.CategoryId}.
 */
public interface CategoryIdService {
    /**
     * Save a categoryId.
     *
     * @param categoryIdDTO the entity to save.
     * @return the persisted entity.
     */
    CategoryIdDTO save(CategoryIdDTO categoryIdDTO);

    /**
     * Updates a categoryId.
     *
     * @param categoryIdDTO the entity to update.
     * @return the persisted entity.
     */
    CategoryIdDTO update(CategoryIdDTO categoryIdDTO);

    /**
     * Partially updates a categoryId.
     *
     * @param categoryIdDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CategoryIdDTO> partialUpdate(CategoryIdDTO categoryIdDTO);

    /**
     * Get all the categoryIds.
     *
     * @return the list of entities.
     */
    List<CategoryIdDTO> findAll();

    /**
     * Get the "id" categoryId.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CategoryIdDTO> findOne(Long id);

    /**
     * Delete the "id" categoryId.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
