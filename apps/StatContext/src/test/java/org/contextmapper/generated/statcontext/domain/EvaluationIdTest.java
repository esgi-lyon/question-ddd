package org.contextmapper.generated.statcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.statcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EvaluationIdTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EvaluationId.class);
        EvaluationId evaluationId1 = new EvaluationId();
        evaluationId1.setId(1L);
        EvaluationId evaluationId2 = new EvaluationId();
        evaluationId2.setId(evaluationId1.getId());
        assertThat(evaluationId1).isEqualTo(evaluationId2);
        evaluationId2.setId(2L);
        assertThat(evaluationId1).isNotEqualTo(evaluationId2);
        evaluationId1.setId(null);
        assertThat(evaluationId1).isNotEqualTo(evaluationId2);
    }
}
