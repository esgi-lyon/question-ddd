package org.contextmapper.generated.evaluationcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.evaluationcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CreateEvaluationTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CreateEvaluation.class);
        CreateEvaluation createEvaluation1 = new CreateEvaluation();
        createEvaluation1.setId(1L);
        CreateEvaluation createEvaluation2 = new CreateEvaluation();
        createEvaluation2.setId(createEvaluation1.getId());
        assertThat(createEvaluation1).isEqualTo(createEvaluation2);
        createEvaluation2.setId(2L);
        assertThat(createEvaluation1).isNotEqualTo(createEvaluation2);
        createEvaluation1.setId(null);
        assertThat(createEvaluation1).isNotEqualTo(createEvaluation2);
    }
}
