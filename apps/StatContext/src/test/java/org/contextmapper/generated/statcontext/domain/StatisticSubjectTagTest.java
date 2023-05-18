package org.contextmapper.generated.statcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.statcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class StatisticSubjectTagTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StatisticSubjectTag.class);
        StatisticSubjectTag statisticSubjectTag1 = new StatisticSubjectTag();
        statisticSubjectTag1.setId(1L);
        StatisticSubjectTag statisticSubjectTag2 = new StatisticSubjectTag();
        statisticSubjectTag2.setId(statisticSubjectTag1.getId());
        assertThat(statisticSubjectTag1).isEqualTo(statisticSubjectTag2);
        statisticSubjectTag2.setId(2L);
        assertThat(statisticSubjectTag1).isNotEqualTo(statisticSubjectTag2);
        statisticSubjectTag1.setId(null);
        assertThat(statisticSubjectTag1).isNotEqualTo(statisticSubjectTag2);
    }
}
