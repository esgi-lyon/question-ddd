package org.contextmapper.generated.statcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.statcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class QuestionStatsViewedDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuestionStatsViewedDTO.class);
        QuestionStatsViewedDTO questionStatsViewedDTO1 = new QuestionStatsViewedDTO();
        questionStatsViewedDTO1.setId(1L);
        QuestionStatsViewedDTO questionStatsViewedDTO2 = new QuestionStatsViewedDTO();
        assertThat(questionStatsViewedDTO1).isNotEqualTo(questionStatsViewedDTO2);
        questionStatsViewedDTO2.setId(questionStatsViewedDTO1.getId());
        assertThat(questionStatsViewedDTO1).isEqualTo(questionStatsViewedDTO2);
        questionStatsViewedDTO2.setId(2L);
        assertThat(questionStatsViewedDTO1).isNotEqualTo(questionStatsViewedDTO2);
        questionStatsViewedDTO1.setId(null);
        assertThat(questionStatsViewedDTO1).isNotEqualTo(questionStatsViewedDTO2);
    }
}
