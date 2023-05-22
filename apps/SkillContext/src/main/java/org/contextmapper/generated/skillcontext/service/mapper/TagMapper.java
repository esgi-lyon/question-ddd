package org.contextmapper.generated.skillcontext.service.mapper;

import org.contextmapper.generated.skillcontext.domain.Category;
import org.contextmapper.generated.skillcontext.domain.CreatedById;
import org.contextmapper.generated.skillcontext.domain.Tag;
import org.contextmapper.generated.skillcontext.service.dto.CategoryDTO;
import org.contextmapper.generated.skillcontext.service.dto.CreatedByIdDTO;
import org.contextmapper.generated.skillcontext.service.dto.TagDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Tag} and its DTO {@link TagDTO}.
 */
@Mapper(componentModel = "spring")
public interface TagMapper extends EntityMapper<TagDTO, Tag> {
    @Mapping(target = "createdById", source = "createdById", qualifiedByName = "createdByIdId")
    @Mapping(target = "category", source = "category", qualifiedByName = "categoryId")
    TagDTO toDto(Tag s);

    @Named("createdByIdId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CreatedByIdDTO toDtoCreatedByIdId(CreatedById createdById);

    @Named("categoryId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CategoryDTO toDtoCategoryId(Category category);
}
