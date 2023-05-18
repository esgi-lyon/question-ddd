package org.contextmapper.generated.evaluationcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.evaluationcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AwardPointForEvaluationDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AwardPointForEvaluationDTO.class);
        AwardPointForEvaluationDTO awardPointForEvaluationDTO1 = new AwardPointForEvaluationDTO();
        awardPointForEvaluationDTO1.setId(1L);
        AwardPointForEvaluationDTO awardPointForEvaluationDTO2 = new AwardPointForEvaluationDTO();
        assertThat(awardPointForEvaluationDTO1).isNotEqualTo(awardPointForEvaluationDTO2);
        awardPointForEvaluationDTO2.setId(awardPointForEvaluationDTO1.getId());
        assertThat(awardPointForEvaluationDTO1).isEqualTo(awardPointForEvaluationDTO2);
        awardPointForEvaluationDTO2.setId(2L);
        assertThat(awardPointForEvaluationDTO1).isNotEqualTo(awardPointForEvaluationDTO2);
        awardPointForEvaluationDTO1.setId(null);
        assertThat(awardPointForEvaluationDTO1).isNotEqualTo(awardPointForEvaluationDTO2);
    }
}
