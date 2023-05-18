package org.contextmapper.generated.statcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.statcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class StatisticSubjectTagDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(StatisticSubjectTagDTO.class);
        StatisticSubjectTagDTO statisticSubjectTagDTO1 = new StatisticSubjectTagDTO();
        statisticSubjectTagDTO1.setId(1L);
        StatisticSubjectTagDTO statisticSubjectTagDTO2 = new StatisticSubjectTagDTO();
        assertThat(statisticSubjectTagDTO1).isNotEqualTo(statisticSubjectTagDTO2);
        statisticSubjectTagDTO2.setId(statisticSubjectTagDTO1.getId());
        assertThat(statisticSubjectTagDTO1).isEqualTo(statisticSubjectTagDTO2);
        statisticSubjectTagDTO2.setId(2L);
        assertThat(statisticSubjectTagDTO1).isNotEqualTo(statisticSubjectTagDTO2);
        statisticSubjectTagDTO1.setId(null);
        assertThat(statisticSubjectTagDTO1).isNotEqualTo(statisticSubjectTagDTO2);
    }
}
