package org.contextmapper.generated.usermanagementcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.usermanagementcontext.domain.UserInfos;
import org.contextmapper.generated.usermanagementcontext.repository.UserInfosRepository;
import org.contextmapper.generated.usermanagementcontext.service.dto.UserInfosDTO;
import org.contextmapper.generated.usermanagementcontext.service.mapper.UserInfosMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link UserInfos}.
 */
@Service
@Transactional
public class UserInfosService {

    private final Logger log = LoggerFactory.getLogger(UserInfosService.class);

    private final UserInfosRepository userInfosRepository;

    private final UserInfosMapper userInfosMapper;

    public UserInfosService(UserInfosRepository userInfosRepository, UserInfosMapper userInfosMapper) {
        this.userInfosRepository = userInfosRepository;
        this.userInfosMapper = userInfosMapper;
    }

    /**
     * Save a userInfos.
     *
     * @param userInfosDTO the entity to save.
     * @return the persisted entity.
     */
    public UserInfosDTO save(UserInfosDTO userInfosDTO) {
        log.debug("Request to save UserInfos : {}", userInfosDTO);
        UserInfos userInfos = userInfosMapper.toEntity(userInfosDTO);
        userInfos = userInfosRepository.save(userInfos);
        return userInfosMapper.toDto(userInfos);
    }

    /**
     * Update a userInfos.
     *
     * @param userInfosDTO the entity to save.
     * @return the persisted entity.
     */
    public UserInfosDTO update(UserInfosDTO userInfosDTO) {
        log.debug("Request to update UserInfos : {}", userInfosDTO);
        UserInfos userInfos = userInfosMapper.toEntity(userInfosDTO);
        userInfos = userInfosRepository.save(userInfos);
        return userInfosMapper.toDto(userInfos);
    }

    /**
     * Partially update a userInfos.
     *
     * @param userInfosDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<UserInfosDTO> partialUpdate(UserInfosDTO userInfosDTO) {
        log.debug("Request to partially update UserInfos : {}", userInfosDTO);

        return userInfosRepository
            .findById(userInfosDTO.getId())
            .map(existingUserInfos -> {
                userInfosMapper.partialUpdate(existingUserInfos, userInfosDTO);

                return existingUserInfos;
            })
            .map(userInfosRepository::save)
            .map(userInfosMapper::toDto);
    }

    /**
     * Get all the userInfos.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<UserInfosDTO> findAll() {
        log.debug("Request to get all UserInfos");
        return userInfosRepository.findAll().stream().map(userInfosMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one userInfos by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<UserInfosDTO> findOne(Long id) {
        log.debug("Request to get UserInfos : {}", id);
        return userInfosRepository.findById(id).map(userInfosMapper::toDto);
    }

    /**
     * Delete the userInfos by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete UserInfos : {}", id);
        userInfosRepository.deleteById(id);
    }
}
