package org.contextmapper.generated.statcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.statcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class StatisticSubjectUserTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StatisticSubjectUser.class);
        StatisticSubjectUser statisticSubjectUser1 = new StatisticSubjectUser();
        statisticSubjectUser1.setId(1L);
        StatisticSubjectUser statisticSubjectUser2 = new StatisticSubjectUser();
        statisticSubjectUser2.setId(statisticSubjectUser1.getId());
        assertThat(statisticSubjectUser1).isEqualTo(statisticSubjectUser2);
        statisticSubjectUser2.setId(2L);
        assertThat(statisticSubjectUser1).isNotEqualTo(statisticSubjectUser2);
        statisticSubjectUser1.setId(null);
        assertThat(statisticSubjectUser1).isNotEqualTo(statisticSubjectUser2);
    }
}
