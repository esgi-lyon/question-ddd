package org.contextmapper.generated.answercontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.answercontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AnswerSubmittedEventTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AnswerSubmittedEvent.class);
        AnswerSubmittedEvent answerSubmittedEvent1 = new AnswerSubmittedEvent();
        answerSubmittedEvent1.setId(1L);
        AnswerSubmittedEvent answerSubmittedEvent2 = new AnswerSubmittedEvent();
        answerSubmittedEvent2.setId(answerSubmittedEvent1.getId());
        assertThat(answerSubmittedEvent1).isEqualTo(answerSubmittedEvent2);
        answerSubmittedEvent2.setId(2L);
        assertThat(answerSubmittedEvent1).isNotEqualTo(answerSubmittedEvent2);
        answerSubmittedEvent1.setId(null);
        assertThat(answerSubmittedEvent1).isNotEqualTo(answerSubmittedEvent2);
    }
}
