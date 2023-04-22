package org.contextmapper.generated.statcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.statcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class QuestionStatsViewedEventTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuestionStatsViewedEvent.class);
        QuestionStatsViewedEvent questionStatsViewedEvent1 = new QuestionStatsViewedEvent();
        questionStatsViewedEvent1.setId(1L);
        QuestionStatsViewedEvent questionStatsViewedEvent2 = new QuestionStatsViewedEvent();
        questionStatsViewedEvent2.setId(questionStatsViewedEvent1.getId());
        assertThat(questionStatsViewedEvent1).isEqualTo(questionStatsViewedEvent2);
        questionStatsViewedEvent2.setId(2L);
        assertThat(questionStatsViewedEvent1).isNotEqualTo(questionStatsViewedEvent2);
        questionStatsViewedEvent1.setId(null);
        assertThat(questionStatsViewedEvent1).isNotEqualTo(questionStatsViewedEvent2);
    }
}
