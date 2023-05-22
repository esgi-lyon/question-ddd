package org.contextmapper.generated.gateway.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.contextmapper.generated.gateway.domain.UserWithPreferencesId;
import org.contextmapper.generated.gateway.repository.UserWithPreferencesIdRepository;
import org.contextmapper.generated.gateway.service.dto.UserWithPreferencesIdDTO;
import org.contextmapper.generated.gateway.service.mapper.UserWithPreferencesIdMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link UserWithPreferencesId}.
 */
@Service
@Transactional
public class UserWithPreferencesIdService {

    private final Logger log = LoggerFactory.getLogger(UserWithPreferencesIdService.class);

    private final UserWithPreferencesIdRepository userWithPreferencesIdRepository;

    private final UserWithPreferencesIdMapper userWithPreferencesIdMapper;

    public UserWithPreferencesIdService(
        UserWithPreferencesIdRepository userWithPreferencesIdRepository,
        UserWithPreferencesIdMapper userWithPreferencesIdMapper
    ) {
        this.userWithPreferencesIdRepository = userWithPreferencesIdRepository;
        this.userWithPreferencesIdMapper = userWithPreferencesIdMapper;
    }

    /**
     * Save a userWithPreferencesId.
     *
     * @param userWithPreferencesIdDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<UserWithPreferencesIdDTO> save(UserWithPreferencesIdDTO userWithPreferencesIdDTO) {
        log.debug("Request to save UserWithPreferencesId : {}", userWithPreferencesIdDTO);
        return userWithPreferencesIdRepository
            .save(userWithPreferencesIdMapper.toEntity(userWithPreferencesIdDTO))
            .map(userWithPreferencesIdMapper::toDto);
    }

    /**
     * Update a userWithPreferencesId.
     *
     * @param userWithPreferencesIdDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<UserWithPreferencesIdDTO> update(UserWithPreferencesIdDTO userWithPreferencesIdDTO) {
        log.debug("Request to update UserWithPreferencesId : {}", userWithPreferencesIdDTO);
        return userWithPreferencesIdRepository
            .save(userWithPreferencesIdMapper.toEntity(userWithPreferencesIdDTO))
            .map(userWithPreferencesIdMapper::toDto);
    }

    /**
     * Partially update a userWithPreferencesId.
     *
     * @param userWithPreferencesIdDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<UserWithPreferencesIdDTO> partialUpdate(UserWithPreferencesIdDTO userWithPreferencesIdDTO) {
        log.debug("Request to partially update UserWithPreferencesId : {}", userWithPreferencesIdDTO);

        return userWithPreferencesIdRepository
            .findById(userWithPreferencesIdDTO.getId())
            .map(existingUserWithPreferencesId -> {
                userWithPreferencesIdMapper.partialUpdate(existingUserWithPreferencesId, userWithPreferencesIdDTO);

                return existingUserWithPreferencesId;
            })
            .flatMap(userWithPreferencesIdRepository::save)
            .map(userWithPreferencesIdMapper::toDto);
    }

    /**
     * Get all the userWithPreferencesIds.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<UserWithPreferencesIdDTO> findAll() {
        log.debug("Request to get all UserWithPreferencesIds");
        return userWithPreferencesIdRepository.findAll().map(userWithPreferencesIdMapper::toDto);
    }

    /**
     * Returns the number of userWithPreferencesIds available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return userWithPreferencesIdRepository.count();
    }

    /**
     * Get one userWithPreferencesId by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<UserWithPreferencesIdDTO> findOne(Long id) {
        log.debug("Request to get UserWithPreferencesId : {}", id);
        return userWithPreferencesIdRepository.findById(id).map(userWithPreferencesIdMapper::toDto);
    }

    /**
     * Delete the userWithPreferencesId by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete UserWithPreferencesId : {}", id);
        return userWithPreferencesIdRepository.deleteById(id);
    }
}
