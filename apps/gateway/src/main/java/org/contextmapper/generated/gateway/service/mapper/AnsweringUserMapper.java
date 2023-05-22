package org.contextmapper.generated.gateway.service.mapper;

import org.contextmapper.generated.gateway.domain.AnsweringUser;
import org.contextmapper.generated.gateway.service.dto.AnsweringUserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AnsweringUser} and its DTO {@link AnsweringUserDTO}.
 */
@Mapper(componentModel = "spring")
public interface AnsweringUserMapper extends EntityMapper<AnsweringUserDTO, AnsweringUser> {}
