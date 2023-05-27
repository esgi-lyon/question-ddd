package org.contextmapper.generated.sendquestioncontext.service;

import org.contextmapper.generated.sendquestioncontext.client.skillcontext.api.TagResourceApiClient;
import org.contextmapper.generated.sendquestioncontext.client.usermanagementcontext.api.UserInfosResourceApi;
import org.contextmapper.generated.sendquestioncontext.repository.AddPreferencesCommandRepository;
import org.contextmapper.generated.sendquestioncontext.service.dto.*;
import org.contextmapper.generated.sendquestioncontext.service.mapper.AddPreferencesCommandMapper;
import org.contextmapper.generated.sendquestioncontext.service.mapper.UserPreferencesMapper;
import org.contextmapper.generated.sendquestioncontext.service.mapper.UserPreferencesTagInfosMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Primary
@Service
@Transactional
public class UserPreferencesCommandHandler extends AddPreferencesCommandService {
    private final Logger log = LoggerFactory.getLogger(UserPreferencesCommandHandler.class);

    private final UserInfosResourceApi userInfosResourceApi;

    private final UserPreferencesService userPreferencesService;

    private final PreferencesAddedEventService preferencesAddedEventService;

    private final TagResourceApiClient tagResourceApi;
    private final UserPreferencesTagInfosService userPreferencesTagInfosService;

    public UserPreferencesCommandHandler(
        AddPreferencesCommandRepository addPreferencesCommandRepository,
        UserPreferencesService userPreferencesService,
        PreferencesAddedEventService preferencesAddedEventService,
        TagResourceApiClient tagResourceApi,
        UserInfosResourceApi userInfosResourceApi,
        UserPreferencesTagInfosService userPreferencesTagInfosService,
        AddPreferencesCommandMapper addPreferencesCommandMapper
    ) {
        super(addPreferencesCommandRepository, addPreferencesCommandMapper);
        this.userPreferencesService = userPreferencesService;
        this.preferencesAddedEventService = preferencesAddedEventService;
        this.tagResourceApi = tagResourceApi;
        this.userInfosResourceApi = userInfosResourceApi;
        this.userPreferencesTagInfosService = userPreferencesTagInfosService;
    }

    public AddPreferencesCommandDTO handleAddPreferencesCommand(AddPreferencesCommandDTO addPreferencesCommand) {
        log.info("Handle command to add preferences");

        final var tagFromApi =
            Optional.ofNullable(
            tagResourceApi.getTag(addPreferencesCommand.getPreferences().getTagId()).getBody()
        ).orElseThrow();

        final var userId = SecurityContextHolder.getContext().getAuthentication().getName();
        userInfosResourceApi.getUserInfos(Long.parseLong(userId));

        final var userPreferences = new UserPreferencesDTO();
        userPreferences.setUser(new UserWithPreferencesIdDTO());
        final var savedUserPrefs = userPreferencesService.save(userPreferences);

        final var tagInfosDto = new UserPreferencesTagInfosDTO();
        tagInfosDto.setTagId(tagFromApi.getId());
        tagInfosDto.setUserPreferences(savedUserPrefs);

        final var savedTagInfos = userPreferencesTagInfosService.save(tagInfosDto);

        final var savedPrefs = userPreferencesService.save(userPreferences);

        addPreferencesCommand.setPreferences(savedTagInfos);

        final var saved = userPreferencesService.save(savedPrefs);

        final var preferencesAddedEventDTO = new PreferencesAddedEventDTO();
        preferencesAddedEventDTO.setPreferences(saved);
        preferencesAddedEventService.save(preferencesAddedEventDTO);

        return save(addPreferencesCommand);
    }
}
