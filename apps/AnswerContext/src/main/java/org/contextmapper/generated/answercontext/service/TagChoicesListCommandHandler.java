package org.contextmapper.generated.answercontext.service;

import org.contextmapper.generated.answercontext.repository.TagChoicesListedEventRepository;
import org.contextmapper.generated.answercontext.service.dto.TagChoicesListCommandDTO;
import org.contextmapper.generated.answercontext.service.dto.TagChoicesListedEventDTO;
import org.contextmapper.generated.answercontext.service.mapper.TagChoicesListedEventMapper;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;

@Primary
@Service
@Transactional
public class TagChoicesListCommandHandler extends TagChoicesListedEventService {
    private final Logger log = LoggerFactory.getLogger(TagChoicesListCommandHandler.class);

    private final TagChoicesListedEventService tagChoicesListedEventService;

    private final TagChoicesListedService tagChoicesListedService;

    private final AvailableAnswerService availableAnswerService;

    public TagChoicesListCommandHandler(
        TagChoicesListedEventRepository tagChoicesListEventRepository,
        TagChoicesListedEventService tagChoicesListedEventService,
        TagChoicesListedEventMapper tagChoicesListedEventMapper,
        TagChoicesListedService tagChoicesListedService,
        AvailableAnswerService availableAnswerService
    ) {
        super(tagChoicesListEventRepository, tagChoicesListedEventMapper);
        this.tagChoicesListedEventService = tagChoicesListedEventService;
        this.tagChoicesListedService = tagChoicesListedService;
        this.availableAnswerService = availableAnswerService;
    }

    public TagChoicesListedEventDTO handleTagChoicesListCommand(TagChoicesListCommandDTO cmd) {
        log.info("Handle command to list tag choices");


        final var tagChoicesListedEventDTO = new TagChoicesListedEventDTO();
        // Api send question fetch question choices
        // cmd.getQuestionSent().getQuestionId();
        // availableAnswerService.save();

        return save(tagChoicesListedEventService.save(tagChoicesListedEventDTO));
    }
}
