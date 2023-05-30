package org.contextmapper.generated.evaluationcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.evaluationcontext.domain.ViewQuestionEvaluationCommand;
import org.contextmapper.generated.evaluationcontext.repository.ViewQuestionEvaluationCommandRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.ViewQuestionEvaluationCommandDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.ViewQuestionEvaluationCommandMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ViewQuestionEvaluationCommand}.
 */
@Service
@Transactional
public class ViewQuestionEvaluationCommandService {

    private final Logger log = LoggerFactory.getLogger(ViewQuestionEvaluationCommandService.class);

    private final ViewQuestionEvaluationCommandRepository viewQuestionEvaluationCommandRepository;

    private final ViewQuestionEvaluationCommandMapper viewQuestionEvaluationCommandMapper;

    public ViewQuestionEvaluationCommandService(
        ViewQuestionEvaluationCommandRepository viewQuestionEvaluationCommandRepository,
        ViewQuestionEvaluationCommandMapper viewQuestionEvaluationCommandMapper
    ) {
        this.viewQuestionEvaluationCommandRepository = viewQuestionEvaluationCommandRepository;
        this.viewQuestionEvaluationCommandMapper = viewQuestionEvaluationCommandMapper;
    }

    /**
     * Save a viewQuestionEvaluationCommand.
     *
     * @param viewQuestionEvaluationCommandDTO the entity to save.
     * @return the persisted entity.
     */
    public ViewQuestionEvaluationCommandDTO save(ViewQuestionEvaluationCommandDTO viewQuestionEvaluationCommandDTO) {
        log.debug("Request to save ViewQuestionEvaluationCommand : {}", viewQuestionEvaluationCommandDTO);
        ViewQuestionEvaluationCommand viewQuestionEvaluationCommand = viewQuestionEvaluationCommandMapper.toEntity(
            viewQuestionEvaluationCommandDTO
        );
        viewQuestionEvaluationCommand = viewQuestionEvaluationCommandRepository.save(viewQuestionEvaluationCommand);
        return viewQuestionEvaluationCommandMapper.toDto(viewQuestionEvaluationCommand);
    }

    /**
     * Update a viewQuestionEvaluationCommand.
     *
     * @param viewQuestionEvaluationCommandDTO the entity to save.
     * @return the persisted entity.
     */
    public ViewQuestionEvaluationCommandDTO update(ViewQuestionEvaluationCommandDTO viewQuestionEvaluationCommandDTO) {
        log.debug("Request to update ViewQuestionEvaluationCommand : {}", viewQuestionEvaluationCommandDTO);
        ViewQuestionEvaluationCommand viewQuestionEvaluationCommand = viewQuestionEvaluationCommandMapper.toEntity(
            viewQuestionEvaluationCommandDTO
        );
        viewQuestionEvaluationCommand = viewQuestionEvaluationCommandRepository.save(viewQuestionEvaluationCommand);
        return viewQuestionEvaluationCommandMapper.toDto(viewQuestionEvaluationCommand);
    }

    /**
     * Partially update a viewQuestionEvaluationCommand.
     *
     * @param viewQuestionEvaluationCommandDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ViewQuestionEvaluationCommandDTO> partialUpdate(ViewQuestionEvaluationCommandDTO viewQuestionEvaluationCommandDTO) {
        log.debug("Request to partially update ViewQuestionEvaluationCommand : {}", viewQuestionEvaluationCommandDTO);

        return viewQuestionEvaluationCommandRepository
            .findById(viewQuestionEvaluationCommandDTO.getId())
            .map(existingViewQuestionEvaluationCommand -> {
                viewQuestionEvaluationCommandMapper.partialUpdate(existingViewQuestionEvaluationCommand, viewQuestionEvaluationCommandDTO);

                return existingViewQuestionEvaluationCommand;
            })
            .map(viewQuestionEvaluationCommandRepository::save)
            .map(viewQuestionEvaluationCommandMapper::toDto);
    }

    /**
     * Get all the viewQuestionEvaluationCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ViewQuestionEvaluationCommandDTO> findAll() {
        log.debug("Request to get all ViewQuestionEvaluationCommands");
        return viewQuestionEvaluationCommandRepository
            .findAll()
            .stream()
            .map(viewQuestionEvaluationCommandMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one viewQuestionEvaluationCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ViewQuestionEvaluationCommandDTO> findOne(Long id) {
        log.debug("Request to get ViewQuestionEvaluationCommand : {}", id);
        return viewQuestionEvaluationCommandRepository.findById(id).map(viewQuestionEvaluationCommandMapper::toDto);
    }

    /**
     * Delete the viewQuestionEvaluationCommand by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ViewQuestionEvaluationCommand : {}", id);
        viewQuestionEvaluationCommandRepository.deleteById(id);
    }
}
