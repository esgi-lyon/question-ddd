package org.contextmapper.generated.statcontext.service.mapper;

import org.contextmapper.generated.statcontext.domain.QuestionStatsViewed;
import org.contextmapper.generated.statcontext.domain.StatisticSubjectQuestion;
import org.contextmapper.generated.statcontext.service.dto.QuestionStatsViewedDTO;
import org.contextmapper.generated.statcontext.service.dto.StatisticSubjectQuestionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link QuestionStatsViewed} and its DTO {@link QuestionStatsViewedDTO}.
 */
@Mapper(componentModel = "spring")
public interface QuestionStatsViewedMapper extends EntityMapper<QuestionStatsViewedDTO, QuestionStatsViewed> {
    @Mapping(target = "question", source = "question", qualifiedByName = "statisticSubjectQuestionId")
    QuestionStatsViewedDTO toDto(QuestionStatsViewed s);

    @Named("statisticSubjectQuestionId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    StatisticSubjectQuestionDTO toDtoStatisticSubjectQuestionId(StatisticSubjectQuestion statisticSubjectQuestion);
}
