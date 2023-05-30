package org.contextmapper.generated.statcontext.service.mapper;

import org.contextmapper.generated.statcontext.domain.EvaluationStatEntry;
import org.contextmapper.generated.statcontext.domain.EvaluationStats;
import org.contextmapper.generated.statcontext.domain.StatisticSubjectQuestion;
import org.contextmapper.generated.statcontext.domain.StatisticSubjectUser;
import org.contextmapper.generated.statcontext.service.dto.EvaluationStatEntryDTO;
import org.contextmapper.generated.statcontext.service.dto.EvaluationStatsDTO;
import org.contextmapper.generated.statcontext.service.dto.StatisticSubjectQuestionDTO;
import org.contextmapper.generated.statcontext.service.dto.StatisticSubjectUserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EvaluationStatEntry} and its DTO {@link EvaluationStatEntryDTO}.
 */
@Mapper(componentModel = "spring")
public interface EvaluationStatEntryMapper extends EntityMapper<EvaluationStatEntryDTO, EvaluationStatEntry> {
    @Mapping(target = "user", source = "user", qualifiedByName = "statisticSubjectUserId")
    @Mapping(target = "question", source = "question", qualifiedByName = "statisticSubjectQuestionId")
    @Mapping(target = "evaluationStats", source = "evaluationStats", qualifiedByName = "evaluationStatsId")
    EvaluationStatEntryDTO toDto(EvaluationStatEntry s);

    @Named("statisticSubjectUserId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    StatisticSubjectUserDTO toDtoStatisticSubjectUserId(StatisticSubjectUser statisticSubjectUser);

    @Named("statisticSubjectQuestionId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    StatisticSubjectQuestionDTO toDtoStatisticSubjectQuestionId(StatisticSubjectQuestion statisticSubjectQuestion);

    @Named("evaluationStatsId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EvaluationStatsDTO toDtoEvaluationStatsId(EvaluationStats evaluationStats);
}
