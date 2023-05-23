package org.contextmapper.generated.questioncontext.service;

import org.contextmapper.generated.questioncontext.domain.CreateResourceCommand;
import org.contextmapper.generated.questioncontext.domain.enumeration.States;
import org.contextmapper.generated.questioncontext.repository.CreateResourceCommandRepository;
import org.contextmapper.generated.questioncontext.service.dto.QuestionResourceDTO;
import org.contextmapper.generated.questioncontext.service.dto.ResourceWaitingForAssociationEventDTO;
import org.contextmapper.generated.questioncontext.service.mapper.QuestionResourceMapper;
import org.contextmapper.generated.skillcontext.web.ApiClient;
import org.contextmapper.generated.skillcontext.web.api.TagResourceApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Primary
@Service
@Transactional
public class ResourceCommandHandler extends CreateResourceCommandService {
    private final Logger log = LoggerFactory.getLogger(ResourceCommandHandler.class);

    private final QuestionResourceService questionResourceService;

    private final ResourceWaitingForAssociationEventService resourceWaitingForAssociationEventService;

    private final QuestionResourceMapper questionResourceMapper;

    private final QuestionResourceTagInfosService questionResourceTagInfosService;

    private final TagResourceApi tagResourceApi = new TagResourceApi(new ApiClient());
    ;

    public ResourceCommandHandler(
        CreateResourceCommandRepository createResourceCommandRepository,
        QuestionResourceService questionResourceService,
        ResourceWaitingForAssociationEventService resourceWaitingForAssociationEventService,
        QuestionResourceMapper questionResourceMapper,
        QuestionResourceTagInfosService questionResourceTagInfosService
    ) {
        super(createResourceCommandRepository);
        this.questionResourceService = questionResourceService;
        this.questionResourceMapper = questionResourceMapper;
        this.resourceWaitingForAssociationEventService = resourceWaitingForAssociationEventService;
        this.questionResourceTagInfosService = questionResourceTagInfosService;
    }

    public CreateResourceCommand handle(QuestionResourceDTO questionResourceDTO) {
        log.debug("Request to create resource");
        CreateResourceCommand createResourceCommand = new CreateResourceCommand();

        try {
            final var tagFromApi = tagResourceApi.getTag(questionResourceDTO.getTagId().getId());
            questionResourceDTO.getTagId().setTagId(tagFromApi.getId());
            final var tagInfosSaved = questionResourceTagInfosService.save(questionResourceDTO.getTagId());

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
        } catch (Exception e) {
            throw new RuntimeException("Tag not found", e);
        }

    }
}
