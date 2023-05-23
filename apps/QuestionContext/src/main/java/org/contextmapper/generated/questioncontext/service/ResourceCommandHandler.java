package org.contextmapper.generated.questioncontext.service;

import org.contextmapper.generated.questioncontext.client.skillcontext.api.TagResourceApiClient;
import org.contextmapper.generated.questioncontext.client.skillcontext.model.TagDTO;
import org.contextmapper.generated.questioncontext.client.skillcontext.model.TagInfosDTO;
import org.contextmapper.generated.questioncontext.domain.CreateResourceCommand;
import org.contextmapper.generated.questioncontext.domain.enumeration.States;
import org.contextmapper.generated.questioncontext.repository.CreateResourceCommandRepository;
import org.contextmapper.generated.questioncontext.service.dto.QuestionResourceDTO;
import org.contextmapper.generated.questioncontext.service.dto.QuestionResourceTagInfosDTO;
import org.contextmapper.generated.questioncontext.service.dto.ResourceWaitingForAssociationEventDTO;
import org.contextmapper.generated.questioncontext.service.mapper.QuestionResourceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Primary
@Service
@Transactional
public class ResourceCommandHandler extends CreateResourceCommandService {
    private final Logger log = LoggerFactory.getLogger(ResourceCommandHandler.class);

    private final QuestionResourceService questionResourceService;

    private final ResourceWaitingForAssociationEventService resourceWaitingForAssociationEventService;

    private final QuestionResourceMapper questionResourceMapper;

    private final QuestionResourceTagInfosService questionResourceTagInfosService;

    private final TagResourceApiClient tagResourceApi;

    public ResourceCommandHandler(
        CreateResourceCommandRepository createResourceCommandRepository,
        QuestionResourceService questionResourceService,
        ResourceWaitingForAssociationEventService resourceWaitingForAssociationEventService,
        QuestionResourceMapper questionResourceMapper,
        QuestionResourceTagInfosService questionResourceTagInfosService,
        TagResourceApiClient tagResourceApi
    ) {
        super(createResourceCommandRepository);
        this.questionResourceService = questionResourceService;
        this.questionResourceMapper = questionResourceMapper;
        this.resourceWaitingForAssociationEventService = resourceWaitingForAssociationEventService;
        this.questionResourceTagInfosService = questionResourceTagInfosService;
        this.tagResourceApi = tagResourceApi;
    }

    public CreateResourceCommand handle(QuestionResourceDTO questionResourceDTO) {
        log.debug("Request to create resource");
        CreateResourceCommand createResourceCommand = new CreateResourceCommand();

        final var tagFromApi = Optional.ofNullable(
            tagResourceApi.getTag(questionResourceDTO.getTagId().getTagId()).getBody()
        ).orElseThrow();

        final var tagInfosDto = new QuestionResourceTagInfosDTO();
        tagInfosDto.setTagId(tagFromApi.getId());
        tagInfosDto.setName(tagFromApi.getName());

        final var tagInfosSaved = questionResourceTagInfosService.save(tagInfosDto);

        questionResourceDTO.setQuestionState(States.WAITING);
        questionResourceDTO.setTagId(tagInfosSaved);
        final var saved = questionResourceService.save(questionResourceDTO);

        final var resourceWaitingForAssociationEventDTO = new ResourceWaitingForAssociationEventDTO();
        questionResourceDTO.setId(saved.getId());
        resourceWaitingForAssociationEventDTO.setQuestionId(questionResourceDTO);
        resourceWaitingForAssociationEventService.save(resourceWaitingForAssociationEventDTO);

        return save(
            createResourceCommand.questionId(questionResourceMapper.toEntity(saved))
        );
    }
}
