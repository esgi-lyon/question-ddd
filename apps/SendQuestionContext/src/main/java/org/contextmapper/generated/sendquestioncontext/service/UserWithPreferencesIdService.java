package org.contextmapper.generated.sendquestioncontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.sendquestioncontext.domain.UserWithPreferencesId;
import org.contextmapper.generated.sendquestioncontext.repository.UserWithPreferencesIdRepository;
import org.contextmapper.generated.sendquestioncontext.service.dto.UserWithPreferencesIdDTO;
import org.contextmapper.generated.sendquestioncontext.service.mapper.UserWithPreferencesIdMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public UserWithPreferencesIdDTO save(UserWithPreferencesIdDTO userWithPreferencesIdDTO) {
        log.debug("Request to save UserWithPreferencesId : {}", userWithPreferencesIdDTO);
        UserWithPreferencesId userWithPreferencesId = userWithPreferencesIdMapper.toEntity(userWithPreferencesIdDTO);
        userWithPreferencesId = userWithPreferencesIdRepository.save(userWithPreferencesId);
        return userWithPreferencesIdMapper.toDto(userWithPreferencesId);
    }

    /**
     * Update a userWithPreferencesId.
     *
     * @param userWithPreferencesIdDTO the entity to save.
     * @return the persisted entity.
     */
    public UserWithPreferencesIdDTO update(UserWithPreferencesIdDTO userWithPreferencesIdDTO) {
        log.debug("Request to update UserWithPreferencesId : {}", userWithPreferencesIdDTO);
        UserWithPreferencesId userWithPreferencesId = userWithPreferencesIdMapper.toEntity(userWithPreferencesIdDTO);
        userWithPreferencesId = userWithPreferencesIdRepository.save(userWithPreferencesId);
        return userWithPreferencesIdMapper.toDto(userWithPreferencesId);
    }

    /**
     * Partially update a userWithPreferencesId.
     *
     * @param userWithPreferencesIdDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<UserWithPreferencesIdDTO> partialUpdate(UserWithPreferencesIdDTO userWithPreferencesIdDTO) {
        log.debug("Request to partially update UserWithPreferencesId : {}", userWithPreferencesIdDTO);

        return userWithPreferencesIdRepository
            .findById(userWithPreferencesIdDTO.getId())
            .map(existingUserWithPreferencesId -> {
                userWithPreferencesIdMapper.partialUpdate(existingUserWithPreferencesId, userWithPreferencesIdDTO);

                return existingUserWithPreferencesId;
            })
            .map(userWithPreferencesIdRepository::save)
            .map(userWithPreferencesIdMapper::toDto);
    }

    /**
     * Get all the userWithPreferencesIds.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<UserWithPreferencesIdDTO> findAll() {
        log.debug("Request to get all UserWithPreferencesIds");
        return userWithPreferencesIdRepository
            .findAll()
            .stream()
            .map(userWithPreferencesIdMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one userWithPreferencesId by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<UserWithPreferencesIdDTO> findOne(Long id) {
        log.debug("Request to get UserWithPreferencesId : {}", id);
        return userWithPreferencesIdRepository.findById(id).map(userWithPreferencesIdMapper::toDto);
    }

    /**
     * Delete the userWithPreferencesId by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete UserWithPreferencesId : {}", id);
        userWithPreferencesIdRepository.deleteById(id);
    }
}
