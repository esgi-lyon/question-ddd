package org.contextmapper.generated.sendquestioncontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.sendquestioncontext.domain.PrepareQuestionCommand;
import org.contextmapper.generated.sendquestioncontext.repository.PrepareQuestionCommandRepository;
import org.contextmapper.generated.sendquestioncontext.service.dto.PrepareQuestionCommandDTO;
import org.contextmapper.generated.sendquestioncontext.service.mapper.PrepareQuestionCommandMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link PrepareQuestionCommand}.
 */
@Service
@Transactional
public class PrepareQuestionCommandService {

    private final Logger log = LoggerFactory.getLogger(PrepareQuestionCommandService.class);

    private final PrepareQuestionCommandRepository prepareQuestionCommandRepository;

    private final PrepareQuestionCommandMapper prepareQuestionCommandMapper;

    public PrepareQuestionCommandService(
        PrepareQuestionCommandRepository prepareQuestionCommandRepository,
        PrepareQuestionCommandMapper prepareQuestionCommandMapper
    ) {
        this.prepareQuestionCommandRepository = prepareQuestionCommandRepository;
        this.prepareQuestionCommandMapper = prepareQuestionCommandMapper;
    }

    /**
     * Save a prepareQuestionCommand.
     *
     * @param prepareQuestionCommandDTO the entity to save.
     * @return the persisted entity.
     */
    public PrepareQuestionCommandDTO save(PrepareQuestionCommandDTO prepareQuestionCommandDTO) {
        log.debug("Request to save PrepareQuestionCommand : {}", prepareQuestionCommandDTO);
        PrepareQuestionCommand prepareQuestionCommand = prepareQuestionCommandMapper.toEntity(prepareQuestionCommandDTO);
        prepareQuestionCommand = prepareQuestionCommandRepository.save(prepareQuestionCommand);
        return prepareQuestionCommandMapper.toDto(prepareQuestionCommand);
    }

    /**
     * Update a prepareQuestionCommand.
     *
     * @param prepareQuestionCommandDTO the entity to save.
     * @return the persisted entity.
     */
    public PrepareQuestionCommandDTO update(PrepareQuestionCommandDTO prepareQuestionCommandDTO) {
        log.debug("Request to update PrepareQuestionCommand : {}", prepareQuestionCommandDTO);
        PrepareQuestionCommand prepareQuestionCommand = prepareQuestionCommandMapper.toEntity(prepareQuestionCommandDTO);
        prepareQuestionCommand = prepareQuestionCommandRepository.save(prepareQuestionCommand);
        return prepareQuestionCommandMapper.toDto(prepareQuestionCommand);
    }

    /**
     * Partially update a prepareQuestionCommand.
     *
     * @param prepareQuestionCommandDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<PrepareQuestionCommandDTO> partialUpdate(PrepareQuestionCommandDTO prepareQuestionCommandDTO) {
        log.debug("Request to partially update PrepareQuestionCommand : {}", prepareQuestionCommandDTO);

        return prepareQuestionCommandRepository
            .findById(prepareQuestionCommandDTO.getId())
            .map(existingPrepareQuestionCommand -> {
                prepareQuestionCommandMapper.partialUpdate(existingPrepareQuestionCommand, prepareQuestionCommandDTO);

                return existingPrepareQuestionCommand;
            })
            .map(prepareQuestionCommandRepository::save)
            .map(prepareQuestionCommandMapper::toDto);
    }

    /**
     * Get all the prepareQuestionCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PrepareQuestionCommandDTO> findAll() {
        log.debug("Request to get all PrepareQuestionCommands");
        return prepareQuestionCommandRepository
            .findAll()
            .stream()
            .map(prepareQuestionCommandMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one prepareQuestionCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PrepareQuestionCommandDTO> findOne(Long id) {
        log.debug("Request to get PrepareQuestionCommand : {}", id);
        return prepareQuestionCommandRepository.findById(id).map(prepareQuestionCommandMapper::toDto);
    }

    /**
     * Delete the prepareQuestionCommand by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PrepareQuestionCommand : {}", id);
        prepareQuestionCommandRepository.deleteById(id);
    }
}
