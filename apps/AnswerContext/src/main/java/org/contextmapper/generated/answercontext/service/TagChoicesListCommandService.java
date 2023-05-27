package org.contextmapper.generated.answercontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.answercontext.domain.TagChoicesListCommand;
import org.contextmapper.generated.answercontext.repository.TagChoicesListCommandRepository;
import org.contextmapper.generated.answercontext.service.dto.TagChoicesListCommandDTO;
import org.contextmapper.generated.answercontext.service.mapper.TagChoicesListCommandMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TagChoicesListCommand}.
 */
@Service
@Transactional
public class TagChoicesListCommandService {

    private final Logger log = LoggerFactory.getLogger(TagChoicesListCommandService.class);

    private final TagChoicesListCommandRepository tagChoicesListCommandRepository;

    private final TagChoicesListCommandMapper tagChoicesListCommandMapper;

    public TagChoicesListCommandService(
        TagChoicesListCommandRepository tagChoicesListCommandRepository,
        TagChoicesListCommandMapper tagChoicesListCommandMapper
    ) {
        this.tagChoicesListCommandRepository = tagChoicesListCommandRepository;
        this.tagChoicesListCommandMapper = tagChoicesListCommandMapper;
    }

    /**
     * Save a tagChoicesListCommand.
     *
     * @param tagChoicesListCommandDTO the entity to save.
     * @return the persisted entity.
     */
    public TagChoicesListCommandDTO save(TagChoicesListCommandDTO tagChoicesListCommandDTO) {
        log.debug("Request to save TagChoicesListCommand : {}", tagChoicesListCommandDTO);
        TagChoicesListCommand tagChoicesListCommand = tagChoicesListCommandMapper.toEntity(tagChoicesListCommandDTO);
        tagChoicesListCommand = tagChoicesListCommandRepository.save(tagChoicesListCommand);
        return tagChoicesListCommandMapper.toDto(tagChoicesListCommand);
    }

    /**
     * Update a tagChoicesListCommand.
     *
     * @param tagChoicesListCommandDTO the entity to save.
     * @return the persisted entity.
     */
    public TagChoicesListCommandDTO update(TagChoicesListCommandDTO tagChoicesListCommandDTO) {
        log.debug("Request to update TagChoicesListCommand : {}", tagChoicesListCommandDTO);
        TagChoicesListCommand tagChoicesListCommand = tagChoicesListCommandMapper.toEntity(tagChoicesListCommandDTO);
        tagChoicesListCommand = tagChoicesListCommandRepository.save(tagChoicesListCommand);
        return tagChoicesListCommandMapper.toDto(tagChoicesListCommand);
    }

    /**
     * Partially update a tagChoicesListCommand.
     *
     * @param tagChoicesListCommandDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<TagChoicesListCommandDTO> partialUpdate(TagChoicesListCommandDTO tagChoicesListCommandDTO) {
        log.debug("Request to partially update TagChoicesListCommand : {}", tagChoicesListCommandDTO);

        return tagChoicesListCommandRepository
            .findById(tagChoicesListCommandDTO.getId())
            .map(existingTagChoicesListCommand -> {
                tagChoicesListCommandMapper.partialUpdate(existingTagChoicesListCommand, tagChoicesListCommandDTO);

                return existingTagChoicesListCommand;
            })
            .map(tagChoicesListCommandRepository::save)
            .map(tagChoicesListCommandMapper::toDto);
    }

    /**
     * Get all the tagChoicesListCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TagChoicesListCommandDTO> findAll() {
        log.debug("Request to get all TagChoicesListCommands");
        return tagChoicesListCommandRepository
            .findAll()
            .stream()
            .map(tagChoicesListCommandMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one tagChoicesListCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TagChoicesListCommandDTO> findOne(Long id) {
        log.debug("Request to get TagChoicesListCommand : {}", id);
        return tagChoicesListCommandRepository.findById(id).map(tagChoicesListCommandMapper::toDto);
    }

    /**
     * Delete the tagChoicesListCommand by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TagChoicesListCommand : {}", id);
        tagChoicesListCommandRepository.deleteById(id);
    }
}
