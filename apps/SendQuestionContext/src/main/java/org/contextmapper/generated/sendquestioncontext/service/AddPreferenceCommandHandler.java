package org.contextmapper.generated.sendquestioncontext.service;

import org.contextmapper.generated.sendquestioncontext.client.skillcontext.api.TagResourceApi;
import org.contextmapper.generated.sendquestioncontext.client.usermanagementcontext.api.UserInfosResourceApi;
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

    private final UserInfosResourceApi userInfosResourceApi;

    public AddPreferenceCommandHandler(
        PreferencesAddedEventRepository preferencesAddedEventRepository,
        PreferencesAddedEventMapper preferencesAddedEventMapper,
        TagResourceApi tagResourceApi,
        UserPreferencesTagInfosService userPreferencesTagInfosService,
        UserPreferencesService userPreferencesService,
        UserInfosResourceApi userInfosResourceApi
    ) {
        super(preferencesAddedEventRepository, preferencesAddedEventMapper);
        this.tagResourceApi = tagResourceApi;
        this.userPreferencesTagInfosService = userPreferencesTagInfosService;
        this.userPreferencesService = userPreferencesService;
        this.userInfosResourceApi = userInfosResourceApi;
    }

    public PreferencesAddedEventDTO handle(AddPreferencesCommandDTO addPreferencesCommandDTO) {
        log.debug("Request to save PreferencesAddedEvent : {}", addPreferencesCommandDTO);
        log.info("Handle command to add preferences");


        final var tagFromApi = Optional.ofNullable(
            tagResourceApi.getTag(addPreferencesCommandDTO.getPreferences().getTagId()).getBody()
        ).orElseThrow();

        final var userWithPreferencesDTO = new UserWithPreferencesIdDTO();
        final var userFromApi = Optional.ofNullable(userInfosResourceApi.viewUserByEmail(
            SecurityContextHolder.getContext().getAuthentication().getName()
        ).getBody()).orElseThrow();

        final var userPreferencesDTO = new UserPreferencesDTO();
        final var userIdDTO = new UserWithPreferencesIdDTO();
        userIdDTO.setMail(userFromApi.getMail());
        userIdDTO.setId(userFromApi.getId());
        userPreferencesDTO.setUser(userFromApi.getMail());
        final var saved = userPreferencesService.save(userPreferencesDTO);

        final var tagInfosDto = new UserPreferencesTagInfosDTO();
        tagInfosDto.setTagId(tagFromApi.getId());
        tagInfosDto.setName(tagFromApi.getName());
        tagInfosDto.setUserPreferences(saved);

        userPreferencesTagInfosService.save(tagInfosDto);

        final var preferencesAddedEventDTO = new PreferencesAddedEventDTO();
        preferencesAddedEventDTO.setPreferences(saved);

        return save(preferencesAddedEventDTO);
    }

}
