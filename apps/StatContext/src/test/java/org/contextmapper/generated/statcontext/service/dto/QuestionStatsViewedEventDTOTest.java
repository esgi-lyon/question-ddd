package org.contextmapper.generated.statcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.statcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class QuestionStatsViewedEventDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuestionStatsViewedEventDTO.class);
        QuestionStatsViewedEventDTO questionStatsViewedEventDTO1 = new QuestionStatsViewedEventDTO();
        questionStatsViewedEventDTO1.setId(1L);
        QuestionStatsViewedEventDTO questionStatsViewedEventDTO2 = new QuestionStatsViewedEventDTO();
        assertThat(questionStatsViewedEventDTO1).isNotEqualTo(questionStatsViewedEventDTO2);
        questionStatsViewedEventDTO2.setId(questionStatsViewedEventDTO1.getId());
        assertThat(questionStatsViewedEventDTO1).isEqualTo(questionStatsViewedEventDTO2);
        questionStatsViewedEventDTO2.setId(2L);
        assertThat(questionStatsViewedEventDTO1).isNotEqualTo(questionStatsViewedEventDTO2);
        questionStatsViewedEventDTO1.setId(null);
        assertThat(questionStatsViewedEventDTO1).isNotEqualTo(questionStatsViewedEventDTO2);
    }
}
