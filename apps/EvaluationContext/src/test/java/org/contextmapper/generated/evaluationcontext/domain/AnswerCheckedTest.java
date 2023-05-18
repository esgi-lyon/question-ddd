package org.contextmapper.generated.evaluationcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.evaluationcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AnswerCheckedTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AnswerChecked.class);
        AnswerChecked answerChecked1 = new AnswerChecked();
        answerChecked1.setId(1L);
        AnswerChecked answerChecked2 = new AnswerChecked();
        answerChecked2.setId(answerChecked1.getId());
        assertThat(answerChecked1).isEqualTo(answerChecked2);
        answerChecked2.setId(2L);
        assertThat(answerChecked1).isNotEqualTo(answerChecked2);
        answerChecked1.setId(null);
        assertThat(answerChecked1).isNotEqualTo(answerChecked2);
    }
}
