package org.contextmapper.generated.evaluationcontext.service.mapper;

import org.contextmapper.generated.evaluationcontext.domain.UserAndLevel;
import org.contextmapper.generated.evaluationcontext.service.dto.UserAndLevelDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserAndLevel} and its DTO {@link UserAndLevelDTO}.
 */
@Mapper(componentModel = "spring")
public interface UserAndLevelMapper extends EntityMapper<UserAndLevelDTO, UserAndLevel> {}
