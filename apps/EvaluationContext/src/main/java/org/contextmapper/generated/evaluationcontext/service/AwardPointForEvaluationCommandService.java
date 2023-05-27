package org.contextmapper.generated.evaluationcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.evaluationcontext.domain.AwardPointForEvaluationCommand;
import org.contextmapper.generated.evaluationcontext.repository.AwardPointForEvaluationCommandRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.AwardPointForEvaluationCommandDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.AwardPointForEvaluationCommandMapper;
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

    private final AwardPointForEvaluationCommandMapper awardPointForEvaluationCommandMapper;

    public AwardPointForEvaluationCommandService(
        AwardPointForEvaluationCommandRepository awardPointForEvaluationCommandRepository,
        AwardPointForEvaluationCommandMapper awardPointForEvaluationCommandMapper
    ) {
        this.awardPointForEvaluationCommandRepository = awardPointForEvaluationCommandRepository;
        this.awardPointForEvaluationCommandMapper = awardPointForEvaluationCommandMapper;
    }

    /**
     * Save a awardPointForEvaluationCommand.
     *
     * @param awardPointForEvaluationCommandDTO the entity to save.
     * @return the persisted entity.
     */
    public AwardPointForEvaluationCommandDTO save(AwardPointForEvaluationCommandDTO awardPointForEvaluationCommandDTO) {
        log.debug("Request to save AwardPointForEvaluationCommand : {}", awardPointForEvaluationCommandDTO);
        AwardPointForEvaluationCommand awardPointForEvaluationCommand = awardPointForEvaluationCommandMapper.toEntity(
            awardPointForEvaluationCommandDTO
        );
        awardPointForEvaluationCommand = awardPointForEvaluationCommandRepository.save(awardPointForEvaluationCommand);
        return awardPointForEvaluationCommandMapper.toDto(awardPointForEvaluationCommand);
    }

    /**
     * Update a awardPointForEvaluationCommand.
     *
     * @param awardPointForEvaluationCommandDTO the entity to save.
     * @return the persisted entity.
     */
    public AwardPointForEvaluationCommandDTO update(AwardPointForEvaluationCommandDTO awardPointForEvaluationCommandDTO) {
        log.debug("Request to update AwardPointForEvaluationCommand : {}", awardPointForEvaluationCommandDTO);
        AwardPointForEvaluationCommand awardPointForEvaluationCommand = awardPointForEvaluationCommandMapper.toEntity(
            awardPointForEvaluationCommandDTO
        );
        awardPointForEvaluationCommand = awardPointForEvaluationCommandRepository.save(awardPointForEvaluationCommand);
        return awardPointForEvaluationCommandMapper.toDto(awardPointForEvaluationCommand);
    }

    /**
     * Partially update a awardPointForEvaluationCommand.
     *
     * @param awardPointForEvaluationCommandDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<AwardPointForEvaluationCommandDTO> partialUpdate(AwardPointForEvaluationCommandDTO awardPointForEvaluationCommandDTO) {
        log.debug("Request to partially update AwardPointForEvaluationCommand : {}", awardPointForEvaluationCommandDTO);

        return awardPointForEvaluationCommandRepository
            .findById(awardPointForEvaluationCommandDTO.getId())
            .map(existingAwardPointForEvaluationCommand -> {
                awardPointForEvaluationCommandMapper.partialUpdate(
                    existingAwardPointForEvaluationCommand,
                    awardPointForEvaluationCommandDTO
                );

                return existingAwardPointForEvaluationCommand;
            })
            .map(awardPointForEvaluationCommandRepository::save)
            .map(awardPointForEvaluationCommandMapper::toDto);
    }

    /**
     * Get all the awardPointForEvaluationCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AwardPointForEvaluationCommandDTO> findAll() {
        log.debug("Request to get all AwardPointForEvaluationCommands");
        return awardPointForEvaluationCommandRepository
            .findAll()
            .stream()
            .map(awardPointForEvaluationCommandMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one awardPointForEvaluationCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AwardPointForEvaluationCommandDTO> findOne(Long id) {
        log.debug("Request to get AwardPointForEvaluationCommand : {}", id);
        return awardPointForEvaluationCommandRepository.findById(id).map(awardPointForEvaluationCommandMapper::toDto);
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
