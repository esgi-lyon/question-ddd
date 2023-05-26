package org.contextmapper.generated.answercontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.answercontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AnswerCreatedEventTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AnswerCreatedEvent.class);
        AnswerCreatedEvent answerCreatedEvent1 = new AnswerCreatedEvent();
        answerCreatedEvent1.setId(1L);
        AnswerCreatedEvent answerCreatedEvent2 = new AnswerCreatedEvent();
        answerCreatedEvent2.setId(answerCreatedEvent1.getId());
        assertThat(answerCreatedEvent1).isEqualTo(answerCreatedEvent2);
        answerCreatedEvent2.setId(2L);
        assertThat(answerCreatedEvent1).isNotEqualTo(answerCreatedEvent2);
        answerCreatedEvent1.setId(null);
        assertThat(answerCreatedEvent1).isNotEqualTo(answerCreatedEvent2);
    }
}
