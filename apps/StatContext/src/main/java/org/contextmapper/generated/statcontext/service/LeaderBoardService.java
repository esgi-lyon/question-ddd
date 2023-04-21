package org.contextmapper.generated.statcontext.service;

import java.util.List;
import java.util.Optional;
import org.contextmapper.generated.statcontext.service.dto.LeaderBoardDTO;

/**
 * Service Interface for managing {@link org.contextmapper.generated.statcontext.domain.LeaderBoard}.
 */
public interface LeaderBoardService {
    /**
     * Save a leaderBoard.
     *
     * @param leaderBoardDTO the entity to save.
     * @return the persisted entity.
     */
    LeaderBoardDTO save(LeaderBoardDTO leaderBoardDTO);

    /**
     * Updates a leaderBoard.
     *
     * @param leaderBoardDTO the entity to update.
     * @return the persisted entity.
     */
    LeaderBoardDTO update(LeaderBoardDTO leaderBoardDTO);

    /**
     * Partially updates a leaderBoard.
     *
     * @param leaderBoardDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<LeaderBoardDTO> partialUpdate(LeaderBoardDTO leaderBoardDTO);

    /**
     * Get all the leaderBoards.
     *
     * @return the list of entities.
     */
    List<LeaderBoardDTO> findAll();

    /**
     * Get the "id" leaderBoard.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LeaderBoardDTO> findOne(Long id);

    /**
     * Delete the "id" leaderBoard.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
