package org.contextmapper.generated.statcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.statcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class StatisticSubjectQuestionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StatisticSubjectQuestion.class);
        StatisticSubjectQuestion statisticSubjectQuestion1 = new StatisticSubjectQuestion();
        statisticSubjectQuestion1.setId(1L);
        StatisticSubjectQuestion statisticSubjectQuestion2 = new StatisticSubjectQuestion();
        statisticSubjectQuestion2.setId(statisticSubjectQuestion1.getId());
        assertThat(statisticSubjectQuestion1).isEqualTo(statisticSubjectQuestion2);
        statisticSubjectQuestion2.setId(2L);
        assertThat(statisticSubjectQuestion1).isNotEqualTo(statisticSubjectQuestion2);
        statisticSubjectQuestion1.setId(null);
        assertThat(statisticSubjectQuestion1).isNotEqualTo(statisticSubjectQuestion2);
    }
}
