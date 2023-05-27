package org.contextmapper.generated.usermanagementcontext.service.mapper;

import org.contextmapper.generated.usermanagementcontext.domain.RegisterCommand;
import org.contextmapper.generated.usermanagementcontext.service.dto.RegisterCommandDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link RegisterCommand} and its DTO {@link RegisterCommandDTO}.
 */
@Mapper(componentModel = "spring")
public interface RegisterCommandMapper extends EntityMapper<RegisterCommandDTO, RegisterCommand> {}
