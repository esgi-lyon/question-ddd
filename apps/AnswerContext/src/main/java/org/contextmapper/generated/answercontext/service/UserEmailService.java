package org.contextmapper.generated.answercontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.answercontext.domain.UserEmail;
import org.contextmapper.generated.answercontext.repository.UserEmailRepository;
import org.contextmapper.generated.answercontext.service.dto.UserEmailDTO;
import org.contextmapper.generated.answercontext.service.mapper.UserEmailMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link UserEmail}.
 */
@Service
@Transactional
public class UserEmailService {

    private final Logger log = LoggerFactory.getLogger(UserEmailService.class);

    private final UserEmailRepository userEmailRepository;

    private final UserEmailMapper userEmailMapper;

    public UserEmailService(UserEmailRepository userEmailRepository, UserEmailMapper userEmailMapper) {
        this.userEmailRepository = userEmailRepository;
        this.userEmailMapper = userEmailMapper;
    }

    /**
     * Save a userEmail.
     *
     * @param userEmailDTO the entity to save.
     * @return the persisted entity.
     */
    public UserEmailDTO save(UserEmailDTO userEmailDTO) {
        log.debug("Request to save UserEmail : {}", userEmailDTO);
        UserEmail userEmail = userEmailMapper.toEntity(userEmailDTO);
        userEmail = userEmailRepository.save(userEmail);
        return userEmailMapper.toDto(userEmail);
    }

    /**
     * Update a userEmail.
     *
     * @param userEmailDTO the entity to save.
     * @return the persisted entity.
     */
    public UserEmailDTO update(UserEmailDTO userEmailDTO) {
        log.debug("Request to update UserEmail : {}", userEmailDTO);
        UserEmail userEmail = userEmailMapper.toEntity(userEmailDTO);
        userEmail = userEmailRepository.save(userEmail);
        return userEmailMapper.toDto(userEmail);
    }

    /**
     * Partially update a userEmail.
     *
     * @param userEmailDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<UserEmailDTO> partialUpdate(UserEmailDTO userEmailDTO) {
        log.debug("Request to partially update UserEmail : {}", userEmailDTO);

        return userEmailRepository
            .findById(userEmailDTO.getId())
            .map(existingUserEmail -> {
                userEmailMapper.partialUpdate(existingUserEmail, userEmailDTO);

                return existingUserEmail;
            })
            .map(userEmailRepository::save)
            .map(userEmailMapper::toDto);
    }

    /**
     * Get all the userEmails.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<UserEmailDTO> findAll() {
        log.debug("Request to get all UserEmails");
        return userEmailRepository.findAll().stream().map(userEmailMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one userEmail by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<UserEmailDTO> findOne(Long id) {
        log.debug("Request to get UserEmail : {}", id);
        return userEmailRepository.findById(id).map(userEmailMapper::toDto);
    }

    /**
     * Delete the userEmail by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete UserEmail : {}", id);
        userEmailRepository.deleteById(id);
    }
}
