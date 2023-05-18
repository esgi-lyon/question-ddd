package org.contextmapper.generated.statcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.statcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class QuestionStatsViewedTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuestionStatsViewed.class);
        QuestionStatsViewed questionStatsViewed1 = new QuestionStatsViewed();
        questionStatsViewed1.setId(1L);
        QuestionStatsViewed questionStatsViewed2 = new QuestionStatsViewed();
        questionStatsViewed2.setId(questionStatsViewed1.getId());
        assertThat(questionStatsViewed1).isEqualTo(questionStatsViewed2);
        questionStatsViewed2.setId(2L);
        assertThat(questionStatsViewed1).isNotEqualTo(questionStatsViewed2);
        questionStatsViewed1.setId(null);
        assertThat(questionStatsViewed1).isNotEqualTo(questionStatsViewed2);
    }
}
