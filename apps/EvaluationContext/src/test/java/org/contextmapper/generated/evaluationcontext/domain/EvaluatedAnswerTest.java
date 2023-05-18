package org.contextmapper.generated.evaluationcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.evaluationcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EvaluatedAnswerTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EvaluatedAnswer.class);
        EvaluatedAnswer evaluatedAnswer1 = new EvaluatedAnswer();
        evaluatedAnswer1.setId(1L);
        EvaluatedAnswer evaluatedAnswer2 = new EvaluatedAnswer();
        evaluatedAnswer2.setId(evaluatedAnswer1.getId());
        assertThat(evaluatedAnswer1).isEqualTo(evaluatedAnswer2);
        evaluatedAnswer2.setId(2L);
        assertThat(evaluatedAnswer1).isNotEqualTo(evaluatedAnswer2);
        evaluatedAnswer1.setId(null);
        assertThat(evaluatedAnswer1).isNotEqualTo(evaluatedAnswer2);
    }
}
