package org.contextmapper.generated.gateway.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.contextmapper.generated.gateway.domain.AnsweringUser;
import org.contextmapper.generated.gateway.repository.AnsweringUserRepository;
import org.contextmapper.generated.gateway.service.dto.AnsweringUserDTO;
import org.contextmapper.generated.gateway.service.mapper.AnsweringUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    public Mono<AnsweringUserDTO> save(AnsweringUserDTO answeringUserDTO) {
        log.debug("Request to save AnsweringUser : {}", answeringUserDTO);
        return answeringUserRepository.save(answeringUserMapper.toEntity(answeringUserDTO)).map(answeringUserMapper::toDto);
    }

    /**
     * Update a answeringUser.
     *
     * @param answeringUserDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<AnsweringUserDTO> update(AnsweringUserDTO answeringUserDTO) {
        log.debug("Request to update AnsweringUser : {}", answeringUserDTO);
        return answeringUserRepository.save(answeringUserMapper.toEntity(answeringUserDTO)).map(answeringUserMapper::toDto);
    }

    /**
     * Partially update a answeringUser.
     *
     * @param answeringUserDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<AnsweringUserDTO> partialUpdate(AnsweringUserDTO answeringUserDTO) {
        log.debug("Request to partially update AnsweringUser : {}", answeringUserDTO);

        return answeringUserRepository
            .findById(answeringUserDTO.getId())
            .map(existingAnsweringUser -> {
                answeringUserMapper.partialUpdate(existingAnsweringUser, answeringUserDTO);

                return existingAnsweringUser;
            })
            .flatMap(answeringUserRepository::save)
            .map(answeringUserMapper::toDto);
    }

    /**
     * Get all the answeringUsers.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<AnsweringUserDTO> findAll() {
        log.debug("Request to get all AnsweringUsers");
        return answeringUserRepository.findAll().map(answeringUserMapper::toDto);
    }

    /**
     * Returns the number of answeringUsers available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return answeringUserRepository.count();
    }

    /**
     * Get one answeringUser by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<AnsweringUserDTO> findOne(Long id) {
        log.debug("Request to get AnsweringUser : {}", id);
        return answeringUserRepository.findById(id).map(answeringUserMapper::toDto);
    }

    /**
     * Delete the answeringUser by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete AnsweringUser : {}", id);
        return answeringUserRepository.deleteById(id);
    }
}
