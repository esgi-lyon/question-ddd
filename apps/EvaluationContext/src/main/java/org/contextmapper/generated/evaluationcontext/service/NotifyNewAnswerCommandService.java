package org.contextmapper.generated.evaluationcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.evaluationcontext.domain.NotifyNewAnswerCommand;
import org.contextmapper.generated.evaluationcontext.repository.NotifyNewAnswerCommandRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.NotifyNewAnswerCommandDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.NotifyNewAnswerCommandMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link NotifyNewAnswerCommand}.
 */
@Service
@Transactional
public class NotifyNewAnswerCommandService {

    private final Logger log = LoggerFactory.getLogger(NotifyNewAnswerCommandService.class);

    private final NotifyNewAnswerCommandRepository notifyNewAnswerCommandRepository;

    private final NotifyNewAnswerCommandMapper notifyNewAnswerCommandMapper;

    public NotifyNewAnswerCommandService(
        NotifyNewAnswerCommandRepository notifyNewAnswerCommandRepository,
        NotifyNewAnswerCommandMapper notifyNewAnswerCommandMapper
    ) {
        this.notifyNewAnswerCommandRepository = notifyNewAnswerCommandRepository;
        this.notifyNewAnswerCommandMapper = notifyNewAnswerCommandMapper;
    }

    /**
     * Save a notifyNewAnswerCommand.
     *
     * @param notifyNewAnswerCommandDTO the entity to save.
     * @return the persisted entity.
     */
    public NotifyNewAnswerCommandDTO save(NotifyNewAnswerCommandDTO notifyNewAnswerCommandDTO) {
        log.debug("Request to save NotifyNewAnswerCommand : {}", notifyNewAnswerCommandDTO);
        NotifyNewAnswerCommand notifyNewAnswerCommand = notifyNewAnswerCommandMapper.toEntity(notifyNewAnswerCommandDTO);
        notifyNewAnswerCommand = notifyNewAnswerCommandRepository.save(notifyNewAnswerCommand);
        return notifyNewAnswerCommandMapper.toDto(notifyNewAnswerCommand);
    }

    /**
     * Update a notifyNewAnswerCommand.
     *
     * @param notifyNewAnswerCommandDTO the entity to save.
     * @return the persisted entity.
     */
    public NotifyNewAnswerCommandDTO update(NotifyNewAnswerCommandDTO notifyNewAnswerCommandDTO) {
        log.debug("Request to update NotifyNewAnswerCommand : {}", notifyNewAnswerCommandDTO);
        NotifyNewAnswerCommand notifyNewAnswerCommand = notifyNewAnswerCommandMapper.toEntity(notifyNewAnswerCommandDTO);
        notifyNewAnswerCommand = notifyNewAnswerCommandRepository.save(notifyNewAnswerCommand);
        return notifyNewAnswerCommandMapper.toDto(notifyNewAnswerCommand);
    }

    /**
     * Partially update a notifyNewAnswerCommand.
     *
     * @param notifyNewAnswerCommandDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<NotifyNewAnswerCommandDTO> partialUpdate(NotifyNewAnswerCommandDTO notifyNewAnswerCommandDTO) {
        log.debug("Request to partially update NotifyNewAnswerCommand : {}", notifyNewAnswerCommandDTO);

        return notifyNewAnswerCommandRepository
            .findById(notifyNewAnswerCommandDTO.getId())
            .map(existingNotifyNewAnswerCommand -> {
                notifyNewAnswerCommandMapper.partialUpdate(existingNotifyNewAnswerCommand, notifyNewAnswerCommandDTO);

                return existingNotifyNewAnswerCommand;
            })
            .map(notifyNewAnswerCommandRepository::save)
            .map(notifyNewAnswerCommandMapper::toDto);
    }

    /**
     * Get all the notifyNewAnswerCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<NotifyNewAnswerCommandDTO> findAll() {
        log.debug("Request to get all NotifyNewAnswerCommands");
        return notifyNewAnswerCommandRepository
            .findAll()
            .stream()
            .map(notifyNewAnswerCommandMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one notifyNewAnswerCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<NotifyNewAnswerCommandDTO> findOne(Long id) {
        log.debug("Request to get NotifyNewAnswerCommand : {}", id);
        return notifyNewAnswerCommandRepository.findById(id).map(notifyNewAnswerCommandMapper::toDto);
    }

    /**
     * Delete the notifyNewAnswerCommand by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete NotifyNewAnswerCommand : {}", id);
        notifyNewAnswerCommandRepository.deleteById(id);
    }
}
