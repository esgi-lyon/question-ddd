package org.contextmapper.generated.statcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.statcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EvaluationStatsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EvaluationStats.class);
        EvaluationStats evaluationStats1 = new EvaluationStats();
        evaluationStats1.setId(1L);
        EvaluationStats evaluationStats2 = new EvaluationStats();
        evaluationStats2.setId(evaluationStats1.getId());
        assertThat(evaluationStats1).isEqualTo(evaluationStats2);
        evaluationStats2.setId(2L);
        assertThat(evaluationStats1).isNotEqualTo(evaluationStats2);
        evaluationStats1.setId(null);
        assertThat(evaluationStats1).isNotEqualTo(evaluationStats2);
    }
}
