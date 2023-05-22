package org.contextmapper.generated.answercontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.answercontext.domain.UserId;
import org.contextmapper.generated.answercontext.repository.UserIdRepository;
import org.contextmapper.generated.answercontext.service.dto.UserIdDTO;
import org.contextmapper.generated.answercontext.service.mapper.UserIdMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link UserId}.
 */
@Service
@Transactional
public class UserIdService {

    private final Logger log = LoggerFactory.getLogger(UserIdService.class);

    private final UserIdRepository userIdRepository;

    private final UserIdMapper userIdMapper;

    public UserIdService(UserIdRepository userIdRepository, UserIdMapper userIdMapper) {
        this.userIdRepository = userIdRepository;
        this.userIdMapper = userIdMapper;
    }

    /**
     * Save a userId.
     *
     * @param userIdDTO the entity to save.
     * @return the persisted entity.
     */
    public UserIdDTO save(UserIdDTO userIdDTO) {
        log.debug("Request to save UserId : {}", userIdDTO);
        UserId userId = userIdMapper.toEntity(userIdDTO);
        userId = userIdRepository.save(userId);
        return userIdMapper.toDto(userId);
    }

    /**
     * Update a userId.
     *
     * @param userIdDTO the entity to save.
     * @return the persisted entity.
     */
    public UserIdDTO update(UserIdDTO userIdDTO) {
        log.debug("Request to update UserId : {}", userIdDTO);
        UserId userId = userIdMapper.toEntity(userIdDTO);
        // no save call needed as we have no fields that can be updated
        return userIdMapper.toDto(userId);
    }

    /**
     * Partially update a userId.
     *
     * @param userIdDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<UserIdDTO> partialUpdate(UserIdDTO userIdDTO) {
        log.debug("Request to partially update UserId : {}", userIdDTO);

        return userIdRepository
            .findById(userIdDTO.getId())
            .map(existingUserId -> {
                userIdMapper.partialUpdate(existingUserId, userIdDTO);

                return existingUserId;
            })
            // .map(userIdRepository::save)
            .map(userIdMapper::toDto);
    }

    /**
     * Get all the userIds.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<UserIdDTO> findAll() {
        log.debug("Request to get all UserIds");
        return userIdRepository.findAll().stream().map(userIdMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one userId by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<UserIdDTO> findOne(Long id) {
        log.debug("Request to get UserId : {}", id);
        return userIdRepository.findById(id).map(userIdMapper::toDto);
    }

    /**
     * Delete the userId by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete UserId : {}", id);
        userIdRepository.deleteById(id);
    }
}
