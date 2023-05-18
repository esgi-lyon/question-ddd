package org.contextmapper.generated.sendquestioncontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.sendquestioncontext.domain.SendQuestionByTagsPreferences;
import org.contextmapper.generated.sendquestioncontext.repository.SendQuestionByTagsPreferencesRepository;
import org.contextmapper.generated.sendquestioncontext.service.dto.SendQuestionByTagsPreferencesDTO;
import org.contextmapper.generated.sendquestioncontext.service.mapper.SendQuestionByTagsPreferencesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link SendQuestionByTagsPreferences}.
 */
@Service
@Transactional
public class SendQuestionByTagsPreferencesService {

    private final Logger log = LoggerFactory.getLogger(SendQuestionByTagsPreferencesService.class);

    private final SendQuestionByTagsPreferencesRepository sendQuestionByTagsPreferencesRepository;

    private final SendQuestionByTagsPreferencesMapper sendQuestionByTagsPreferencesMapper;

    public SendQuestionByTagsPreferencesService(
        SendQuestionByTagsPreferencesRepository sendQuestionByTagsPreferencesRepository,
        SendQuestionByTagsPreferencesMapper sendQuestionByTagsPreferencesMapper
    ) {
        this.sendQuestionByTagsPreferencesRepository = sendQuestionByTagsPreferencesRepository;
        this.sendQuestionByTagsPreferencesMapper = sendQuestionByTagsPreferencesMapper;
    }

    /**
     * Save a sendQuestionByTagsPreferences.
     *
     * @param sendQuestionByTagsPreferencesDTO the entity to save.
     * @return the persisted entity.
     */
    public SendQuestionByTagsPreferencesDTO save(SendQuestionByTagsPreferencesDTO sendQuestionByTagsPreferencesDTO) {
        log.debug("Request to save SendQuestionByTagsPreferences : {}", sendQuestionByTagsPreferencesDTO);
        SendQuestionByTagsPreferences sendQuestionByTagsPreferences = sendQuestionByTagsPreferencesMapper.toEntity(
            sendQuestionByTagsPreferencesDTO
        );
        sendQuestionByTagsPreferences = sendQuestionByTagsPreferencesRepository.save(sendQuestionByTagsPreferences);
        return sendQuestionByTagsPreferencesMapper.toDto(sendQuestionByTagsPreferences);
    }

    /**
     * Update a sendQuestionByTagsPreferences.
     *
     * @param sendQuestionByTagsPreferencesDTO the entity to save.
     * @return the persisted entity.
     */
    public SendQuestionByTagsPreferencesDTO update(SendQuestionByTagsPreferencesDTO sendQuestionByTagsPreferencesDTO) {
        log.debug("Request to update SendQuestionByTagsPreferences : {}", sendQuestionByTagsPreferencesDTO);
        SendQuestionByTagsPreferences sendQuestionByTagsPreferences = sendQuestionByTagsPreferencesMapper.toEntity(
            sendQuestionByTagsPreferencesDTO
        );
        sendQuestionByTagsPreferences = sendQuestionByTagsPreferencesRepository.save(sendQuestionByTagsPreferences);
        return sendQuestionByTagsPreferencesMapper.toDto(sendQuestionByTagsPreferences);
    }

    /**
     * Partially update a sendQuestionByTagsPreferences.
     *
     * @param sendQuestionByTagsPreferencesDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<SendQuestionByTagsPreferencesDTO> partialUpdate(SendQuestionByTagsPreferencesDTO sendQuestionByTagsPreferencesDTO) {
        log.debug("Request to partially update SendQuestionByTagsPreferences : {}", sendQuestionByTagsPreferencesDTO);

        return sendQuestionByTagsPreferencesRepository
            .findById(sendQuestionByTagsPreferencesDTO.getId())
            .map(existingSendQuestionByTagsPreferences -> {
                sendQuestionByTagsPreferencesMapper.partialUpdate(existingSendQuestionByTagsPreferences, sendQuestionByTagsPreferencesDTO);

                return existingSendQuestionByTagsPreferences;
            })
            .map(sendQuestionByTagsPreferencesRepository::save)
            .map(sendQuestionByTagsPreferencesMapper::toDto);
    }

    /**
     * Get all the sendQuestionByTagsPreferences.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<SendQuestionByTagsPreferencesDTO> findAll() {
        log.debug("Request to get all SendQuestionByTagsPreferences");
        return sendQuestionByTagsPreferencesRepository
            .findAll()
            .stream()
            .map(sendQuestionByTagsPreferencesMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one sendQuestionByTagsPreferences by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SendQuestionByTagsPreferencesDTO> findOne(Long id) {
        log.debug("Request to get SendQuestionByTagsPreferences : {}", id);
        return sendQuestionByTagsPreferencesRepository.findById(id).map(sendQuestionByTagsPreferencesMapper::toDto);
    }

    /**
     * Delete the sendQuestionByTagsPreferences by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete SendQuestionByTagsPreferences : {}", id);
        sendQuestionByTagsPreferencesRepository.deleteById(id);
    }
}
