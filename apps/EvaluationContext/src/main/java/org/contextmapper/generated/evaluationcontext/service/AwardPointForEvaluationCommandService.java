package org.contextmapper.generated.evaluationcontext.service;

import java.util.List;
import java.util.Optional;
import org.contextmapper.generated.evaluationcontext.domain.AwardPointForEvaluationCommand;
import org.contextmapper.generated.evaluationcontext.repository.AwardPointForEvaluationCommandRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link AwardPointForEvaluationCommand}.
 */
@Service
@Transactional
public class AwardPointForEvaluationCommandService {

    private final Logger log = LoggerFactory.getLogger(AwardPointForEvaluationCommandService.class);

    private final AwardPointForEvaluationCommandRepository awardPointForEvaluationCommandRepository;

    public AwardPointForEvaluationCommandService(AwardPointForEvaluationCommandRepository awardPointForEvaluationCommandRepository) {
        this.awardPointForEvaluationCommandRepository = awardPointForEvaluationCommandRepository;
    }

    /**
     * Save a awardPointForEvaluationCommand.
     *
     * @param awardPointForEvaluationCommand the entity to save.
     * @return the persisted entity.
     */
    public AwardPointForEvaluationCommand save(AwardPointForEvaluationCommand awardPointForEvaluationCommand) {
        log.debug("Request to save AwardPointForEvaluationCommand : {}", awardPointForEvaluationCommand);
        return awardPointForEvaluationCommandRepository.save(awardPointForEvaluationCommand);
    }

    /**
     * Update a awardPointForEvaluationCommand.
     *
     * @param awardPointForEvaluationCommand the entity to save.
     * @return the persisted entity.
     */
    public AwardPointForEvaluationCommand update(AwardPointForEvaluationCommand awardPointForEvaluationCommand) {
        log.debug("Request to update AwardPointForEvaluationCommand : {}", awardPointForEvaluationCommand);
        // no save call needed as we have no fields that can be updated
        return awardPointForEvaluationCommand;
    }

    /**
     * Partially update a awardPointForEvaluationCommand.
     *
     * @param awardPointForEvaluationCommand the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<AwardPointForEvaluationCommand> partialUpdate(AwardPointForEvaluationCommand awardPointForEvaluationCommand) {
        log.debug("Request to partially update AwardPointForEvaluationCommand : {}", awardPointForEvaluationCommand);

        return awardPointForEvaluationCommandRepository
            .findById(awardPointForEvaluationCommand.getId())
            .map(existingAwardPointForEvaluationCommand -> {
                return existingAwardPointForEvaluationCommand;
            })// .map(awardPointForEvaluationCommandRepository::save)
        ;
    }

    /**
     * Get all the awardPointForEvaluationCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AwardPointForEvaluationCommand> findAll() {
        log.debug("Request to get all AwardPointForEvaluationCommands");
        return awardPointForEvaluationCommandRepository.findAll();
    }

    /**
     * Get one awardPointForEvaluationCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AwardPointForEvaluationCommand> findOne(Long id) {
        log.debug("Request to get AwardPointForEvaluationCommand : {}", id);
        return awardPointForEvaluationCommandRepository.findById(id);
    }

    /**
     * Delete the awardPointForEvaluationCommand by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AwardPointForEvaluationCommand : {}", id);
        awardPointForEvaluationCommandRepository.deleteById(id);
    }
}
