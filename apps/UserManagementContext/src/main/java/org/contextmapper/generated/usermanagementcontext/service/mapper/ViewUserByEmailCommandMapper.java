package org.contextmapper.generated.usermanagementcontext.service.mapper;

import org.contextmapper.generated.usermanagementcontext.domain.ViewUserByEmailCommand;
import org.contextmapper.generated.usermanagementcontext.service.dto.ViewUserByEmailCommandDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ViewUserByEmailCommand} and its DTO {@link ViewUserByEmailCommandDTO}.
 */
@Mapper(componentModel = "spring")
public interface ViewUserByEmailCommandMapper extends EntityMapper<ViewUserByEmailCommandDTO, ViewUserByEmailCommand> {}
