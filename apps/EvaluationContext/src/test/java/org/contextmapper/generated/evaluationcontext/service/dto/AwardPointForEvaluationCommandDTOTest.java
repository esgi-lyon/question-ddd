package org.contextmapper.generated.evaluationcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.evaluationcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AwardPointForEvaluationCommandDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AwardPointForEvaluationCommandDTO.class);
        AwardPointForEvaluationCommandDTO awardPointForEvaluationCommandDTO1 = new AwardPointForEvaluationCommandDTO();
        awardPointForEvaluationCommandDTO1.setId(1L);
        AwardPointForEvaluationCommandDTO awardPointForEvaluationCommandDTO2 = new AwardPointForEvaluationCommandDTO();
        assertThat(awardPointForEvaluationCommandDTO1).isNotEqualTo(awardPointForEvaluationCommandDTO2);
        awardPointForEvaluationCommandDTO2.setId(awardPointForEvaluationCommandDTO1.getId());
        assertThat(awardPointForEvaluationCommandDTO1).isEqualTo(awardPointForEvaluationCommandDTO2);
        awardPointForEvaluationCommandDTO2.setId(2L);
        assertThat(awardPointForEvaluationCommandDTO1).isNotEqualTo(awardPointForEvaluationCommandDTO2);
        awardPointForEvaluationCommandDTO1.setId(null);
        assertThat(awardPointForEvaluationCommandDTO1).isNotEqualTo(awardPointForEvaluationCommandDTO2);
    }
}
