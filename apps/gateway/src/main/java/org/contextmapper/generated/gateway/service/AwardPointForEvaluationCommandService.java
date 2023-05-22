package org.contextmapper.generated.gateway.service;

import java.util.List;
import org.contextmapper.generated.gateway.domain.AwardPointForEvaluationCommand;
import org.contextmapper.generated.gateway.repository.AwardPointForEvaluationCommandRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    public Mono<AwardPointForEvaluationCommand> save(AwardPointForEvaluationCommand awardPointForEvaluationCommand) {
        log.debug("Request to save AwardPointForEvaluationCommand : {}", awardPointForEvaluationCommand);
        return awardPointForEvaluationCommandRepository.save(awardPointForEvaluationCommand);
    }

    /**
     * Update a awardPointForEvaluationCommand.
     *
     * @param awardPointForEvaluationCommand the entity to save.
     * @return the persisted entity.
     */
    public Mono<AwardPointForEvaluationCommand> update(AwardPointForEvaluationCommand awardPointForEvaluationCommand) {
        log.debug("Request to update AwardPointForEvaluationCommand : {}", awardPointForEvaluationCommand);
        return awardPointForEvaluationCommandRepository.save(awardPointForEvaluationCommand);
    }

    /**
     * Partially update a awardPointForEvaluationCommand.
     *
     * @param awardPointForEvaluationCommand the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<AwardPointForEvaluationCommand> partialUpdate(AwardPointForEvaluationCommand awardPointForEvaluationCommand) {
        log.debug("Request to partially update AwardPointForEvaluationCommand : {}", awardPointForEvaluationCommand);

        return awardPointForEvaluationCommandRepository
            .findById(awardPointForEvaluationCommand.getId())
            .map(existingAwardPointForEvaluationCommand -> {
                return existingAwardPointForEvaluationCommand;
            })
            .flatMap(awardPointForEvaluationCommandRepository::save);
    }

    /**
     * Get all the awardPointForEvaluationCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<AwardPointForEvaluationCommand> findAll() {
        log.debug("Request to get all AwardPointForEvaluationCommands");
        return awardPointForEvaluationCommandRepository.findAll();
    }

    /**
     * Returns the number of awardPointForEvaluationCommands available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return awardPointForEvaluationCommandRepository.count();
    }

    /**
     * Get one awardPointForEvaluationCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<AwardPointForEvaluationCommand> findOne(Long id) {
        log.debug("Request to get AwardPointForEvaluationCommand : {}", id);
        return awardPointForEvaluationCommandRepository.findById(id);
    }

    /**
     * Delete the awardPointForEvaluationCommand by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete AwardPointForEvaluationCommand : {}", id);
        return awardPointForEvaluationCommandRepository.deleteById(id);
    }
}
