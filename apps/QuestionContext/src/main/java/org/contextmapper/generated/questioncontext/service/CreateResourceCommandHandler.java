package org.contextmapper.generated.questioncontext.service;

import org.contextmapper.generated.questioncontext.client.skillcontext.api.TagResourceApiClient;
import org.contextmapper.generated.questioncontext.domain.enumeration.States;
import org.contextmapper.generated.questioncontext.repository.CreateResourceCommandRepository;
import org.contextmapper.generated.questioncontext.service.dto.CreateResourceCommandDTO;
import org.contextmapper.generated.questioncontext.service.dto.QuestionResourceDTO;
import org.contextmapper.generated.questioncontext.service.dto.QuestionResourceTagInfosDTO;
import org.contextmapper.generated.questioncontext.service.dto.ResourceWaitingForAssociationEventDTO;
import org.contextmapper.generated.questioncontext.service.mapper.CreateResourceCommandMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Primary
@Service
@Transactional
public class CreateResourceCommandHandler extends CreateResourceCommandService {
    private final Logger log = LoggerFactory.getLogger(CreateResourceCommandHandler.class);

    private final QuestionResourceService questionResourceService;

    private final ResourceWaitingForAssociationEventService resourceWaitingForAssociationEventService;

    private final QuestionResourceTagInfosService questionResourceTagInfosService;

    private final TagResourceApiClient tagResourceApi;

    public CreateResourceCommandHandler(
        CreateResourceCommandRepository createResourceCommandRepository,
        QuestionResourceService questionResourceService,
        ResourceWaitingForAssociationEventService resourceWaitingForAssociationEventService,
        CreateResourceCommandMapper questionResourceMapper,
        QuestionResourceTagInfosService questionResourceTagInfosService,
        TagResourceApiClient tagResourceApi
    ) {
        super(createResourceCommandRepository, questionResourceMapper);
        this.questionResourceService = questionResourceService;
        this.resourceWaitingForAssociationEventService = resourceWaitingForAssociationEventService;
        this.questionResourceTagInfosService = questionResourceTagInfosService;
        this.tagResourceApi = tagResourceApi;
    }

    public CreateResourceCommandDTO handleCreateResourceCommand(QuestionResourceDTO questionResourceDTO) {
        log.info("Handle command to create resource");
        CreateResourceCommandDTO createResourceCommand = new CreateResourceCommandDTO();

        final var tagFromApi = Optional.ofNullable(
            tagResourceApi.getTag(questionResourceDTO.getTagInfos().getTagId()).getBody()
        ).orElseThrow();

        final var tagInfosDto = new QuestionResourceTagInfosDTO();
        tagInfosDto.setTagId(tagFromApi.getId());
        tagInfosDto.setName(tagFromApi.getName());

        final var tagInfosSaved = questionResourceTagInfosService.save(tagInfosDto);

        questionResourceDTO.setQuestionState(States.WAITING);
        questionResourceDTO.setTagInfos(tagInfosSaved);
        final var saved = questionResourceService.save(questionResourceDTO);

        final var resourceWaitingForAssociationEventDTO = new ResourceWaitingForAssociationEventDTO();
        questionResourceDTO.setId(saved.getId());
        resourceWaitingForAssociationEventDTO.setQuestionId(questionResourceDTO);
        resourceWaitingForAssociationEventService.save(resourceWaitingForAssociationEventDTO);

        createResourceCommand.setQuestionId(saved);

        return save(createResourceCommand);
    }
}
