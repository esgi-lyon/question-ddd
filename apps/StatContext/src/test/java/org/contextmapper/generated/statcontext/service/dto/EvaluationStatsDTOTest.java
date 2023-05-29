package org.contextmapper.generated.statcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.statcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EvaluationStatsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EvaluationStatsDTO.class);
        EvaluationStatsDTO evaluationStatsDTO1 = new EvaluationStatsDTO();
        evaluationStatsDTO1.setId(1L);
        EvaluationStatsDTO evaluationStatsDTO2 = new EvaluationStatsDTO();
        assertThat(evaluationStatsDTO1).isNotEqualTo(evaluationStatsDTO2);
        evaluationStatsDTO2.setId(evaluationStatsDTO1.getId());
        assertThat(evaluationStatsDTO1).isEqualTo(evaluationStatsDTO2);
        evaluationStatsDTO2.setId(2L);
        assertThat(evaluationStatsDTO1).isNotEqualTo(evaluationStatsDTO2);
        evaluationStatsDTO1.setId(null);
        assertThat(evaluationStatsDTO1).isNotEqualTo(evaluationStatsDTO2);
    }
}
