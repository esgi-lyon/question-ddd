package org.contextmapper.generated.questioncontext.service;

import org.contextmapper.generated.questioncontext.client.skillcontext.api.TagResourceApiClient;
import org.contextmapper.generated.questioncontext.domain.ResourceAcceptedAssociationEvent;
import org.contextmapper.generated.questioncontext.domain.ResourceRefusedAssociationEvent;
import org.contextmapper.generated.questioncontext.domain.ValidateResourceTagLinkageCommand;
import org.contextmapper.generated.questioncontext.domain.enumeration.States;
import org.contextmapper.generated.questioncontext.repository.ValidateResourceTagLinkageCommandRepository;
import org.contextmapper.generated.questioncontext.service.dto.ResourceAcceptedAssociationEventDTO;
import org.contextmapper.generated.questioncontext.service.dto.ResourceRefusedAssociationEventDTO;
import org.contextmapper.generated.questioncontext.service.mapper.QuestionResourceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Primary
@Service
@Transactional
public class ValidateResourceTagLinkageCommandHandler extends ValidateResourceTagLinkageCommandService {
    private final Logger log = LoggerFactory.getLogger(ValidateResourceTagLinkageCommandHandler.class);

    private final QuestionResourceService questionResourceService;

    private final ResourceWaitingForAssociationEventService resourceWaitingForAssociationEventService;

    private final QuestionResourceMapper questionResourceMapper;

    private final QuestionResourceTagInfosService questionResourceTagInfosService;

    private final TagResourceApiClient tagResourceApi;

    private final ResourceAcceptedAssociationEventService resourceAcceptedAssociationEventService;
    private final ResourceRefusedAssociationEventService resourceRefusedAssociationEventService;

    public ValidateResourceTagLinkageCommandHandler(
        ValidateResourceTagLinkageCommandRepository repository,
        QuestionResourceService questionResourceService,
        ResourceWaitingForAssociationEventService resourceWaitingForAssociationEventService,
        QuestionResourceMapper questionResourceMapper,
        QuestionResourceTagInfosService questionResourceTagInfosService,
        TagResourceApiClient tagResourceApi,
        ResourceAcceptedAssociationEventService resourceAcceptedAssociationEventService,
        ResourceRefusedAssociationEventService resourceRefusedAssociationEventService
    ) {
        super(repository);
        this.questionResourceService = questionResourceService;
        this.questionResourceMapper = questionResourceMapper;
        this.resourceWaitingForAssociationEventService = resourceWaitingForAssociationEventService;
        this.questionResourceTagInfosService = questionResourceTagInfosService;
        this.tagResourceApi = tagResourceApi;
        this.resourceAcceptedAssociationEventService = resourceAcceptedAssociationEventService;
        this.resourceRefusedAssociationEventService = resourceRefusedAssociationEventService;
    }

    public ValidateResourceTagLinkageCommand handle(
        ValidateResourceTagLinkageCommand validateResourceTagLinkageCommand
    ) {
        log.info("Handle command to validate resource tag linkage");

        final var stateToSet = validateResourceTagLinkageCommand.getQuestionId().getQuestionState();
        final var questionResource = questionResourceService.findOne(
            validateResourceTagLinkageCommand.getQuestionId().getId()
        )
            .map(resource -> {
                resource.setQuestionState(stateToSet);
                return questionResourceService.save(resource);
            })
            .orElseThrow(() -> new RuntimeException("QuestionResource not found"));

        validateResourceTagLinkageCommand
            .setQuestionId(questionResourceMapper.toEntity(questionResource));

        if (questionResource.getQuestionState() == States.REFUSED) {
            final var event = new ResourceRefusedAssociationEventDTO();
            event.setQuestionId(questionResource);
            resourceRefusedAssociationEventService.save(event);
        }

        if (questionResource.getQuestionState() == States.ASSOCIATED) {
            final var event = new ResourceAcceptedAssociationEventDTO();
            event.setQuestionId(questionResource);
            event.setTagInfos(questionResource.getTagInfos());
            resourceAcceptedAssociationEventService.save(event);
        }

        return save(validateResourceTagLinkageCommand);
    }
}
