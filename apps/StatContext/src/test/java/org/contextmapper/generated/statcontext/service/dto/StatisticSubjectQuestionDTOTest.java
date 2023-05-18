package org.contextmapper.generated.statcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.statcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class StatisticSubjectQuestionDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(StatisticSubjectQuestionDTO.class);
        StatisticSubjectQuestionDTO statisticSubjectQuestionDTO1 = new StatisticSubjectQuestionDTO();
        statisticSubjectQuestionDTO1.setId(1L);
        StatisticSubjectQuestionDTO statisticSubjectQuestionDTO2 = new StatisticSubjectQuestionDTO();
        assertThat(statisticSubjectQuestionDTO1).isNotEqualTo(statisticSubjectQuestionDTO2);
        statisticSubjectQuestionDTO2.setId(statisticSubjectQuestionDTO1.getId());
        assertThat(statisticSubjectQuestionDTO1).isEqualTo(statisticSubjectQuestionDTO2);
        statisticSubjectQuestionDTO2.setId(2L);
        assertThat(statisticSubjectQuestionDTO1).isNotEqualTo(statisticSubjectQuestionDTO2);
        statisticSubjectQuestionDTO1.setId(null);
        assertThat(statisticSubjectQuestionDTO1).isNotEqualTo(statisticSubjectQuestionDTO2);
    }
}
