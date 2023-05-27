package org.contextmapper.generated.answercontext.service.mapper;

import org.contextmapper.generated.answercontext.domain.UserEmail;
import org.contextmapper.generated.answercontext.service.dto.UserEmailDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserEmail} and its DTO {@link UserEmailDTO}.
 */
@Mapper(componentModel = "spring")
public interface UserEmailMapper extends EntityMapper<UserEmailDTO, UserEmail> {}
