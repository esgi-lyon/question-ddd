package org.contextmapper.generated.evaluationcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.evaluationcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EvaluatedAnswerDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EvaluatedAnswerDTO.class);
        EvaluatedAnswerDTO evaluatedAnswerDTO1 = new EvaluatedAnswerDTO();
        evaluatedAnswerDTO1.setId(1L);
        EvaluatedAnswerDTO evaluatedAnswerDTO2 = new EvaluatedAnswerDTO();
        assertThat(evaluatedAnswerDTO1).isNotEqualTo(evaluatedAnswerDTO2);
        evaluatedAnswerDTO2.setId(evaluatedAnswerDTO1.getId());
        assertThat(evaluatedAnswerDTO1).isEqualTo(evaluatedAnswerDTO2);
        evaluatedAnswerDTO2.setId(2L);
        assertThat(evaluatedAnswerDTO1).isNotEqualTo(evaluatedAnswerDTO2);
        evaluatedAnswerDTO1.setId(null);
        assertThat(evaluatedAnswerDTO1).isNotEqualTo(evaluatedAnswerDTO2);
    }
}
