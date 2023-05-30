package org.contextmapper.generated.evaluationcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.evaluationcontext.domain.ViewTagEvaluationCommand;
import org.contextmapper.generated.evaluationcontext.repository.ViewTagEvaluationCommandRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.ViewTagEvaluationCommandDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.ViewTagEvaluationCommandMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ViewTagEvaluationCommand}.
 */
@Service
@Transactional
public class ViewTagEvaluationCommandService {

    private final Logger log = LoggerFactory.getLogger(ViewTagEvaluationCommandService.class);

    private final ViewTagEvaluationCommandRepository viewTagEvaluationCommandRepository;

    private final ViewTagEvaluationCommandMapper viewTagEvaluationCommandMapper;

    public ViewTagEvaluationCommandService(
        ViewTagEvaluationCommandRepository viewTagEvaluationCommandRepository,
        ViewTagEvaluationCommandMapper viewTagEvaluationCommandMapper
    ) {
        this.viewTagEvaluationCommandRepository = viewTagEvaluationCommandRepository;
        this.viewTagEvaluationCommandMapper = viewTagEvaluationCommandMapper;
    }

    /**
     * Save a viewTagEvaluationCommand.
     *
     * @param viewTagEvaluationCommandDTO the entity to save.
     * @return the persisted entity.
     */
    public ViewTagEvaluationCommandDTO save(ViewTagEvaluationCommandDTO viewTagEvaluationCommandDTO) {
        log.debug("Request to save ViewTagEvaluationCommand : {}", viewTagEvaluationCommandDTO);
        ViewTagEvaluationCommand viewTagEvaluationCommand = viewTagEvaluationCommandMapper.toEntity(viewTagEvaluationCommandDTO);
        viewTagEvaluationCommand = viewTagEvaluationCommandRepository.save(viewTagEvaluationCommand);
        return viewTagEvaluationCommandMapper.toDto(viewTagEvaluationCommand);
    }

    /**
     * Update a viewTagEvaluationCommand.
     *
     * @param viewTagEvaluationCommandDTO the entity to save.
     * @return the persisted entity.
     */
    public ViewTagEvaluationCommandDTO update(ViewTagEvaluationCommandDTO viewTagEvaluationCommandDTO) {
        log.debug("Request to update ViewTagEvaluationCommand : {}", viewTagEvaluationCommandDTO);
        ViewTagEvaluationCommand viewTagEvaluationCommand = viewTagEvaluationCommandMapper.toEntity(viewTagEvaluationCommandDTO);
        viewTagEvaluationCommand = viewTagEvaluationCommandRepository.save(viewTagEvaluationCommand);
        return viewTagEvaluationCommandMapper.toDto(viewTagEvaluationCommand);
    }

    /**
     * Partially update a viewTagEvaluationCommand.
     *
     * @param viewTagEvaluationCommandDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ViewTagEvaluationCommandDTO> partialUpdate(ViewTagEvaluationCommandDTO viewTagEvaluationCommandDTO) {
        log.debug("Request to partially update ViewTagEvaluationCommand : {}", viewTagEvaluationCommandDTO);

        return viewTagEvaluationCommandRepository
            .findById(viewTagEvaluationCommandDTO.getId())
            .map(existingViewTagEvaluationCommand -> {
                viewTagEvaluationCommandMapper.partialUpdate(existingViewTagEvaluationCommand, viewTagEvaluationCommandDTO);

                return existingViewTagEvaluationCommand;
            })
            .map(viewTagEvaluationCommandRepository::save)
            .map(viewTagEvaluationCommandMapper::toDto);
    }

    /**
     * Get all the viewTagEvaluationCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ViewTagEvaluationCommandDTO> findAll() {
        log.debug("Request to get all ViewTagEvaluationCommands");
        return viewTagEvaluationCommandRepository
            .findAll()
            .stream()
            .map(viewTagEvaluationCommandMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one viewTagEvaluationCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ViewTagEvaluationCommandDTO> findOne(Long id) {
        log.debug("Request to get ViewTagEvaluationCommand : {}", id);
        return viewTagEvaluationCommandRepository.findById(id).map(viewTagEvaluationCommandMapper::toDto);
    }

    /**
     * Delete the viewTagEvaluationCommand by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ViewTagEvaluationCommand : {}", id);
        viewTagEvaluationCommandRepository.deleteById(id);
    }
}
