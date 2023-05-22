package org.contextmapper.generated.gateway.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.contextmapper.generated.gateway.domain.TagInfos;
import org.contextmapper.generated.gateway.repository.TagInfosRepository;
import org.contextmapper.generated.gateway.service.dto.TagInfosDTO;
import org.contextmapper.generated.gateway.service.mapper.TagInfosMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link TagInfos}.
 */
@Service
@Transactional
public class TagInfosService {

    private final Logger log = LoggerFactory.getLogger(TagInfosService.class);

    private final TagInfosRepository tagInfosRepository;

    private final TagInfosMapper tagInfosMapper;

    public TagInfosService(TagInfosRepository tagInfosRepository, TagInfosMapper tagInfosMapper) {
        this.tagInfosRepository = tagInfosRepository;
        this.tagInfosMapper = tagInfosMapper;
    }

    /**
     * Save a tagInfos.
     *
     * @param tagInfosDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<TagInfosDTO> save(TagInfosDTO tagInfosDTO) {
        log.debug("Request to save TagInfos : {}", tagInfosDTO);
        return tagInfosRepository.save(tagInfosMapper.toEntity(tagInfosDTO)).map(tagInfosMapper::toDto);
    }

    /**
     * Update a tagInfos.
     *
     * @param tagInfosDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<TagInfosDTO> update(TagInfosDTO tagInfosDTO) {
        log.debug("Request to update TagInfos : {}", tagInfosDTO);
        return tagInfosRepository.save(tagInfosMapper.toEntity(tagInfosDTO)).map(tagInfosMapper::toDto);
    }

    /**
     * Partially update a tagInfos.
     *
     * @param tagInfosDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<TagInfosDTO> partialUpdate(TagInfosDTO tagInfosDTO) {
        log.debug("Request to partially update TagInfos : {}", tagInfosDTO);

        return tagInfosRepository
            .findById(tagInfosDTO.getId())
            .map(existingTagInfos -> {
                tagInfosMapper.partialUpdate(existingTagInfos, tagInfosDTO);

                return existingTagInfos;
            })
            .flatMap(tagInfosRepository::save)
            .map(tagInfosMapper::toDto);
    }

    /**
     * Get all the tagInfos.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<TagInfosDTO> findAll() {
        log.debug("Request to get all TagInfos");
        return tagInfosRepository.findAll().map(tagInfosMapper::toDto);
    }

    /**
     * Returns the number of tagInfos available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return tagInfosRepository.count();
    }

    /**
     * Get one tagInfos by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<TagInfosDTO> findOne(Long id) {
        log.debug("Request to get TagInfos : {}", id);
        return tagInfosRepository.findById(id).map(tagInfosMapper::toDto);
    }

    /**
     * Delete the tagInfos by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete TagInfos : {}", id);
        return tagInfosRepository.deleteById(id);
    }
}
