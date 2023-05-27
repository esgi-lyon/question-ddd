package org.contextmapper.generated.sendquestioncontext.service;

import org.contextmapper.generated.sendquestioncontext.client.skillcontext.api.TagResourceApi;
import org.contextmapper.generated.sendquestioncontext.client.usermanagementcontext.api.QueryHandlerResourceApi;
import org.contextmapper.generated.sendquestioncontext.domain.PreferencesAddedEvent;
import org.contextmapper.generated.sendquestioncontext.repository.PreferencesAddedEventRepository;
import org.contextmapper.generated.sendquestioncontext.service.dto.*;
import org.contextmapper.generated.sendquestioncontext.service.mapper.PreferencesAddedEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link PreferencesAddedEvent}.
 */
@Service
@Transactional
public class AddPreferenceCommandHandler extends PreferencesAddedEventService {

    private final Logger log = LoggerFactory.getLogger(AddPreferenceCommandHandler.class);

    private final TagResourceApi tagResourceApi;

    private final UserPreferencesTagInfosService userPreferencesTagInfosService;

    private final UserPreferencesService userPreferencesService;

    private final UserWithPreferencesIdService userWithPreferencesIdService;

    private final QueryHandlerResourceApi queryHandlerResourceApi;

    public AddPreferenceCommandHandler(
        PreferencesAddedEventRepository preferencesAddedEventRepository,
        PreferencesAddedEventMapper preferencesAddedEventMapper,
        TagResourceApi tagResourceApi,
        UserPreferencesTagInfosService userPreferencesTagInfosService,
        UserPreferencesService userPreferencesService,
        QueryHandlerResourceApi queryHandlerResourceApi,
        UserWithPreferencesIdService userWithPreferencesIdService
    ) {
        super(preferencesAddedEventRepository, preferencesAddedEventMapper);
        this.tagResourceApi = tagResourceApi;
        this.userPreferencesTagInfosService = userPreferencesTagInfosService;
        this.userPreferencesService = userPreferencesService;
        this.queryHandlerResourceApi = queryHandlerResourceApi;
        this.userWithPreferencesIdService = userWithPreferencesIdService;
    }

    public PreferencesAddedEventDTO handle(AddPreferencesCommandDTO addPreferencesCommandDTO) {
        log.debug("Request to save PreferencesAddedEvent : {}", addPreferencesCommandDTO);
        log.info("Handle command to add preferences");

        final var tagFromApi = Optional.ofNullable(
            tagResourceApi.getTag(addPreferencesCommandDTO.getPreferences().getTagId()).getBody()
        ).orElseThrow();

        final var userWithPreferencesDTO = new UserWithPreferencesIdDTO();
        final var userFromApi = Optional.ofNullable(queryHandlerResourceApi.handleViewUserByMail(
            SecurityContextHolder.getContext().getAuthentication().getName()
        ).getBody()).orElseThrow();

        userWithPreferencesDTO.setMail(userFromApi.getMail());

        final var userPreferencesDTO = new UserPreferencesDTO();
        final var userIdDTO = new UserWithPreferencesIdDTO();
        userIdDTO.setMail(userFromApi.getMail());
        userIdDTO.setId(userFromApi.getId());

        final var savedUserIdDto = userWithPreferencesIdService.save(userIdDTO);
        userPreferencesDTO.setUser(savedUserIdDto);
        final var saved = userPreferencesService.save(userPreferencesDTO);

        final var tagInfosDto = new UserPreferencesTagInfosDTO();
        tagInfosDto.setTagId(tagFromApi.getId());
        tagInfosDto.setUserPreferences(saved);

        userPreferencesTagInfosService.save(tagInfosDto);

        final var preferencesAddedEventDTO = new PreferencesAddedEventDTO();
        preferencesAddedEventDTO.setPreferences(saved);

        return save(preferencesAddedEventDTO);
    }

}
