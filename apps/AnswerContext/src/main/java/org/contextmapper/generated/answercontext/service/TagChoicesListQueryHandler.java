package org.contextmapper.generated.answercontext.service;

import org.contextmapper.generated.answercontext.domain.TagChoicesListCommand;
import org.contextmapper.generated.answercontext.repository.TagChoicesListCommandRepository;
import org.contextmapper.generated.answercontext.service.dto.TagChoicesListedEventDTO;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;

@Primary
@Service
@Transactional
public class TagChoicesListQueryHandler extends TagChoicesListCommandService {
    private final Logger log = LoggerFactory.getLogger(TagChoicesListQueryHandler.class);

    private final TagChoicesListedEventService tagChoicesListedEventService;

    public TagChoicesListQueryHandler(
        TagChoicesListCommandRepository tagChoicesListCommandRepository,
        TagChoicesListedEventService tagChoicesListedEventService
    ) {
        super(tagChoicesListCommandRepository);
        this.tagChoicesListedEventService = tagChoicesListedEventService;
    }

    public TagChoicesListCommand handleTagChoicesListCommand() {
        log.info("Handle command to list tag choices");
        TagChoicesListCommand tagChoicesListCommand = new TagChoicesListCommand();

        final var tagChoicesListedEventDTO = new TagChoicesListedEventDTO();
        tagChoicesListedEventService.save(tagChoicesListedEventDTO);

        return save(tagChoicesListCommand);
    }
}
