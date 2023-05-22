package org.contextmapper.generated.gateway.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.contextmapper.generated.gateway.domain.UserPreferencesTagInfos;
import org.contextmapper.generated.gateway.repository.UserPreferencesTagInfosRepository;
import org.contextmapper.generated.gateway.service.dto.UserPreferencesTagInfosDTO;
import org.contextmapper.generated.gateway.service.mapper.UserPreferencesTagInfosMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    public Mono<UserPreferencesTagInfosDTO> save(UserPreferencesTagInfosDTO userPreferencesTagInfosDTO) {
        log.debug("Request to save UserPreferencesTagInfos : {}", userPreferencesTagInfosDTO);
        return userPreferencesTagInfosRepository
            .save(userPreferencesTagInfosMapper.toEntity(userPreferencesTagInfosDTO))
            .map(userPreferencesTagInfosMapper::toDto);
    }

    /**
     * Update a userPreferencesTagInfos.
     *
     * @param userPreferencesTagInfosDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<UserPreferencesTagInfosDTO> update(UserPreferencesTagInfosDTO userPreferencesTagInfosDTO) {
        log.debug("Request to update UserPreferencesTagInfos : {}", userPreferencesTagInfosDTO);
        return userPreferencesTagInfosRepository
            .save(userPreferencesTagInfosMapper.toEntity(userPreferencesTagInfosDTO))
            .map(userPreferencesTagInfosMapper::toDto);
    }

    /**
     * Partially update a userPreferencesTagInfos.
     *
     * @param userPreferencesTagInfosDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<UserPreferencesTagInfosDTO> partialUpdate(UserPreferencesTagInfosDTO userPreferencesTagInfosDTO) {
        log.debug("Request to partially update UserPreferencesTagInfos : {}", userPreferencesTagInfosDTO);

        return userPreferencesTagInfosRepository
            .findById(userPreferencesTagInfosDTO.getId())
            .map(existingUserPreferencesTagInfos -> {
                userPreferencesTagInfosMapper.partialUpdate(existingUserPreferencesTagInfos, userPreferencesTagInfosDTO);

                return existingUserPreferencesTagInfos;
            })
            .flatMap(userPreferencesTagInfosRepository::save)
            .map(userPreferencesTagInfosMapper::toDto);
    }

    /**
     * Get all the userPreferencesTagInfos.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<UserPreferencesTagInfosDTO> findAll() {
        log.debug("Request to get all UserPreferencesTagInfos");
        return userPreferencesTagInfosRepository.findAll().map(userPreferencesTagInfosMapper::toDto);
    }

    /**
     * Returns the number of userPreferencesTagInfos available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return userPreferencesTagInfosRepository.count();
    }

    /**
     * Get one userPreferencesTagInfos by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<UserPreferencesTagInfosDTO> findOne(Long id) {
        log.debug("Request to get UserPreferencesTagInfos : {}", id);
        return userPreferencesTagInfosRepository.findById(id).map(userPreferencesTagInfosMapper::toDto);
    }

    /**
     * Delete the userPreferencesTagInfos by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete UserPreferencesTagInfos : {}", id);
        return userPreferencesTagInfosRepository.deleteById(id);
    }
}
