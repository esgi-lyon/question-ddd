package org.contextmapper.generated.statcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.statcontext.domain.TagStatsViewed;
import org.contextmapper.generated.statcontext.repository.TagStatsViewedRepository;
import org.contextmapper.generated.statcontext.service.dto.TagStatsViewedDTO;
import org.contextmapper.generated.statcontext.service.mapper.TagStatsViewedMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TagStatsViewed}.
 */
@Service
@Transactional
public class TagStatsViewedService {

    private final Logger log = LoggerFactory.getLogger(TagStatsViewedService.class);

    private final TagStatsViewedRepository tagStatsViewedRepository;

    private final TagStatsViewedMapper tagStatsViewedMapper;

    public TagStatsViewedService(TagStatsViewedRepository tagStatsViewedRepository, TagStatsViewedMapper tagStatsViewedMapper) {
        this.tagStatsViewedRepository = tagStatsViewedRepository;
        this.tagStatsViewedMapper = tagStatsViewedMapper;
    }

    /**
     * Save a tagStatsViewed.
     *
     * @param tagStatsViewedDTO the entity to save.
     * @return the persisted entity.
     */
    public TagStatsViewedDTO save(TagStatsViewedDTO tagStatsViewedDTO) {
        log.debug("Request to save TagStatsViewed : {}", tagStatsViewedDTO);
        TagStatsViewed tagStatsViewed = tagStatsViewedMapper.toEntity(tagStatsViewedDTO);
        tagStatsViewed = tagStatsViewedRepository.save(tagStatsViewed);
        return tagStatsViewedMapper.toDto(tagStatsViewed);
    }

    /**
     * Update a tagStatsViewed.
     *
     * @param tagStatsViewedDTO the entity to save.
     * @return the persisted entity.
     */
    public TagStatsViewedDTO update(TagStatsViewedDTO tagStatsViewedDTO) {
        log.debug("Request to update TagStatsViewed : {}", tagStatsViewedDTO);
        TagStatsViewed tagStatsViewed = tagStatsViewedMapper.toEntity(tagStatsViewedDTO);
        tagStatsViewed = tagStatsViewedRepository.save(tagStatsViewed);
        return tagStatsViewedMapper.toDto(tagStatsViewed);
    }

    /**
     * Partially update a tagStatsViewed.
     *
     * @param tagStatsViewedDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<TagStatsViewedDTO> partialUpdate(TagStatsViewedDTO tagStatsViewedDTO) {
        log.debug("Request to partially update TagStatsViewed : {}", tagStatsViewedDTO);

        return tagStatsViewedRepository
            .findById(tagStatsViewedDTO.getId())
            .map(existingTagStatsViewed -> {
                tagStatsViewedMapper.partialUpdate(existingTagStatsViewed, tagStatsViewedDTO);

                return existingTagStatsViewed;
            })
            .map(tagStatsViewedRepository::save)
            .map(tagStatsViewedMapper::toDto);
    }

    /**
     * Get all the tagStatsVieweds.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TagStatsViewedDTO> findAll() {
        log.debug("Request to get all TagStatsVieweds");
        return tagStatsViewedRepository
            .findAll()
            .stream()
            .map(tagStatsViewedMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one tagStatsViewed by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TagStatsViewedDTO> findOne(Long id) {
        log.debug("Request to get TagStatsViewed : {}", id);
        return tagStatsViewedRepository.findById(id).map(tagStatsViewedMapper::toDto);
    }

    /**
     * Delete the tagStatsViewed by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TagStatsViewed : {}", id);
        tagStatsViewedRepository.deleteById(id);
    }
}
