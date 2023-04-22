package org.contextmapper.generated.evaluationcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.evaluationcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AwardPointForEvaluationCommandTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AwardPointForEvaluationCommand.class);
        AwardPointForEvaluationCommand awardPointForEvaluationCommand1 = new AwardPointForEvaluationCommand();
        awardPointForEvaluationCommand1.setId(1L);
        AwardPointForEvaluationCommand awardPointForEvaluationCommand2 = new AwardPointForEvaluationCommand();
        awardPointForEvaluationCommand2.setId(awardPointForEvaluationCommand1.getId());
        assertThat(awardPointForEvaluationCommand1).isEqualTo(awardPointForEvaluationCommand2);
        awardPointForEvaluationCommand2.setId(2L);
        assertThat(awardPointForEvaluationCommand1).isNotEqualTo(awardPointForEvaluationCommand2);
        awardPointForEvaluationCommand1.setId(null);
        assertThat(awardPointForEvaluationCommand1).isNotEqualTo(awardPointForEvaluationCommand2);
    }
}
