package org.contextmapper.generated.skillcontext.service;

import org.contextmapper.generated.skillcontext.domain.CategoryInfos;
import org.contextmapper.generated.skillcontext.domain.CreateCategoryCommand;
import org.contextmapper.generated.skillcontext.repository.CategoryIdRepository;
import org.contextmapper.generated.skillcontext.repository.CreateCategoryCommandRepository;
import org.contextmapper.generated.skillcontext.service.dto.CategoryCreatedEventDTO;
import org.contextmapper.generated.skillcontext.service.dto.CategoryDTO;
import org.contextmapper.generated.skillcontext.service.dto.CategoryIdDTO;
import org.contextmapper.generated.skillcontext.service.dto.CategoryInfosDTO;
import org.contextmapper.generated.skillcontext.service.mapper.CategoryInfosMapper;
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

    private final CategoryInfosMapper categoryInfosMapper;

    public CreateCategoryCommandHandler(
        CreateCategoryCommandRepository repo,
        CategoryCreatedEventService eventService,
        CategoryService categoryService,
        CategoryInfosMapper categoryInfosMapper
    ) {
        super(repo);
        this.eventService = eventService;
        this.categoryService = categoryService;
        this.categoryInfosMapper = categoryInfosMapper;
    }

    public CreateCategoryCommand handle(CategoryInfosDTO createCategory) {
        log.debug("Create Tag Command handler for : {}", createCategory);
        final var cmd = new CreateCategoryCommand();
        final var categoryDto = new CategoryDTO();
        categoryDto.setDescription(createCategory.getDescription());
        categoryDto.setName(createCategory.getName());

        final var saved = categoryService.save(categoryDto);

        final var createdEvent = new CategoryCreatedEventDTO();
        createdEvent.setCategoryId(saved);

        eventService.save(createdEvent);

        createCategory.setId(saved.getId());

        return save(cmd.category(categoryInfosMapper.toEntity(createCategory)));
    }
}
