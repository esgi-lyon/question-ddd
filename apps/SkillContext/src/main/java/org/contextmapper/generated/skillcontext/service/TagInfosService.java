package org.contextmapper.generated.skillcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.skillcontext.domain.TagInfos;
import org.contextmapper.generated.skillcontext.repository.TagInfosRepository;
import org.contextmapper.generated.skillcontext.service.dto.TagInfosDTO;
import org.contextmapper.generated.skillcontext.service.mapper.TagInfosMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public TagInfosDTO save(TagInfosDTO tagInfosDTO) {
        log.debug("Request to save TagInfos : {}", tagInfosDTO);
        TagInfos tagInfos = tagInfosMapper.toEntity(tagInfosDTO);
        tagInfos = tagInfosRepository.save(tagInfos);
        return tagInfosMapper.toDto(tagInfos);
    }

    /**
     * Update a tagInfos.
     *
     * @param tagInfosDTO the entity to save.
     * @return the persisted entity.
     */
    public TagInfosDTO update(TagInfosDTO tagInfosDTO) {
        log.debug("Request to update TagInfos : {}", tagInfosDTO);
        TagInfos tagInfos = tagInfosMapper.toEntity(tagInfosDTO);
        tagInfos = tagInfosRepository.save(tagInfos);
        return tagInfosMapper.toDto(tagInfos);
    }

    /**
     * Partially update a tagInfos.
     *
     * @param tagInfosDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<TagInfosDTO> partialUpdate(TagInfosDTO tagInfosDTO) {
        log.debug("Request to partially update TagInfos : {}", tagInfosDTO);

        return tagInfosRepository
            .findById(tagInfosDTO.getId())
            .map(existingTagInfos -> {
                tagInfosMapper.partialUpdate(existingTagInfos, tagInfosDTO);

                return existingTagInfos;
            })
            .map(tagInfosRepository::save)
            .map(tagInfosMapper::toDto);
    }

    /**
     * Get all the tagInfos.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TagInfosDTO> findAll() {
        log.debug("Request to get all TagInfos");
        return tagInfosRepository.findAll().stream().map(tagInfosMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one tagInfos by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TagInfosDTO> findOne(Long id) {
        log.debug("Request to get TagInfos : {}", id);
        return tagInfosRepository.findById(id).map(tagInfosMapper::toDto);
    }

    /**
     * Delete the tagInfos by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TagInfos : {}", id);
        tagInfosRepository.deleteById(id);
    }
}
