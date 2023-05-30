package org.contextmapper.generated.evaluationcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.evaluationcontext.domain.ViewUserEvaluationCommand;
import org.contextmapper.generated.evaluationcontext.repository.ViewUserEvaluationCommandRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.ViewUserEvaluationCommandDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.ViewUserEvaluationCommandMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ViewUserEvaluationCommand}.
 */
@Service
@Transactional
public class ViewUserEvaluationCommandService {

    private final Logger log = LoggerFactory.getLogger(ViewUserEvaluationCommandService.class);

    private final ViewUserEvaluationCommandRepository viewUserEvaluationCommandRepository;

    private final ViewUserEvaluationCommandMapper viewUserEvaluationCommandMapper;

    public ViewUserEvaluationCommandService(
        ViewUserEvaluationCommandRepository viewUserEvaluationCommandRepository,
        ViewUserEvaluationCommandMapper viewUserEvaluationCommandMapper
    ) {
        this.viewUserEvaluationCommandRepository = viewUserEvaluationCommandRepository;
        this.viewUserEvaluationCommandMapper = viewUserEvaluationCommandMapper;
    }

    /**
     * Save a viewUserEvaluationCommand.
     *
     * @param viewUserEvaluationCommandDTO the entity to save.
     * @return the persisted entity.
     */
    public ViewUserEvaluationCommandDTO save(ViewUserEvaluationCommandDTO viewUserEvaluationCommandDTO) {
        log.debug("Request to save ViewUserEvaluationCommand : {}", viewUserEvaluationCommandDTO);
        ViewUserEvaluationCommand viewUserEvaluationCommand = viewUserEvaluationCommandMapper.toEntity(viewUserEvaluationCommandDTO);
        viewUserEvaluationCommand = viewUserEvaluationCommandRepository.save(viewUserEvaluationCommand);
        return viewUserEvaluationCommandMapper.toDto(viewUserEvaluationCommand);
    }

    /**
     * Update a viewUserEvaluationCommand.
     *
     * @param viewUserEvaluationCommandDTO the entity to save.
     * @return the persisted entity.
     */
    public ViewUserEvaluationCommandDTO update(ViewUserEvaluationCommandDTO viewUserEvaluationCommandDTO) {
        log.debug("Request to update ViewUserEvaluationCommand : {}", viewUserEvaluationCommandDTO);
        ViewUserEvaluationCommand viewUserEvaluationCommand = viewUserEvaluationCommandMapper.toEntity(viewUserEvaluationCommandDTO);
        viewUserEvaluationCommand = viewUserEvaluationCommandRepository.save(viewUserEvaluationCommand);
        return viewUserEvaluationCommandMapper.toDto(viewUserEvaluationCommand);
    }

    /**
     * Partially update a viewUserEvaluationCommand.
     *
     * @param viewUserEvaluationCommandDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ViewUserEvaluationCommandDTO> partialUpdate(ViewUserEvaluationCommandDTO viewUserEvaluationCommandDTO) {
        log.debug("Request to partially update ViewUserEvaluationCommand : {}", viewUserEvaluationCommandDTO);

        return viewUserEvaluationCommandRepository
            .findById(viewUserEvaluationCommandDTO.getId())
            .map(existingViewUserEvaluationCommand -> {
                viewUserEvaluationCommandMapper.partialUpdate(existingViewUserEvaluationCommand, viewUserEvaluationCommandDTO);

                return existingViewUserEvaluationCommand;
            })
            .map(viewUserEvaluationCommandRepository::save)
            .map(viewUserEvaluationCommandMapper::toDto);
    }

    /**
     * Get all the viewUserEvaluationCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ViewUserEvaluationCommandDTO> findAll() {
        log.debug("Request to get all ViewUserEvaluationCommands");
        return viewUserEvaluationCommandRepository
            .findAll()
            .stream()
            .map(viewUserEvaluationCommandMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one viewUserEvaluationCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ViewUserEvaluationCommandDTO> findOne(Long id) {
        log.debug("Request to get ViewUserEvaluationCommand : {}", id);
        return viewUserEvaluationCommandRepository.findById(id).map(viewUserEvaluationCommandMapper::toDto);
    }

    /**
     * Delete the viewUserEvaluationCommand by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ViewUserEvaluationCommand : {}", id);
        viewUserEvaluationCommandRepository.deleteById(id);
    }
}
