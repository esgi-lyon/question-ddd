package org.contextmapper.generated.sendquestioncontext.service;

import java.util.List;
import java.util.Optional;
import org.contextmapper.generated.sendquestioncontext.service.dto.QuestionSentDTO;

/**
 * Service Interface for managing {@link org.contextmapper.generated.sendquestioncontext.domain.QuestionSent}.
 */
public interface QuestionSentService {
    /**
     * Save a questionSent.
     *
     * @param questionSentDTO the entity to save.
     * @return the persisted entity.
     */
    QuestionSentDTO save(QuestionSentDTO questionSentDTO);

    /**
     * Updates a questionSent.
     *
     * @param questionSentDTO the entity to update.
     * @return the persisted entity.
     */
    QuestionSentDTO update(QuestionSentDTO questionSentDTO);

    /**
     * Partially updates a questionSent.
     *
     * @param questionSentDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<QuestionSentDTO> partialUpdate(QuestionSentDTO questionSentDTO);

    /**
     * Get all the questionSents.
     *
     * @return the list of entities.
     */
    List<QuestionSentDTO> findAll();

    /**
     * Get the "id" questionSent.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<QuestionSentDTO> findOne(Long id);

    /**
     * Delete the "id" questionSent.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
