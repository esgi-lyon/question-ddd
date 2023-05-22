package org.contextmapper.generated.skillcontext.service.mapper;

import org.contextmapper.generated.skillcontext.domain.CategoryInfos;
import org.contextmapper.generated.skillcontext.service.dto.CategoryInfosDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CategoryInfos} and its DTO {@link CategoryInfosDTO}.
 */
@Mapper(componentModel = "spring")
public interface CategoryInfosMapper extends EntityMapper<CategoryInfosDTO, CategoryInfos> {}
