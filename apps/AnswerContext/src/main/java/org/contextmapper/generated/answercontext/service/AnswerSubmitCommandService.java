package org.contextmapper.generated.answercontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.answercontext.domain.AnswerSubmitCommand;
import org.contextmapper.generated.answercontext.repository.AnswerSubmitCommandRepository;
import org.contextmapper.generated.answercontext.service.dto.AnswerSubmitCommandDTO;
import org.contextmapper.generated.answercontext.service.mapper.AnswerSubmitCommandMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link AnswerSubmitCommand}.
 */
@Service
@Transactional
public class AnswerSubmitCommandService {

    private final Logger log = LoggerFactory.getLogger(AnswerSubmitCommandService.class);

    private final AnswerSubmitCommandRepository answerSubmitCommandRepository;

    private final AnswerSubmitCommandMapper answerSubmitCommandMapper;

    public AnswerSubmitCommandService(
        AnswerSubmitCommandRepository answerSubmitCommandRepository,
        AnswerSubmitCommandMapper answerSubmitCommandMapper
    ) {
        this.answerSubmitCommandRepository = answerSubmitCommandRepository;
        this.answerSubmitCommandMapper = answerSubmitCommandMapper;
    }

    /**
     * Save a answerSubmitCommand.
     *
     * @param answerSubmitCommandDTO the entity to save.
     * @return the persisted entity.
     */
    public AnswerSubmitCommandDTO save(AnswerSubmitCommandDTO answerSubmitCommandDTO) {
        log.debug("Request to save AnswerSubmitCommand : {}", answerSubmitCommandDTO);
        AnswerSubmitCommand answerSubmitCommand = answerSubmitCommandMapper.toEntity(answerSubmitCommandDTO);
        answerSubmitCommand = answerSubmitCommandRepository.save(answerSubmitCommand);
        return answerSubmitCommandMapper.toDto(answerSubmitCommand);
    }

    /**
     * Update a answerSubmitCommand.
     *
     * @param answerSubmitCommandDTO the entity to save.
     * @return the persisted entity.
     */
    public AnswerSubmitCommandDTO update(AnswerSubmitCommandDTO answerSubmitCommandDTO) {
        log.debug("Request to update AnswerSubmitCommand : {}", answerSubmitCommandDTO);
        AnswerSubmitCommand answerSubmitCommand = answerSubmitCommandMapper.toEntity(answerSubmitCommandDTO);
        answerSubmitCommand = answerSubmitCommandRepository.save(answerSubmitCommand);
        return answerSubmitCommandMapper.toDto(answerSubmitCommand);
    }

    /**
     * Partially update a answerSubmitCommand.
     *
     * @param answerSubmitCommandDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<AnswerSubmitCommandDTO> partialUpdate(AnswerSubmitCommandDTO answerSubmitCommandDTO) {
        log.debug("Request to partially update AnswerSubmitCommand : {}", answerSubmitCommandDTO);

        return answerSubmitCommandRepository
            .findById(answerSubmitCommandDTO.getId())
            .map(existingAnswerSubmitCommand -> {
                answerSubmitCommandMapper.partialUpdate(existingAnswerSubmitCommand, answerSubmitCommandDTO);

                return existingAnswerSubmitCommand;
            })
            .map(answerSubmitCommandRepository::save)
            .map(answerSubmitCommandMapper::toDto);
    }

    /**
     * Get all the answerSubmitCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AnswerSubmitCommandDTO> findAll() {
        log.debug("Request to get all AnswerSubmitCommands");
        return answerSubmitCommandRepository
            .findAll()
            .stream()
            .map(answerSubmitCommandMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one answerSubmitCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AnswerSubmitCommandDTO> findOne(Long id) {
        log.debug("Request to get AnswerSubmitCommand : {}", id);
        return answerSubmitCommandRepository.findById(id).map(answerSubmitCommandMapper::toDto);
    }

    /**
     * Delete the answerSubmitCommand by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AnswerSubmitCommand : {}", id);
        answerSubmitCommandRepository.deleteById(id);
    }
}
