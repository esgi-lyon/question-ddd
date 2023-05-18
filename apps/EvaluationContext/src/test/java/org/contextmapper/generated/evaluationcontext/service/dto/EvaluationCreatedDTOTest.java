package org.contextmapper.generated.evaluationcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.evaluationcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EvaluationCreatedDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EvaluationCreatedDTO.class);
        EvaluationCreatedDTO evaluationCreatedDTO1 = new EvaluationCreatedDTO();
        evaluationCreatedDTO1.setId(1L);
        EvaluationCreatedDTO evaluationCreatedDTO2 = new EvaluationCreatedDTO();
        assertThat(evaluationCreatedDTO1).isNotEqualTo(evaluationCreatedDTO2);
        evaluationCreatedDTO2.setId(evaluationCreatedDTO1.getId());
        assertThat(evaluationCreatedDTO1).isEqualTo(evaluationCreatedDTO2);
        evaluationCreatedDTO2.setId(2L);
        assertThat(evaluationCreatedDTO1).isNotEqualTo(evaluationCreatedDTO2);
        evaluationCreatedDTO1.setId(null);
        assertThat(evaluationCreatedDTO1).isNotEqualTo(evaluationCreatedDTO2);
    }
}
