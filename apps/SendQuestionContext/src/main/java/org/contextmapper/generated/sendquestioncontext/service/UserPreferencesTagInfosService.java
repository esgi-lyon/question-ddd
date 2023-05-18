package org.contextmapper.generated.sendquestioncontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.sendquestioncontext.domain.UserPreferencesTagInfos;
import org.contextmapper.generated.sendquestioncontext.repository.UserPreferencesTagInfosRepository;
import org.contextmapper.generated.sendquestioncontext.service.dto.UserPreferencesTagInfosDTO;
import org.contextmapper.generated.sendquestioncontext.service.mapper.UserPreferencesTagInfosMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link UserPreferencesTagInfos}.
 */
@Service
@Transactional
public class UserPreferencesTagInfosService {

    private final Logger log = LoggerFactory.getLogger(UserPreferencesTagInfosService.class);

    private final UserPreferencesTagInfosRepository userPreferencesTagInfosRepository;

    private final UserPreferencesTagInfosMapper userPreferencesTagInfosMapper;

    public UserPreferencesTagInfosService(
        UserPreferencesTagInfosRepository userPreferencesTagInfosRepository,
        UserPreferencesTagInfosMapper userPreferencesTagInfosMapper
    ) {
        this.userPreferencesTagInfosRepository = userPreferencesTagInfosRepository;
        this.userPreferencesTagInfosMapper = userPreferencesTagInfosMapper;
    }

    /**
     * Save a userPreferencesTagInfos.
     *
     * @param userPreferencesTagInfosDTO the entity to save.
     * @return the persisted entity.
     */
    public UserPreferencesTagInfosDTO save(UserPreferencesTagInfosDTO userPreferencesTagInfosDTO) {
        log.debug("Request to save UserPreferencesTagInfos : {}", userPreferencesTagInfosDTO);
        UserPreferencesTagInfos userPreferencesTagInfos = userPreferencesTagInfosMapper.toEntity(userPreferencesTagInfosDTO);
        userPreferencesTagInfos = userPreferencesTagInfosRepository.save(userPreferencesTagInfos);
        return userPreferencesTagInfosMapper.toDto(userPreferencesTagInfos);
    }

    /**
     * Update a userPreferencesTagInfos.
     *
     * @param userPreferencesTagInfosDTO the entity to save.
     * @return the persisted entity.
     */
    public UserPreferencesTagInfosDTO update(UserPreferencesTagInfosDTO userPreferencesTagInfosDTO) {
        log.debug("Request to update UserPreferencesTagInfos : {}", userPreferencesTagInfosDTO);
        UserPreferencesTagInfos userPreferencesTagInfos = userPreferencesTagInfosMapper.toEntity(userPreferencesTagInfosDTO);
        userPreferencesTagInfos = userPreferencesTagInfosRepository.save(userPreferencesTagInfos);
        return userPreferencesTagInfosMapper.toDto(userPreferencesTagInfos);
    }

    /**
     * Partially update a userPreferencesTagInfos.
     *
     * @param userPreferencesTagInfosDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<UserPreferencesTagInfosDTO> partialUpdate(UserPreferencesTagInfosDTO userPreferencesTagInfosDTO) {
        log.debug("Request to partially update UserPreferencesTagInfos : {}", userPreferencesTagInfosDTO);

        return userPreferencesTagInfosRepository
            .findById(userPreferencesTagInfosDTO.getId())
            .map(existingUserPreferencesTagInfos -> {
                userPreferencesTagInfosMapper.partialUpdate(existingUserPreferencesTagInfos, userPreferencesTagInfosDTO);

                return existingUserPreferencesTagInfos;
            })
            .map(userPreferencesTagInfosRepository::save)
            .map(userPreferencesTagInfosMapper::toDto);
    }

    /**
     * Get all the userPreferencesTagInfos.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<UserPreferencesTagInfosDTO> findAll() {
        log.debug("Request to get all UserPreferencesTagInfos");
        return userPreferencesTagInfosRepository
            .findAll()
            .stream()
            .map(userPreferencesTagInfosMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one userPreferencesTagInfos by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<UserPreferencesTagInfosDTO> findOne(Long id) {
        log.debug("Request to get UserPreferencesTagInfos : {}", id);
        return userPreferencesTagInfosRepository.findById(id).map(userPreferencesTagInfosMapper::toDto);
    }

    /**
     * Delete the userPreferencesTagInfos by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete UserPreferencesTagInfos : {}", id);
        userPreferencesTagInfosRepository.deleteById(id);
    }
}
