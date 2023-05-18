package org.contextmapper.generated.answercontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.answercontext.domain.TagChoicesListed;
import org.contextmapper.generated.answercontext.repository.TagChoicesListedRepository;
import org.contextmapper.generated.answercontext.service.dto.TagChoicesListedDTO;
import org.contextmapper.generated.answercontext.service.mapper.TagChoicesListedMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TagChoicesListed}.
 */
@Service
@Transactional
public class TagChoicesListedService {

    private final Logger log = LoggerFactory.getLogger(TagChoicesListedService.class);

    private final TagChoicesListedRepository tagChoicesListedRepository;

    private final TagChoicesListedMapper tagChoicesListedMapper;

    public TagChoicesListedService(TagChoicesListedRepository tagChoicesListedRepository, TagChoicesListedMapper tagChoicesListedMapper) {
        this.tagChoicesListedRepository = tagChoicesListedRepository;
        this.tagChoicesListedMapper = tagChoicesListedMapper;
    }

    /**
     * Save a tagChoicesListed.
     *
     * @param tagChoicesListedDTO the entity to save.
     * @return the persisted entity.
     */
    public TagChoicesListedDTO save(TagChoicesListedDTO tagChoicesListedDTO) {
        log.debug("Request to save TagChoicesListed : {}", tagChoicesListedDTO);
        TagChoicesListed tagChoicesListed = tagChoicesListedMapper.toEntity(tagChoicesListedDTO);
        tagChoicesListed = tagChoicesListedRepository.save(tagChoicesListed);
        return tagChoicesListedMapper.toDto(tagChoicesListed);
    }

    /**
     * Update a tagChoicesListed.
     *
     * @param tagChoicesListedDTO the entity to save.
     * @return the persisted entity.
     */
    public TagChoicesListedDTO update(TagChoicesListedDTO tagChoicesListedDTO) {
        log.debug("Request to update TagChoicesListed : {}", tagChoicesListedDTO);
        TagChoicesListed tagChoicesListed = tagChoicesListedMapper.toEntity(tagChoicesListedDTO);
        // no save call needed as we have no fields that can be updated
        return tagChoicesListedMapper.toDto(tagChoicesListed);
    }

    /**
     * Partially update a tagChoicesListed.
     *
     * @param tagChoicesListedDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<TagChoicesListedDTO> partialUpdate(TagChoicesListedDTO tagChoicesListedDTO) {
        log.debug("Request to partially update TagChoicesListed : {}", tagChoicesListedDTO);

        return tagChoicesListedRepository
            .findById(tagChoicesListedDTO.getId())
            .map(existingTagChoicesListed -> {
                tagChoicesListedMapper.partialUpdate(existingTagChoicesListed, tagChoicesListedDTO);

                return existingTagChoicesListed;
            })
            // .map(tagChoicesListedRepository::save)
            .map(tagChoicesListedMapper::toDto);
    }

    /**
     * Get all the tagChoicesListeds.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TagChoicesListedDTO> findAll() {
        log.debug("Request to get all TagChoicesListeds");
        return tagChoicesListedRepository
            .findAll()
            .stream()
            .map(tagChoicesListedMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one tagChoicesListed by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TagChoicesListedDTO> findOne(Long id) {
        log.debug("Request to get TagChoicesListed : {}", id);
        return tagChoicesListedRepository.findById(id).map(tagChoicesListedMapper::toDto);
    }

    /**
     * Delete the tagChoicesListed by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TagChoicesListed : {}", id);
        tagChoicesListedRepository.deleteById(id);
    }
}
