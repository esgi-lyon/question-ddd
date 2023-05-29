package org.contextmapper.generated.sendquestioncontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.sendquestioncontext.domain.ViewTagsForQuestionCommand;
import org.contextmapper.generated.sendquestioncontext.repository.ViewTagsForQuestionCommandRepository;
import org.contextmapper.generated.sendquestioncontext.service.dto.ViewTagsForQuestionCommandDTO;
import org.contextmapper.generated.sendquestioncontext.service.mapper.ViewTagsForQuestionCommandMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ViewTagsForQuestionCommand}.
 */
@Service
@Transactional
public class ViewTagsForQuestionCommandService {

    private final Logger log = LoggerFactory.getLogger(ViewTagsForQuestionCommandService.class);

    private final ViewTagsForQuestionCommandRepository viewTagsForQuestionCommandRepository;

    private final ViewTagsForQuestionCommandMapper viewTagsForQuestionCommandMapper;

    public ViewTagsForQuestionCommandService(
        ViewTagsForQuestionCommandRepository viewTagsForQuestionCommandRepository,
        ViewTagsForQuestionCommandMapper viewTagsForQuestionCommandMapper
    ) {
        this.viewTagsForQuestionCommandRepository = viewTagsForQuestionCommandRepository;
        this.viewTagsForQuestionCommandMapper = viewTagsForQuestionCommandMapper;
    }

    /**
     * Save a viewTagsForQuestionCommand.
     *
     * @param viewTagsForQuestionCommandDTO the entity to save.
     * @return the persisted entity.
     */
    public ViewTagsForQuestionCommandDTO save(ViewTagsForQuestionCommandDTO viewTagsForQuestionCommandDTO) {
        log.debug("Request to save ViewTagsForQuestionCommand : {}", viewTagsForQuestionCommandDTO);
        ViewTagsForQuestionCommand viewTagsForQuestionCommand = viewTagsForQuestionCommandMapper.toEntity(viewTagsForQuestionCommandDTO);
        viewTagsForQuestionCommand = viewTagsForQuestionCommandRepository.save(viewTagsForQuestionCommand);
        return viewTagsForQuestionCommandMapper.toDto(viewTagsForQuestionCommand);
    }

    /**
     * Update a viewTagsForQuestionCommand.
     *
     * @param viewTagsForQuestionCommandDTO the entity to save.
     * @return the persisted entity.
     */
    public ViewTagsForQuestionCommandDTO update(ViewTagsForQuestionCommandDTO viewTagsForQuestionCommandDTO) {
        log.debug("Request to update ViewTagsForQuestionCommand : {}", viewTagsForQuestionCommandDTO);
        ViewTagsForQuestionCommand viewTagsForQuestionCommand = viewTagsForQuestionCommandMapper.toEntity(viewTagsForQuestionCommandDTO);
        viewTagsForQuestionCommand = viewTagsForQuestionCommandRepository.save(viewTagsForQuestionCommand);
        return viewTagsForQuestionCommandMapper.toDto(viewTagsForQuestionCommand);
    }

    /**
     * Partially update a viewTagsForQuestionCommand.
     *
     * @param viewTagsForQuestionCommandDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ViewTagsForQuestionCommandDTO> partialUpdate(ViewTagsForQuestionCommandDTO viewTagsForQuestionCommandDTO) {
        log.debug("Request to partially update ViewTagsForQuestionCommand : {}", viewTagsForQuestionCommandDTO);

        return viewTagsForQuestionCommandRepository
            .findById(viewTagsForQuestionCommandDTO.getId())
            .map(existingViewTagsForQuestionCommand -> {
                viewTagsForQuestionCommandMapper.partialUpdate(existingViewTagsForQuestionCommand, viewTagsForQuestionCommandDTO);

                return existingViewTagsForQuestionCommand;
            })
            .map(viewTagsForQuestionCommandRepository::save)
            .map(viewTagsForQuestionCommandMapper::toDto);
    }

    /**
     * Get all the viewTagsForQuestionCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ViewTagsForQuestionCommandDTO> findAll() {
        log.debug("Request to get all ViewTagsForQuestionCommands");
        return viewTagsForQuestionCommandRepository
            .findAll()
            .stream()
            .map(viewTagsForQuestionCommandMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one viewTagsForQuestionCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ViewTagsForQuestionCommandDTO> findOne(Long id) {
        log.debug("Request to get ViewTagsForQuestionCommand : {}", id);
        return viewTagsForQuestionCommandRepository.findById(id).map(viewTagsForQuestionCommandMapper::toDto);
    }

    /**
     * Delete the viewTagsForQuestionCommand by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ViewTagsForQuestionCommand : {}", id);
        viewTagsForQuestionCommandRepository.deleteById(id);
    }
}
