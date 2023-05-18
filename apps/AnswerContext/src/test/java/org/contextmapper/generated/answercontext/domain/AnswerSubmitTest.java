package org.contextmapper.generated.answercontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.answercontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AnswerSubmitTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AnswerSubmit.class);
        AnswerSubmit answerSubmit1 = new AnswerSubmit();
        answerSubmit1.setId(1L);
        AnswerSubmit answerSubmit2 = new AnswerSubmit();
        answerSubmit2.setId(answerSubmit1.getId());
        assertThat(answerSubmit1).isEqualTo(answerSubmit2);
        answerSubmit2.setId(2L);
        assertThat(answerSubmit1).isNotEqualTo(answerSubmit2);
        answerSubmit1.setId(null);
        assertThat(answerSubmit1).isNotEqualTo(answerSubmit2);
    }
}
