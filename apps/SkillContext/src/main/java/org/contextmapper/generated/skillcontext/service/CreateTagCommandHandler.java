package org.contextmapper.generated.skillcontext.service;

import org.contextmapper.generated.skillcontext.domain.CreateTagCommand;
import org.contextmapper.generated.skillcontext.domain.TagCreatedEvent;
import org.contextmapper.generated.skillcontext.domain.TagWithCategory;
import org.contextmapper.generated.skillcontext.repository.CreateTagCommandRepository;
import org.contextmapper.generated.skillcontext.service.dto.TagCreatedEventDTO;
import org.contextmapper.generated.skillcontext.service.dto.TagDTO;
import org.contextmapper.generated.skillcontext.service.dto.TagInfosDTO;
import org.contextmapper.generated.skillcontext.service.dto.TagWithCategoryDTO;
import org.contextmapper.generated.skillcontext.service.mapper.CategoryMapper;
import org.contextmapper.generated.skillcontext.service.mapper.TagMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    private final TagInfosService tagInfosService;

    public CreateTagCommandHandler(
        CreateTagCommandRepository createTagCommandRepository,
        TagService tagService,
        TagCreatedEventService tagCreatedEventService,
        CategoryService categoryService,
        TagInfosService tagInfosService
    ) {
        super(createTagCommandRepository);
        this.tagService = tagService;
        this.tagCreatedEventService = tagCreatedEventService;
        this.categoryService = categoryService;
        this.tagInfosService = tagInfosService;
    }

    public CreateTagCommand handle(TagWithCategoryDTO createTag) {
        log.debug("Create Tag Command handler for : {}", createTag);
        final var cmd = new CreateTagCommand();
        final var tagDto = new TagDTO();

        tagDto.setName(createTag.getName());
        tagDto.setCategory(categoryService.findOne(createTag.getCategory()).orElseThrow(
            () -> new RuntimeException("Category not found")
        ));

        final var saved = tagService.save(tagDto);

        final var tagCreatedEvent = new TagCreatedEventDTO();
        tagCreatedEvent.setTagId(saved);

        tagCreatedEventService.save(tagCreatedEvent);

        return save(cmd.tag(new TagWithCategory()
            .category(saved.getCategory().getId())
            .id(saved.getId()))
        );
    }
}
