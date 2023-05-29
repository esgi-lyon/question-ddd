package org.contextmapper.generated.evaluationcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.evaluationcontext.domain.UserAndLevel;
import org.contextmapper.generated.evaluationcontext.repository.UserAndLevelRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.UserAndLevelDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.UserAndLevelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link UserAndLevel}.
 */
@Service
@Transactional
public class UserAndLevelService {

    private final Logger log = LoggerFactory.getLogger(UserAndLevelService.class);

    private final UserAndLevelRepository userAndLevelRepository;

    private final UserAndLevelMapper userAndLevelMapper;

    public UserAndLevelService(UserAndLevelRepository userAndLevelRepository, UserAndLevelMapper userAndLevelMapper) {
        this.userAndLevelRepository = userAndLevelRepository;
        this.userAndLevelMapper = userAndLevelMapper;
    }

    /**
     * Save a userAndLevel.
     *
     * @param userAndLevelDTO the entity to save.
     * @return the persisted entity.
     */
    public UserAndLevelDTO save(UserAndLevelDTO userAndLevelDTO) {
        log.debug("Request to save UserAndLevel : {}", userAndLevelDTO);
        UserAndLevel userAndLevel = userAndLevelMapper.toEntity(userAndLevelDTO);
        userAndLevel = userAndLevelRepository.save(userAndLevel);
        return userAndLevelMapper.toDto(userAndLevel);
    }

    /**
     * Update a userAndLevel.
     *
     * @param userAndLevelDTO the entity to save.
     * @return the persisted entity.
     */
    public UserAndLevelDTO update(UserAndLevelDTO userAndLevelDTO) {
        log.debug("Request to update UserAndLevel : {}", userAndLevelDTO);
        UserAndLevel userAndLevel = userAndLevelMapper.toEntity(userAndLevelDTO);
        userAndLevel = userAndLevelRepository.save(userAndLevel);
        return userAndLevelMapper.toDto(userAndLevel);
    }

    /**
     * Partially update a userAndLevel.
     *
     * @param userAndLevelDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<UserAndLevelDTO> partialUpdate(UserAndLevelDTO userAndLevelDTO) {
        log.debug("Request to partially update UserAndLevel : {}", userAndLevelDTO);

        return userAndLevelRepository
            .findById(userAndLevelDTO.getId())
            .map(existingUserAndLevel -> {
                userAndLevelMapper.partialUpdate(existingUserAndLevel, userAndLevelDTO);

                return existingUserAndLevel;
            })
            .map(userAndLevelRepository::save)
            .map(userAndLevelMapper::toDto);
    }

    /**
     * Get all the userAndLevels.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<UserAndLevelDTO> findAll() {
        log.debug("Request to get all UserAndLevels");
        return userAndLevelRepository.findAll().stream().map(userAndLevelMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one userAndLevel by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<UserAndLevelDTO> findOne(Long id) {
        log.debug("Request to get UserAndLevel : {}", id);
        return userAndLevelRepository.findById(id).map(userAndLevelMapper::toDto);
    }

    /**
     * Delete the userAndLevel by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete UserAndLevel : {}", id);
        userAndLevelRepository.deleteById(id);
    }
}
