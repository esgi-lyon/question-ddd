package org.contextmapper.generated.evaluationcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.evaluationcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EvaluationQuestionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EvaluationQuestion.class);
        EvaluationQuestion evaluationQuestion1 = new EvaluationQuestion();
        evaluationQuestion1.setId(1L);
        EvaluationQuestion evaluationQuestion2 = new EvaluationQuestion();
        evaluationQuestion2.setId(evaluationQuestion1.getId());
        assertThat(evaluationQuestion1).isEqualTo(evaluationQuestion2);
        evaluationQuestion2.setId(2L);
        assertThat(evaluationQuestion1).isNotEqualTo(evaluationQuestion2);
        evaluationQuestion1.setId(null);
        assertThat(evaluationQuestion1).isNotEqualTo(evaluationQuestion2);
    }
}
