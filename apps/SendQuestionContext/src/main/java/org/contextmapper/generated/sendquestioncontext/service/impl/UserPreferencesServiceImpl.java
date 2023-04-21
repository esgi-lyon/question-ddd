package org.contextmapper.generated.sendquestioncontext.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.sendquestioncontext.domain.UserPreferences;
import org.contextmapper.generated.sendquestioncontext.repository.UserPreferencesRepository;
import org.contextmapper.generated.sendquestioncontext.service.UserPreferencesService;
import org.contextmapper.generated.sendquestioncontext.service.dto.UserPreferencesDTO;
import org.contextmapper.generated.sendquestioncontext.service.mapper.UserPreferencesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link UserPreferences}.
 */
@Service
@Transactional
public class UserPreferencesServiceImpl implements UserPreferencesService {

    private final Logger log = LoggerFactory.getLogger(UserPreferencesServiceImpl.class);

    private final UserPreferencesRepository userPreferencesRepository;

    private final UserPreferencesMapper userPreferencesMapper;

    public UserPreferencesServiceImpl(UserPreferencesRepository userPreferencesRepository, UserPreferencesMapper userPreferencesMapper) {
        this.userPreferencesRepository = userPreferencesRepository;
        this.userPreferencesMapper = userPreferencesMapper;
    }

    @Override
    public UserPreferencesDTO save(UserPreferencesDTO userPreferencesDTO) {
        log.debug("Request to save UserPreferences : {}", userPreferencesDTO);
        UserPreferences userPreferences = userPreferencesMapper.toEntity(userPreferencesDTO);
        userPreferences = userPreferencesRepository.save(userPreferences);
        return userPreferencesMapper.toDto(userPreferences);
    }

    @Override
    public UserPreferencesDTO update(UserPreferencesDTO userPreferencesDTO) {
        log.debug("Request to update UserPreferences : {}", userPreferencesDTO);
        UserPreferences userPreferences = userPreferencesMapper.toEntity(userPreferencesDTO);
        // no save call needed as we have no fields that can be updated
        return userPreferencesMapper.toDto(userPreferences);
    }

    @Override
    public Optional<UserPreferencesDTO> partialUpdate(UserPreferencesDTO userPreferencesDTO) {
        log.debug("Request to partially update UserPreferences : {}", userPreferencesDTO);

        return userPreferencesRepository
            .findById(userPreferencesDTO.getId())
            .map(existingUserPreferences -> {
                userPreferencesMapper.partialUpdate(existingUserPreferences, userPreferencesDTO);

                return existingUserPreferences;
            })
            // .map(userPreferencesRepository::save)
            .map(userPreferencesMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserPreferencesDTO> findAll() {
        log.debug("Request to get all UserPreferences");
        return userPreferencesRepository
            .findAll()
            .stream()
            .map(userPreferencesMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserPreferencesDTO> findOne(Long id) {
        log.debug("Request to get UserPreferences : {}", id);
        return userPreferencesRepository.findById(id).map(userPreferencesMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete UserPreferences : {}", id);
        userPreferencesRepository.deleteById(id);
    }
}
