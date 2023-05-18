package org.contextmapper.generated.evaluationcontext.service.mapper;

import org.contextmapper.generated.evaluationcontext.domain.AnsweringUser;
import org.contextmapper.generated.evaluationcontext.service.dto.AnsweringUserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AnsweringUser} and its DTO {@link AnsweringUserDTO}.
 */
@Mapper(componentModel = "spring")
public interface AnsweringUserMapper extends EntityMapper<AnsweringUserDTO, AnsweringUser> {}
