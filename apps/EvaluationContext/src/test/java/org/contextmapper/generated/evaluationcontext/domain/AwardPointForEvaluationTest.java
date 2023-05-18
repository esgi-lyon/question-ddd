package org.contextmapper.generated.evaluationcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.evaluationcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AwardPointForEvaluationTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AwardPointForEvaluation.class);
        AwardPointForEvaluation awardPointForEvaluation1 = new AwardPointForEvaluation();
        awardPointForEvaluation1.setId(1L);
        AwardPointForEvaluation awardPointForEvaluation2 = new AwardPointForEvaluation();
        awardPointForEvaluation2.setId(awardPointForEvaluation1.getId());
        assertThat(awardPointForEvaluation1).isEqualTo(awardPointForEvaluation2);
        awardPointForEvaluation2.setId(2L);
        assertThat(awardPointForEvaluation1).isNotEqualTo(awardPointForEvaluation2);
        awardPointForEvaluation1.setId(null);
        assertThat(awardPointForEvaluation1).isNotEqualTo(awardPointForEvaluation2);
    }
}
