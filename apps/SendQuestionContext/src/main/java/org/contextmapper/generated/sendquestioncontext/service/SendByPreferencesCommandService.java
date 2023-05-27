package org.contextmapper.generated.sendquestioncontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.sendquestioncontext.domain.SendByPreferencesCommand;
import org.contextmapper.generated.sendquestioncontext.repository.SendByPreferencesCommandRepository;
import org.contextmapper.generated.sendquestioncontext.service.dto.SendByPreferencesCommandDTO;
import org.contextmapper.generated.sendquestioncontext.service.mapper.SendByPreferencesCommandMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link SendByPreferencesCommand}.
 */
@Service
@Transactional
public class SendByPreferencesCommandService {

    private final Logger log = LoggerFactory.getLogger(SendByPreferencesCommandService.class);

    private final SendByPreferencesCommandRepository sendByPreferencesCommandRepository;

    private final SendByPreferencesCommandMapper sendByPreferencesCommandMapper;

    public SendByPreferencesCommandService(
        SendByPreferencesCommandRepository sendByPreferencesCommandRepository,
        SendByPreferencesCommandMapper sendByPreferencesCommandMapper
    ) {
        this.sendByPreferencesCommandRepository = sendByPreferencesCommandRepository;
        this.sendByPreferencesCommandMapper = sendByPreferencesCommandMapper;
    }

    /**
     * Save a sendByPreferencesCommand.
     *
     * @param sendByPreferencesCommandDTO the entity to save.
     * @return the persisted entity.
     */
    public SendByPreferencesCommandDTO save(SendByPreferencesCommandDTO sendByPreferencesCommandDTO) {
        log.debug("Request to save SendByPreferencesCommand : {}", sendByPreferencesCommandDTO);
        SendByPreferencesCommand sendByPreferencesCommand = sendByPreferencesCommandMapper.toEntity(sendByPreferencesCommandDTO);
        sendByPreferencesCommand = sendByPreferencesCommandRepository.save(sendByPreferencesCommand);
        return sendByPreferencesCommandMapper.toDto(sendByPreferencesCommand);
    }

    /**
     * Update a sendByPreferencesCommand.
     *
     * @param sendByPreferencesCommandDTO the entity to save.
     * @return the persisted entity.
     */
    public SendByPreferencesCommandDTO update(SendByPreferencesCommandDTO sendByPreferencesCommandDTO) {
        log.debug("Request to update SendByPreferencesCommand : {}", sendByPreferencesCommandDTO);
        SendByPreferencesCommand sendByPreferencesCommand = sendByPreferencesCommandMapper.toEntity(sendByPreferencesCommandDTO);
        sendByPreferencesCommand = sendByPreferencesCommandRepository.save(sendByPreferencesCommand);
        return sendByPreferencesCommandMapper.toDto(sendByPreferencesCommand);
    }

    /**
     * Partially update a sendByPreferencesCommand.
     *
     * @param sendByPreferencesCommandDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<SendByPreferencesCommandDTO> partialUpdate(SendByPreferencesCommandDTO sendByPreferencesCommandDTO) {
        log.debug("Request to partially update SendByPreferencesCommand : {}", sendByPreferencesCommandDTO);

        return sendByPreferencesCommandRepository
            .findById(sendByPreferencesCommandDTO.getId())
            .map(existingSendByPreferencesCommand -> {
                sendByPreferencesCommandMapper.partialUpdate(existingSendByPreferencesCommand, sendByPreferencesCommandDTO);

                return existingSendByPreferencesCommand;
            })
            .map(sendByPreferencesCommandRepository::save)
            .map(sendByPreferencesCommandMapper::toDto);
    }

    /**
     * Get all the sendByPreferencesCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<SendByPreferencesCommandDTO> findAll() {
        log.debug("Request to get all SendByPreferencesCommands");
        return sendByPreferencesCommandRepository
            .findAll()
            .stream()
            .map(sendByPreferencesCommandMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one sendByPreferencesCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SendByPreferencesCommandDTO> findOne(Long id) {
        log.debug("Request to get SendByPreferencesCommand : {}", id);
        return sendByPreferencesCommandRepository.findById(id).map(sendByPreferencesCommandMapper::toDto);
    }

    /**
     * Delete the sendByPreferencesCommand by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete SendByPreferencesCommand : {}", id);
        sendByPreferencesCommandRepository.deleteById(id);
    }
}
