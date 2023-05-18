package org.contextmapper.generated.evaluationcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.evaluationcontext.domain.AnsweringUser;
import org.contextmapper.generated.evaluationcontext.repository.AnsweringUserRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.AnsweringUserDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.AnsweringUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link AnsweringUser}.
 */
@Service
@Transactional
public class AnsweringUserService {

    private final Logger log = LoggerFactory.getLogger(AnsweringUserService.class);

    private final AnsweringUserRepository answeringUserRepository;

    private final AnsweringUserMapper answeringUserMapper;

    public AnsweringUserService(AnsweringUserRepository answeringUserRepository, AnsweringUserMapper answeringUserMapper) {
        this.answeringUserRepository = answeringUserRepository;
        this.answeringUserMapper = answeringUserMapper;
    }

    /**
     * Save a answeringUser.
     *
     * @param answeringUserDTO the entity to save.
     * @return the persisted entity.
     */
    public AnsweringUserDTO save(AnsweringUserDTO answeringUserDTO) {
        log.debug("Request to save AnsweringUser : {}", answeringUserDTO);
        AnsweringUser answeringUser = answeringUserMapper.toEntity(answeringUserDTO);
        answeringUser = answeringUserRepository.save(answeringUser);
        return answeringUserMapper.toDto(answeringUser);
    }

    /**
     * Update a answeringUser.
     *
     * @param answeringUserDTO the entity to save.
     * @return the persisted entity.
     */
    public AnsweringUserDTO update(AnsweringUserDTO answeringUserDTO) {
        log.debug("Request to update AnsweringUser : {}", answeringUserDTO);
        AnsweringUser answeringUser = answeringUserMapper.toEntity(answeringUserDTO);
        answeringUser = answeringUserRepository.save(answeringUser);
        return answeringUserMapper.toDto(answeringUser);
    }

    /**
     * Partially update a answeringUser.
     *
     * @param answeringUserDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<AnsweringUserDTO> partialUpdate(AnsweringUserDTO answeringUserDTO) {
        log.debug("Request to partially update AnsweringUser : {}", answeringUserDTO);

        return answeringUserRepository
            .findById(answeringUserDTO.getId())
            .map(existingAnsweringUser -> {
                answeringUserMapper.partialUpdate(existingAnsweringUser, answeringUserDTO);

                return existingAnsweringUser;
            })
            .map(answeringUserRepository::save)
            .map(answeringUserMapper::toDto);
    }

    /**
     * Get all the answeringUsers.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AnsweringUserDTO> findAll() {
        log.debug("Request to get all AnsweringUsers");
        return answeringUserRepository.findAll().stream().map(answeringUserMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one answeringUser by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AnsweringUserDTO> findOne(Long id) {
        log.debug("Request to get AnsweringUser : {}", id);
        return answeringUserRepository.findById(id).map(answeringUserMapper::toDto);
    }

    /**
     * Delete the answeringUser by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AnsweringUser : {}", id);
        answeringUserRepository.deleteById(id);
    }
}
