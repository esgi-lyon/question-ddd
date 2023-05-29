package org.contextmapper.generated.sendquestioncontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.sendquestioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class QuestionSentTagInfosViewedEventTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuestionSentTagInfosViewedEvent.class);
        QuestionSentTagInfosViewedEvent questionSentTagInfosViewedEvent1 = new QuestionSentTagInfosViewedEvent();
        questionSentTagInfosViewedEvent1.setId(1L);
        QuestionSentTagInfosViewedEvent questionSentTagInfosViewedEvent2 = new QuestionSentTagInfosViewedEvent();
        questionSentTagInfosViewedEvent2.setId(questionSentTagInfosViewedEvent1.getId());
        assertThat(questionSentTagInfosViewedEvent1).isEqualTo(questionSentTagInfosViewedEvent2);
        questionSentTagInfosViewedEvent2.setId(2L);
        assertThat(questionSentTagInfosViewedEvent1).isNotEqualTo(questionSentTagInfosViewedEvent2);
        questionSentTagInfosViewedEvent1.setId(null);
        assertThat(questionSentTagInfosViewedEvent1).isNotEqualTo(questionSentTagInfosViewedEvent2);
    }
}
