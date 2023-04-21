package org.contextmapper.generated.questioncontext.service;

import java.util.List;
import java.util.Optional;
import org.contextmapper.generated.questioncontext.service.dto.QuestionResourceTagDTO;

/**
 * Service Interface for managing {@link org.contextmapper.generated.questioncontext.domain.QuestionResourceTag}.
 */
public interface QuestionResourceTagService {
    /**
     * Save a questionResourceTag.
     *
     * @param questionResourceTagDTO the entity to save.
     * @return the persisted entity.
     */
    QuestionResourceTagDTO save(QuestionResourceTagDTO questionResourceTagDTO);

    /**
     * Updates a questionResourceTag.
     *
     * @param questionResourceTagDTO the entity to update.
     * @return the persisted entity.
     */
    QuestionResourceTagDTO update(QuestionResourceTagDTO questionResourceTagDTO);

    /**
     * Partially updates a questionResourceTag.
     *
     * @param questionResourceTagDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<QuestionResourceTagDTO> partialUpdate(QuestionResourceTagDTO questionResourceTagDTO);

    /**
     * Get all the questionResourceTags.
     *
     * @return the list of entities.
     */
    List<QuestionResourceTagDTO> findAll();

    /**
     * Get the "id" questionResourceTag.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<QuestionResourceTagDTO> findOne(Long id);

    /**
     * Delete the "id" questionResourceTag.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
