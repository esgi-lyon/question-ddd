package org.contextmapper.generated.usermanagementcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.usermanagementcontext.domain.ViewUserByEmailCommand;
import org.contextmapper.generated.usermanagementcontext.repository.ViewUserByEmailCommandRepository;
import org.contextmapper.generated.usermanagementcontext.service.dto.ViewUserByEmailCommandDTO;
import org.contextmapper.generated.usermanagementcontext.service.mapper.ViewUserByEmailCommandMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ViewUserByEmailCommand}.
 */
@Service
@Transactional
public class ViewUserByEmailCommandService {

    private final Logger log = LoggerFactory.getLogger(ViewUserByEmailCommandService.class);

    private final ViewUserByEmailCommandRepository viewUserByEmailCommandRepository;

    private final ViewUserByEmailCommandMapper viewUserByEmailCommandMapper;

    public ViewUserByEmailCommandService(
        ViewUserByEmailCommandRepository viewUserByEmailCommandRepository,
        ViewUserByEmailCommandMapper viewUserByEmailCommandMapper
    ) {
        this.viewUserByEmailCommandRepository = viewUserByEmailCommandRepository;
        this.viewUserByEmailCommandMapper = viewUserByEmailCommandMapper;
    }

    /**
     * Save a viewUserByEmailCommand.
     *
     * @param viewUserByEmailCommandDTO the entity to save.
     * @return the persisted entity.
     */
    public ViewUserByEmailCommandDTO save(ViewUserByEmailCommandDTO viewUserByEmailCommandDTO) {
        log.debug("Request to save ViewUserByEmailCommand : {}", viewUserByEmailCommandDTO);
        ViewUserByEmailCommand viewUserByEmailCommand = viewUserByEmailCommandMapper.toEntity(viewUserByEmailCommandDTO);
        viewUserByEmailCommand = viewUserByEmailCommandRepository.save(viewUserByEmailCommand);
        return viewUserByEmailCommandMapper.toDto(viewUserByEmailCommand);
    }

    /**
     * Update a viewUserByEmailCommand.
     *
     * @param viewUserByEmailCommandDTO the entity to save.
     * @return the persisted entity.
     */
    public ViewUserByEmailCommandDTO update(ViewUserByEmailCommandDTO viewUserByEmailCommandDTO) {
        log.debug("Request to update ViewUserByEmailCommand : {}", viewUserByEmailCommandDTO);
        ViewUserByEmailCommand viewUserByEmailCommand = viewUserByEmailCommandMapper.toEntity(viewUserByEmailCommandDTO);
        viewUserByEmailCommand = viewUserByEmailCommandRepository.save(viewUserByEmailCommand);
        return viewUserByEmailCommandMapper.toDto(viewUserByEmailCommand);
    }

    /**
     * Partially update a viewUserByEmailCommand.
     *
     * @param viewUserByEmailCommandDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ViewUserByEmailCommandDTO> partialUpdate(ViewUserByEmailCommandDTO viewUserByEmailCommandDTO) {
        log.debug("Request to partially update ViewUserByEmailCommand : {}", viewUserByEmailCommandDTO);

        return viewUserByEmailCommandRepository
            .findById(viewUserByEmailCommandDTO.getId())
            .map(existingViewUserByEmailCommand -> {
                viewUserByEmailCommandMapper.partialUpdate(existingViewUserByEmailCommand, viewUserByEmailCommandDTO);

                return existingViewUserByEmailCommand;
            })
            .map(viewUserByEmailCommandRepository::save)
            .map(viewUserByEmailCommandMapper::toDto);
    }

    /**
     * Get all the viewUserByEmailCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ViewUserByEmailCommandDTO> findAll() {
        log.debug("Request to get all ViewUserByEmailCommands");
        return viewUserByEmailCommandRepository
            .findAll()
            .stream()
            .map(viewUserByEmailCommandMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one viewUserByEmailCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ViewUserByEmailCommandDTO> findOne(Long id) {
        log.debug("Request to get ViewUserByEmailCommand : {}", id);
        return viewUserByEmailCommandRepository.findById(id).map(viewUserByEmailCommandMapper::toDto);
    }

    /**
     * Delete the viewUserByEmailCommand by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ViewUserByEmailCommand : {}", id);
        viewUserByEmailCommandRepository.deleteById(id);
    }
}
