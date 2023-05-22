package org.contextmapper.generated.answercontext.service.mapper;

import org.contextmapper.generated.answercontext.domain.UserId;
import org.contextmapper.generated.answercontext.service.dto.UserIdDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserId} and its DTO {@link UserIdDTO}.
 */
@Mapper(componentModel = "spring")
public interface UserIdMapper extends EntityMapper<UserIdDTO, UserId> {}
