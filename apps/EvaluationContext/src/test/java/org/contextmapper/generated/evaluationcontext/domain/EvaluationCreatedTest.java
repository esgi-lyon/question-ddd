package org.contextmapper.generated.evaluationcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.evaluationcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EvaluationCreatedTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EvaluationCreated.class);
        EvaluationCreated evaluationCreated1 = new EvaluationCreated();
        evaluationCreated1.setId(1L);
        EvaluationCreated evaluationCreated2 = new EvaluationCreated();
        evaluationCreated2.setId(evaluationCreated1.getId());
        assertThat(evaluationCreated1).isEqualTo(evaluationCreated2);
        evaluationCreated2.setId(2L);
        assertThat(evaluationCreated1).isNotEqualTo(evaluationCreated2);
        evaluationCreated1.setId(null);
        assertThat(evaluationCreated1).isNotEqualTo(evaluationCreated2);
    }
}
