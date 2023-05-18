package org.contextmapper.generated.statcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.statcontext.domain.UserStatsViewed;
import org.contextmapper.generated.statcontext.repository.UserStatsViewedRepository;
import org.contextmapper.generated.statcontext.service.dto.UserStatsViewedDTO;
import org.contextmapper.generated.statcontext.service.mapper.UserStatsViewedMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link UserStatsViewed}.
 */
@Service
@Transactional
public class UserStatsViewedService {

    private final Logger log = LoggerFactory.getLogger(UserStatsViewedService.class);

    private final UserStatsViewedRepository userStatsViewedRepository;

    private final UserStatsViewedMapper userStatsViewedMapper;

    public UserStatsViewedService(UserStatsViewedRepository userStatsViewedRepository, UserStatsViewedMapper userStatsViewedMapper) {
        this.userStatsViewedRepository = userStatsViewedRepository;
        this.userStatsViewedMapper = userStatsViewedMapper;
    }

    /**
     * Save a userStatsViewed.
     *
     * @param userStatsViewedDTO the entity to save.
     * @return the persisted entity.
     */
    public UserStatsViewedDTO save(UserStatsViewedDTO userStatsViewedDTO) {
        log.debug("Request to save UserStatsViewed : {}", userStatsViewedDTO);
        UserStatsViewed userStatsViewed = userStatsViewedMapper.toEntity(userStatsViewedDTO);
        userStatsViewed = userStatsViewedRepository.save(userStatsViewed);
        return userStatsViewedMapper.toDto(userStatsViewed);
    }

    /**
     * Update a userStatsViewed.
     *
     * @param userStatsViewedDTO the entity to save.
     * @return the persisted entity.
     */
    public UserStatsViewedDTO update(UserStatsViewedDTO userStatsViewedDTO) {
        log.debug("Request to update UserStatsViewed : {}", userStatsViewedDTO);
        UserStatsViewed userStatsViewed = userStatsViewedMapper.toEntity(userStatsViewedDTO);
        userStatsViewed = userStatsViewedRepository.save(userStatsViewed);
        return userStatsViewedMapper.toDto(userStatsViewed);
    }

    /**
     * Partially update a userStatsViewed.
     *
     * @param userStatsViewedDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<UserStatsViewedDTO> partialUpdate(UserStatsViewedDTO userStatsViewedDTO) {
        log.debug("Request to partially update UserStatsViewed : {}", userStatsViewedDTO);

        return userStatsViewedRepository
            .findById(userStatsViewedDTO.getId())
            .map(existingUserStatsViewed -> {
                userStatsViewedMapper.partialUpdate(existingUserStatsViewed, userStatsViewedDTO);

                return existingUserStatsViewed;
            })
            .map(userStatsViewedRepository::save)
            .map(userStatsViewedMapper::toDto);
    }

    /**
     * Get all the userStatsVieweds.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<UserStatsViewedDTO> findAll() {
        log.debug("Request to get all UserStatsVieweds");
        return userStatsViewedRepository
            .findAll()
            .stream()
            .map(userStatsViewedMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one userStatsViewed by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<UserStatsViewedDTO> findOne(Long id) {
        log.debug("Request to get UserStatsViewed : {}", id);
        return userStatsViewedRepository.findById(id).map(userStatsViewedMapper::toDto);
    }

    /**
     * Delete the userStatsViewed by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete UserStatsViewed : {}", id);
        userStatsViewedRepository.deleteById(id);
    }
}
