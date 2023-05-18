package org.contextmapper.generated.answercontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.answercontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AnswerSubmittedTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AnswerSubmitted.class);
        AnswerSubmitted answerSubmitted1 = new AnswerSubmitted();
        answerSubmitted1.setId(1L);
        AnswerSubmitted answerSubmitted2 = new AnswerSubmitted();
        answerSubmitted2.setId(answerSubmitted1.getId());
        assertThat(answerSubmitted1).isEqualTo(answerSubmitted2);
        answerSubmitted2.setId(2L);
        assertThat(answerSubmitted1).isNotEqualTo(answerSubmitted2);
        answerSubmitted1.setId(null);
        assertThat(answerSubmitted1).isNotEqualTo(answerSubmitted2);
    }
}
