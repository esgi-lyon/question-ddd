package org.contextmapper.generated.usermanagementcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.usermanagementcontext.domain.RejectUserCommand;
import org.contextmapper.generated.usermanagementcontext.repository.RejectUserCommandRepository;
import org.contextmapper.generated.usermanagementcontext.service.dto.RejectUserCommandDTO;
import org.contextmapper.generated.usermanagementcontext.service.mapper.RejectUserCommandMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link RejectUserCommand}.
 */
@Service
@Transactional
public class RejectUserCommandService {

    private final Logger log = LoggerFactory.getLogger(RejectUserCommandService.class);

    private final RejectUserCommandRepository rejectUserCommandRepository;

    private final RejectUserCommandMapper rejectUserCommandMapper;

    public RejectUserCommandService(
        RejectUserCommandRepository rejectUserCommandRepository,
        RejectUserCommandMapper rejectUserCommandMapper
    ) {
        this.rejectUserCommandRepository = rejectUserCommandRepository;
        this.rejectUserCommandMapper = rejectUserCommandMapper;
    }

    /**
     * Save a rejectUserCommand.
     *
     * @param rejectUserCommandDTO the entity to save.
     * @return the persisted entity.
     */
    public RejectUserCommandDTO save(RejectUserCommandDTO rejectUserCommandDTO) {
        log.debug("Request to save RejectUserCommand : {}", rejectUserCommandDTO);
        RejectUserCommand rejectUserCommand = rejectUserCommandMapper.toEntity(rejectUserCommandDTO);
        rejectUserCommand = rejectUserCommandRepository.save(rejectUserCommand);
        return rejectUserCommandMapper.toDto(rejectUserCommand);
    }

    /**
     * Update a rejectUserCommand.
     *
     * @param rejectUserCommandDTO the entity to save.
     * @return the persisted entity.
     */
    public RejectUserCommandDTO update(RejectUserCommandDTO rejectUserCommandDTO) {
        log.debug("Request to update RejectUserCommand : {}", rejectUserCommandDTO);
        RejectUserCommand rejectUserCommand = rejectUserCommandMapper.toEntity(rejectUserCommandDTO);
        rejectUserCommand = rejectUserCommandRepository.save(rejectUserCommand);
        return rejectUserCommandMapper.toDto(rejectUserCommand);
    }

    /**
     * Partially update a rejectUserCommand.
     *
     * @param rejectUserCommandDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<RejectUserCommandDTO> partialUpdate(RejectUserCommandDTO rejectUserCommandDTO) {
        log.debug("Request to partially update RejectUserCommand : {}", rejectUserCommandDTO);

        return rejectUserCommandRepository
            .findById(rejectUserCommandDTO.getId())
            .map(existingRejectUserCommand -> {
                rejectUserCommandMapper.partialUpdate(existingRejectUserCommand, rejectUserCommandDTO);

                return existingRejectUserCommand;
            })
            .map(rejectUserCommandRepository::save)
            .map(rejectUserCommandMapper::toDto);
    }

    /**
     * Get all the rejectUserCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<RejectUserCommandDTO> findAll() {
        log.debug("Request to get all RejectUserCommands");
        return rejectUserCommandRepository
            .findAll()
            .stream()
            .map(rejectUserCommandMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one rejectUserCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RejectUserCommandDTO> findOne(Long id) {
        log.debug("Request to get RejectUserCommand : {}", id);
        return rejectUserCommandRepository.findById(id).map(rejectUserCommandMapper::toDto);
    }

    /**
     * Delete the rejectUserCommand by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete RejectUserCommand : {}", id);
        rejectUserCommandRepository.deleteById(id);
    }
}
