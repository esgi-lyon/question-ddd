package org.contextmapper.generated.statcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.statcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class StatisticSubjectUserDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(StatisticSubjectUserDTO.class);
        StatisticSubjectUserDTO statisticSubjectUserDTO1 = new StatisticSubjectUserDTO();
        statisticSubjectUserDTO1.setId(1L);
        StatisticSubjectUserDTO statisticSubjectUserDTO2 = new StatisticSubjectUserDTO();
        assertThat(statisticSubjectUserDTO1).isNotEqualTo(statisticSubjectUserDTO2);
        statisticSubjectUserDTO2.setId(statisticSubjectUserDTO1.getId());
        assertThat(statisticSubjectUserDTO1).isEqualTo(statisticSubjectUserDTO2);
        statisticSubjectUserDTO2.setId(2L);
        assertThat(statisticSubjectUserDTO1).isNotEqualTo(statisticSubjectUserDTO2);
        statisticSubjectUserDTO1.setId(null);
        assertThat(statisticSubjectUserDTO1).isNotEqualTo(statisticSubjectUserDTO2);
    }
}
