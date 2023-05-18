package org.contextmapper.generated.evaluationcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.evaluationcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EvaluationTagTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EvaluationTag.class);
        EvaluationTag evaluationTag1 = new EvaluationTag();
        evaluationTag1.setId(1L);
        EvaluationTag evaluationTag2 = new EvaluationTag();
        evaluationTag2.setId(evaluationTag1.getId());
        assertThat(evaluationTag1).isEqualTo(evaluationTag2);
        evaluationTag2.setId(2L);
        assertThat(evaluationTag1).isNotEqualTo(evaluationTag2);
        evaluationTag1.setId(null);
        assertThat(evaluationTag1).isNotEqualTo(evaluationTag2);
    }
}
