package org.contextmapper.generated.gateway.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.contextmapper.generated.gateway.domain.AnsweredTag;
import org.contextmapper.generated.gateway.repository.AnsweredTagRepository;
import org.contextmapper.generated.gateway.service.dto.AnsweredTagDTO;
import org.contextmapper.generated.gateway.service.mapper.AnsweredTagMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link AnsweredTag}.
 */
@Service
@Transactional
public class AnsweredTagService {

    private final Logger log = LoggerFactory.getLogger(AnsweredTagService.class);

    private final AnsweredTagRepository answeredTagRepository;

    private final AnsweredTagMapper answeredTagMapper;

    public AnsweredTagService(AnsweredTagRepository answeredTagRepository, AnsweredTagMapper answeredTagMapper) {
        this.answeredTagRepository = answeredTagRepository;
        this.answeredTagMapper = answeredTagMapper;
    }

    /**
     * Save a answeredTag.
     *
     * @param answeredTagDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<AnsweredTagDTO> save(AnsweredTagDTO answeredTagDTO) {
        log.debug("Request to save AnsweredTag : {}", answeredTagDTO);
        return answeredTagRepository.save(answeredTagMapper.toEntity(answeredTagDTO)).map(answeredTagMapper::toDto);
    }

    /**
     * Update a answeredTag.
     *
     * @param answeredTagDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<AnsweredTagDTO> update(AnsweredTagDTO answeredTagDTO) {
        log.debug("Request to update AnsweredTag : {}", answeredTagDTO);
        return answeredTagRepository.save(answeredTagMapper.toEntity(answeredTagDTO)).map(answeredTagMapper::toDto);
    }

    /**
     * Partially update a answeredTag.
     *
     * @param answeredTagDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<AnsweredTagDTO> partialUpdate(AnsweredTagDTO answeredTagDTO) {
        log.debug("Request to partially update AnsweredTag : {}", answeredTagDTO);

        return answeredTagRepository
            .findById(answeredTagDTO.getId())
            .map(existingAnsweredTag -> {
                answeredTagMapper.partialUpdate(existingAnsweredTag, answeredTagDTO);

                return existingAnsweredTag;
            })
            .flatMap(answeredTagRepository::save)
            .map(answeredTagMapper::toDto);
    }

    /**
     * Get all the answeredTags.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<AnsweredTagDTO> findAll() {
        log.debug("Request to get all AnsweredTags");
        return answeredTagRepository.findAll().map(answeredTagMapper::toDto);
    }

    /**
     * Returns the number of answeredTags available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return answeredTagRepository.count();
    }

    /**
     * Get one answeredTag by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<AnsweredTagDTO> findOne(Long id) {
        log.debug("Request to get AnsweredTag : {}", id);
        return answeredTagRepository.findById(id).map(answeredTagMapper::toDto);
    }

    /**
     * Delete the answeredTag by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete AnsweredTag : {}", id);
        return answeredTagRepository.deleteById(id);
    }
}
