package org.contextmapper.generated.evaluationcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.evaluationcontext.domain.CheckAnswerCommand;
import org.contextmapper.generated.evaluationcontext.repository.CheckAnswerCommandRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.CheckAnswerCommandDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.CheckAnswerCommandMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link CheckAnswerCommand}.
 */
@Service
@Transactional
public class CheckAnswerCommandService {

    private final Logger log = LoggerFactory.getLogger(CheckAnswerCommandService.class);

    private final CheckAnswerCommandRepository checkAnswerCommandRepository;

    private final CheckAnswerCommandMapper checkAnswerCommandMapper;

    public CheckAnswerCommandService(
        CheckAnswerCommandRepository checkAnswerCommandRepository,
        CheckAnswerCommandMapper checkAnswerCommandMapper
    ) {
        this.checkAnswerCommandRepository = checkAnswerCommandRepository;
        this.checkAnswerCommandMapper = checkAnswerCommandMapper;
    }

    /**
     * Save a checkAnswerCommand.
     *
     * @param checkAnswerCommandDTO the entity to save.
     * @return the persisted entity.
     */
    public CheckAnswerCommandDTO save(CheckAnswerCommandDTO checkAnswerCommandDTO) {
        log.debug("Request to save CheckAnswerCommand : {}", checkAnswerCommandDTO);
        CheckAnswerCommand checkAnswerCommand = checkAnswerCommandMapper.toEntity(checkAnswerCommandDTO);
        checkAnswerCommand = checkAnswerCommandRepository.save(checkAnswerCommand);
        return checkAnswerCommandMapper.toDto(checkAnswerCommand);
    }

    /**
     * Update a checkAnswerCommand.
     *
     * @param checkAnswerCommandDTO the entity to save.
     * @return the persisted entity.
     */
    public CheckAnswerCommandDTO update(CheckAnswerCommandDTO checkAnswerCommandDTO) {
        log.debug("Request to update CheckAnswerCommand : {}", checkAnswerCommandDTO);
        CheckAnswerCommand checkAnswerCommand = checkAnswerCommandMapper.toEntity(checkAnswerCommandDTO);
        checkAnswerCommand = checkAnswerCommandRepository.save(checkAnswerCommand);
        return checkAnswerCommandMapper.toDto(checkAnswerCommand);
    }

    /**
     * Partially update a checkAnswerCommand.
     *
     * @param checkAnswerCommandDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CheckAnswerCommandDTO> partialUpdate(CheckAnswerCommandDTO checkAnswerCommandDTO) {
        log.debug("Request to partially update CheckAnswerCommand : {}", checkAnswerCommandDTO);

        return checkAnswerCommandRepository
            .findById(checkAnswerCommandDTO.getId())
            .map(existingCheckAnswerCommand -> {
                checkAnswerCommandMapper.partialUpdate(existingCheckAnswerCommand, checkAnswerCommandDTO);

                return existingCheckAnswerCommand;
            })
            .map(checkAnswerCommandRepository::save)
            .map(checkAnswerCommandMapper::toDto);
    }

    /**
     * Get all the checkAnswerCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CheckAnswerCommandDTO> findAll() {
        log.debug("Request to get all CheckAnswerCommands");
        return checkAnswerCommandRepository
            .findAll()
            .stream()
            .map(checkAnswerCommandMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one checkAnswerCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CheckAnswerCommandDTO> findOne(Long id) {
        log.debug("Request to get CheckAnswerCommand : {}", id);
        return checkAnswerCommandRepository.findById(id).map(checkAnswerCommandMapper::toDto);
    }

    /**
     * Delete the checkAnswerCommand by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CheckAnswerCommand : {}", id);
        checkAnswerCommandRepository.deleteById(id);
    }
}
