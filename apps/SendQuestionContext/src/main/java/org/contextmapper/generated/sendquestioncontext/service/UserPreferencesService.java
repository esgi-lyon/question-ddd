package org.contextmapper.generated.sendquestioncontext.service;

import java.util.List;
import java.util.Optional;
import org.contextmapper.generated.sendquestioncontext.service.dto.UserPreferencesDTO;

/**
 * Service Interface for managing {@link org.contextmapper.generated.sendquestioncontext.domain.UserPreferences}.
 */
public interface UserPreferencesService {
    /**
     * Save a userPreferences.
     *
     * @param userPreferencesDTO the entity to save.
     * @return the persisted entity.
     */
    UserPreferencesDTO save(UserPreferencesDTO userPreferencesDTO);

    /**
     * Updates a userPreferences.
     *
     * @param userPreferencesDTO the entity to update.
     * @return the persisted entity.
     */
    UserPreferencesDTO update(UserPreferencesDTO userPreferencesDTO);

    /**
     * Partially updates a userPreferences.
     *
     * @param userPreferencesDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<UserPreferencesDTO> partialUpdate(UserPreferencesDTO userPreferencesDTO);

    /**
     * Get all the userPreferences.
     *
     * @return the list of entities.
     */
    List<UserPreferencesDTO> findAll();

    /**
     * Get the "id" userPreferences.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UserPreferencesDTO> findOne(Long id);

    /**
     * Delete the "id" userPreferences.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
