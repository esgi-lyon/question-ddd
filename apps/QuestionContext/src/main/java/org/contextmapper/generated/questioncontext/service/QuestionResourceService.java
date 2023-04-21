package org.contextmapper.generated.questioncontext.service;

import java.util.List;
import java.util.Optional;
import org.contextmapper.generated.questioncontext.service.dto.QuestionResourceDTO;

/**
 * Service Interface for managing {@link org.contextmapper.generated.questioncontext.domain.QuestionResource}.
 */
public interface QuestionResourceService {
    /**
     * Save a questionResource.
     *
     * @param questionResourceDTO the entity to save.
     * @return the persisted entity.
     */
    QuestionResourceDTO save(QuestionResourceDTO questionResourceDTO);

    /**
     * Updates a questionResource.
     *
     * @param questionResourceDTO the entity to update.
     * @return the persisted entity.
     */
    QuestionResourceDTO update(QuestionResourceDTO questionResourceDTO);

    /**
     * Partially updates a questionResource.
     *
     * @param questionResourceDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<QuestionResourceDTO> partialUpdate(QuestionResourceDTO questionResourceDTO);

    /**
     * Get all the questionResources.
     *
     * @return the list of entities.
     */
    List<QuestionResourceDTO> findAll();

    /**
     * Get the "id" questionResource.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<QuestionResourceDTO> findOne(Long id);

    /**
     * Delete the "id" questionResource.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
