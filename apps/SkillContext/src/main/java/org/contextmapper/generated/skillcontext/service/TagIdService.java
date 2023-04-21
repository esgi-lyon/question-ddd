package org.contextmapper.generated.skillcontext.service;

import java.util.List;
import java.util.Optional;
import org.contextmapper.generated.skillcontext.service.dto.TagIdDTO;

/**
 * Service Interface for managing {@link org.contextmapper.generated.skillcontext.domain.TagId}.
 */
public interface TagIdService {
    /**
     * Save a tagId.
     *
     * @param tagIdDTO the entity to save.
     * @return the persisted entity.
     */
    TagIdDTO save(TagIdDTO tagIdDTO);

    /**
     * Updates a tagId.
     *
     * @param tagIdDTO the entity to update.
     * @return the persisted entity.
     */
    TagIdDTO update(TagIdDTO tagIdDTO);

    /**
     * Partially updates a tagId.
     *
     * @param tagIdDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TagIdDTO> partialUpdate(TagIdDTO tagIdDTO);

    /**
     * Get all the tagIds.
     *
     * @return the list of entities.
     */
    List<TagIdDTO> findAll();

    /**
     * Get the "id" tagId.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TagIdDTO> findOne(Long id);

    /**
     * Delete the "id" tagId.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
