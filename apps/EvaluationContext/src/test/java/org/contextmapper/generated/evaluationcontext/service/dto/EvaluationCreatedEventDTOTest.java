package org.contextmapper.generated.evaluationcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.evaluationcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EvaluationCreatedEventDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EvaluationCreatedEventDTO.class);
        EvaluationCreatedEventDTO evaluationCreatedEventDTO1 = new EvaluationCreatedEventDTO();
        evaluationCreatedEventDTO1.setId(1L);
        EvaluationCreatedEventDTO evaluationCreatedEventDTO2 = new EvaluationCreatedEventDTO();
        assertThat(evaluationCreatedEventDTO1).isNotEqualTo(evaluationCreatedEventDTO2);
        evaluationCreatedEventDTO2.setId(evaluationCreatedEventDTO1.getId());
        assertThat(evaluationCreatedEventDTO1).isEqualTo(evaluationCreatedEventDTO2);
        evaluationCreatedEventDTO2.setId(2L);
        assertThat(evaluationCreatedEventDTO1).isNotEqualTo(evaluationCreatedEventDTO2);
        evaluationCreatedEventDTO1.setId(null);
        assertThat(evaluationCreatedEventDTO1).isNotEqualTo(evaluationCreatedEventDTO2);
    }
}
