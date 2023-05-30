package org.contextmapper.generated.statcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.statcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EvaluationStatEntryTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EvaluationStatEntry.class);
        EvaluationStatEntry evaluationStatEntry1 = new EvaluationStatEntry();
        evaluationStatEntry1.setId(1L);
        EvaluationStatEntry evaluationStatEntry2 = new EvaluationStatEntry();
        evaluationStatEntry2.setId(evaluationStatEntry1.getId());
        assertThat(evaluationStatEntry1).isEqualTo(evaluationStatEntry2);
        evaluationStatEntry2.setId(2L);
        assertThat(evaluationStatEntry1).isNotEqualTo(evaluationStatEntry2);
        evaluationStatEntry1.setId(null);
        assertThat(evaluationStatEntry1).isNotEqualTo(evaluationStatEntry2);
    }
}
