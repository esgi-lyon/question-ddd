package org.contextmapper.generated.questioncontext.service;

import java.util.List;
import java.util.Optional;
import org.contextmapper.generated.questioncontext.domain.CreateResourceCommand;
import org.contextmapper.generated.questioncontext.domain.enumeration.States;
import org.contextmapper.generated.questioncontext.repository.CreateResourceCommandRepository;
import org.contextmapper.generated.questioncontext.service.dto.QuestionResourceDTO;
import org.contextmapper.generated.questioncontext.service.dto.ResourceWaitingForAssociationEventDTO;
import org.contextmapper.generated.questioncontext.service.mapper.QuestionResourceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link CreateResourceCommand}.
 */
@Service
@Transactional
public class CreateResourceCommandService {

    private final Logger log = LoggerFactory.getLogger(CreateResourceCommandService.class);

    private final CreateResourceCommandRepository createResourceCommandRepository;

    private final QuestionResourceService questionResourceService;

    private final ResourceWaitingForAssociationEventService resourceWaitingForAssociationEventService;

    private final QuestionResourceMapper questionResourceMapper;

    public CreateResourceCommandService(
        CreateResourceCommandRepository createResourceCommandRepository,
        QuestionResourceService questionResourceService,
        ResourceWaitingForAssociationEventService resourceWaitingForAssociationEventService,
        QuestionResourceMapper questionResourceMapper

    ) {
        this.createResourceCommandRepository = createResourceCommandRepository;
        this.questionResourceService = questionResourceService;
        this.resourceWaitingForAssociationEventService = resourceWaitingForAssociationEventService;
        this.questionResourceMapper = questionResourceMapper;
    }

    public CreateResourceCommand handle(QuestionResourceDTO questionResourceDTO) {
        log.debug("Request to create resource");
        CreateResourceCommand createResourceCommand = new CreateResourceCommand();
        questionResourceDTO.setQuestionState(States.WAITING);
        final var saved = questionResourceService.save(questionResourceDTO);

        final var resourceWaitingForAssociationEventDTO = new ResourceWaitingForAssociationEventDTO();
        questionResourceDTO.setId(saved.getId());
        resourceWaitingForAssociationEventDTO.setQuestionId(questionResourceDTO);
        resourceWaitingForAssociationEventService.save(resourceWaitingForAssociationEventDTO);
        return createResourceCommandRepository.save(
            createResourceCommand.questionId(questionResourceMapper.toEntity(saved))
        );
    }

    /**
     * Save a createResourceCommand.
     *
     * @param createResourceCommand the entity to save.
     * @return the persisted entity.
     */
    public CreateResourceCommand save(CreateResourceCommand createResourceCommand) {
        log.debug("Request to save CreateResourceCommand : {}", createResourceCommand);
        return createResourceCommandRepository.save(createResourceCommand);
    }

    /**
     * Update a createResourceCommand.
     *
     * @param createResourceCommand the entity to save.
     * @return the persisted entity.
     */
    public CreateResourceCommand update(CreateResourceCommand createResourceCommand) {
        log.debug("Request to update CreateResourceCommand : {}", createResourceCommand);
        return createResourceCommandRepository.save(createResourceCommand);
    }

    /**
     * Partially update a createResourceCommand.
     *
     * @param createResourceCommand the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CreateResourceCommand> partialUpdate(CreateResourceCommand createResourceCommand) {
        log.debug("Request to partially update CreateResourceCommand : {}", createResourceCommand);

        return createResourceCommandRepository
            .findById(createResourceCommand.getId())
            .map(existingCreateResourceCommand -> {
                return existingCreateResourceCommand;
            })
            .map(createResourceCommandRepository::save);
    }

    /**
     * Get all the createResourceCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CreateResourceCommand> findAll() {
        log.debug("Request to get all CreateResourceCommands");
        return createResourceCommandRepository.findAll();
    }

    /**
     * Get one createResourceCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CreateResourceCommand> findOne(Long id) {
        log.debug("Request to get CreateResourceCommand : {}", id);
        return createResourceCommandRepository.findById(id);
    }

    /**
     * Delete the createResourceCommand by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CreateResourceCommand : {}", id);
        createResourceCommandRepository.deleteById(id);
    }
}
