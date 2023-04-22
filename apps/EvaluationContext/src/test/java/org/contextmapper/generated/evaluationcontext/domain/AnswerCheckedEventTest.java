package org.contextmapper.generated.evaluationcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.evaluationcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AnswerCheckedEventTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AnswerCheckedEvent.class);
        AnswerCheckedEvent answerCheckedEvent1 = new AnswerCheckedEvent();
        answerCheckedEvent1.setId(1L);
        AnswerCheckedEvent answerCheckedEvent2 = new AnswerCheckedEvent();
        answerCheckedEvent2.setId(answerCheckedEvent1.getId());
        assertThat(answerCheckedEvent1).isEqualTo(answerCheckedEvent2);
        answerCheckedEvent2.setId(2L);
        assertThat(answerCheckedEvent1).isNotEqualTo(answerCheckedEvent2);
        answerCheckedEvent1.setId(null);
        assertThat(answerCheckedEvent1).isNotEqualTo(answerCheckedEvent2);
    }
}
