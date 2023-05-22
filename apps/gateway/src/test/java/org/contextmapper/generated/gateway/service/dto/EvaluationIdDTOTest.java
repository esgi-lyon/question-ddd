package org.contextmapper.generated.gateway.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EvaluationIdDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EvaluationIdDTO.class);
        EvaluationIdDTO evaluationIdDTO1 = new EvaluationIdDTO();
        evaluationIdDTO1.setId(1L);
        EvaluationIdDTO evaluationIdDTO2 = new EvaluationIdDTO();
        assertThat(evaluationIdDTO1).isNotEqualTo(evaluationIdDTO2);
        evaluationIdDTO2.setId(evaluationIdDTO1.getId());
        assertThat(evaluationIdDTO1).isEqualTo(evaluationIdDTO2);
        evaluationIdDTO2.setId(2L);
        assertThat(evaluationIdDTO1).isNotEqualTo(evaluationIdDTO2);
        evaluationIdDTO1.setId(null);
        assertThat(evaluationIdDTO1).isNotEqualTo(evaluationIdDTO2);
    }
}
