package org.contextmapper.generated.evaluationcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.evaluationcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EvaluationTagDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EvaluationTagDTO.class);
        EvaluationTagDTO evaluationTagDTO1 = new EvaluationTagDTO();
        evaluationTagDTO1.setId(1L);
        EvaluationTagDTO evaluationTagDTO2 = new EvaluationTagDTO();
        assertThat(evaluationTagDTO1).isNotEqualTo(evaluationTagDTO2);
        evaluationTagDTO2.setId(evaluationTagDTO1.getId());
        assertThat(evaluationTagDTO1).isEqualTo(evaluationTagDTO2);
        evaluationTagDTO2.setId(2L);
        assertThat(evaluationTagDTO1).isNotEqualTo(evaluationTagDTO2);
        evaluationTagDTO1.setId(null);
        assertThat(evaluationTagDTO1).isNotEqualTo(evaluationTagDTO2);
    }
}
