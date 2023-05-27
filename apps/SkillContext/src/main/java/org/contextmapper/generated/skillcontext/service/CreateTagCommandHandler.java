package org.contextmapper.generated.skillcontext.service;

import org.contextmapper.generated.skillcontext.domain.CreateTagCommand;
import org.contextmapper.generated.skillcontext.repository.CreateTagCommandRepository;
import org.contextmapper.generated.skillcontext.service.dto.CreateTagCommandDTO;
import org.contextmapper.generated.skillcontext.service.dto.TagCreatedEventDTO;
import org.contextmapper.generated.skillcontext.service.dto.TagDTO;
import org.contextmapper.generated.skillcontext.service.mapper.CreateTagCommandMapper;
import org.contextmapper.generated.skillcontext.service.mapper.TagMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link CreateTagCommand}.
 */
@Service
@Transactional
public class CreateTagCommandHandler extends CreateTagCommandService {

    private final Logger log = LoggerFactory.getLogger(CreateTagCommandHandler.class);

    private final TagService tagService;

    private final TagCreatedEventService tagCreatedEventService;

    private final CategoryService categoryService;

    private final TagMapper tagMapper;

    public CreateTagCommandHandler(
        CreateTagCommandRepository createTagCommandRepository,
        TagService tagService,
        TagCreatedEventService tagCreatedEventService,
        CategoryService categoryService,
        TagMapper tagMapper,
        CreateTagCommandMapper createTagCommandMapper
    ) {
        super(createTagCommandRepository, createTagCommandMapper);
        this.tagService = tagService;
        this.tagCreatedEventService = tagCreatedEventService;
        this.categoryService = categoryService;
        this.tagMapper = tagMapper;
    }

    public CreateTagCommandDTO handle(TagDTO createTag) {
        final var cmd = new CreateTagCommandDTO();
        final var tagDto = new TagDTO();

        tagDto.setName(createTag.getName());
        tagDto.setCategory(categoryService.findOne(createTag.getCategory().getId())
            .orElseThrow(
                () -> new RuntimeException("Category not found")
            )
        );

        final var saved = tagService.save(tagDto);

        final var tagCreatedEvent = new TagCreatedEventDTO();
        tagCreatedEvent.setTagId(saved);

        tagCreatedEventService.save(tagCreatedEvent);

        cmd.setTag(saved);

        return save(cmd);
    }
}
