package org.contextmapper.generated.skillcontext.service;

import org.contextmapper.generated.skillcontext.domain.CreateCategoryCommand;
import org.contextmapper.generated.skillcontext.repository.CreateCategoryCommandRepository;
import org.contextmapper.generated.skillcontext.service.dto.CategoryCreatedEventDTO;
import org.contextmapper.generated.skillcontext.service.dto.CategoryDTO;
import org.contextmapper.generated.skillcontext.service.dto.CreateCategoryCommandDTO;
import org.contextmapper.generated.skillcontext.service.mapper.CategoryMapper;
import org.contextmapper.generated.skillcontext.service.mapper.CreateCategoryCommandMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link CreateCategoryCommand}.
 */
@Service
@Transactional
@Primary
public class CreateCategoryCommandHandler extends CreateCategoryCommandService {

    private final Logger log = LoggerFactory.getLogger(CreateCategoryCommandHandler.class);

    private final CategoryCreatedEventService eventService;

    private final CategoryService categoryService;

    private final CategoryMapper categoryMapper;

    public CreateCategoryCommandHandler(
        CreateCategoryCommandRepository repo,
        CategoryCreatedEventService eventService,
        CategoryService categoryService,
        CategoryMapper categoryMapper,
        CreateCategoryCommandMapper createCategoryCommandMapper
    ) {
        super(repo, createCategoryCommandMapper);
        this.eventService = eventService;
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    public CreateCategoryCommandDTO handle(CategoryDTO createCategory) {
        log.debug("Create Tag Command handler for : {}", createCategory);
        final var cmd = new CreateCategoryCommandDTO();

        final var saved = categoryService.save(createCategory);

        final var createdEvent = new CategoryCreatedEventDTO();
        createdEvent.setCategoryId(saved);

        eventService.save(createdEvent);

        createCategory.setId(saved.getId());
        cmd.setCategory(saved);

        return save(cmd);
    }
}
