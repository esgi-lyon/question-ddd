package org.contextmapper.generated.answercontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.answercontext.domain.TagChoicesList;
import org.contextmapper.generated.answercontext.repository.TagChoicesListRepository;
import org.contextmapper.generated.answercontext.service.dto.TagChoicesListDTO;
import org.contextmapper.generated.answercontext.service.mapper.TagChoicesListMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TagChoicesList}.
 */
@Service
@Transactional
public class TagChoicesListService {

    private final Logger log = LoggerFactory.getLogger(TagChoicesListService.class);

    private final TagChoicesListRepository tagChoicesListRepository;

    private final TagChoicesListMapper tagChoicesListMapper;

    public TagChoicesListService(TagChoicesListRepository tagChoicesListRepository, TagChoicesListMapper tagChoicesListMapper) {
        this.tagChoicesListRepository = tagChoicesListRepository;
        this.tagChoicesListMapper = tagChoicesListMapper;
    }

    /**
     * Save a tagChoicesList.
     *
     * @param tagChoicesListDTO the entity to save.
     * @return the persisted entity.
     */
    public TagChoicesListDTO save(TagChoicesListDTO tagChoicesListDTO) {
        log.debug("Request to save TagChoicesList : {}", tagChoicesListDTO);
        TagChoicesList tagChoicesList = tagChoicesListMapper.toEntity(tagChoicesListDTO);
        tagChoicesList = tagChoicesListRepository.save(tagChoicesList);
        return tagChoicesListMapper.toDto(tagChoicesList);
    }

    /**
     * Update a tagChoicesList.
     *
     * @param tagChoicesListDTO the entity to save.
     * @return the persisted entity.
     */
    public TagChoicesListDTO update(TagChoicesListDTO tagChoicesListDTO) {
        log.debug("Request to update TagChoicesList : {}", tagChoicesListDTO);
        TagChoicesList tagChoicesList = tagChoicesListMapper.toEntity(tagChoicesListDTO);
        // no save call needed as we have no fields that can be updated
        return tagChoicesListMapper.toDto(tagChoicesList);
    }

    /**
     * Partially update a tagChoicesList.
     *
     * @param tagChoicesListDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<TagChoicesListDTO> partialUpdate(TagChoicesListDTO tagChoicesListDTO) {
        log.debug("Request to partially update TagChoicesList : {}", tagChoicesListDTO);

        return tagChoicesListRepository
            .findById(tagChoicesListDTO.getId())
            .map(existingTagChoicesList -> {
                tagChoicesListMapper.partialUpdate(existingTagChoicesList, tagChoicesListDTO);

                return existingTagChoicesList;
            })
            // .map(tagChoicesListRepository::save)
            .map(tagChoicesListMapper::toDto);
    }

    /**
     * Get all the tagChoicesLists.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TagChoicesListDTO> findAll() {
        log.debug("Request to get all TagChoicesLists");
        return tagChoicesListRepository
            .findAll()
            .stream()
            .map(tagChoicesListMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one tagChoicesList by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TagChoicesListDTO> findOne(Long id) {
        log.debug("Request to get TagChoicesList : {}", id);
        return tagChoicesListRepository.findById(id).map(tagChoicesListMapper::toDto);
    }

    /**
     * Delete the tagChoicesList by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TagChoicesList : {}", id);
        tagChoicesListRepository.deleteById(id);
    }
}
