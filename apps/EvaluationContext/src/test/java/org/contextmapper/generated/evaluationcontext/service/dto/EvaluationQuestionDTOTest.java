package org.contextmapper.generated.evaluationcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.evaluationcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EvaluationQuestionDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EvaluationQuestionDTO.class);
        EvaluationQuestionDTO evaluationQuestionDTO1 = new EvaluationQuestionDTO();
        evaluationQuestionDTO1.setId(1L);
        EvaluationQuestionDTO evaluationQuestionDTO2 = new EvaluationQuestionDTO();
        assertThat(evaluationQuestionDTO1).isNotEqualTo(evaluationQuestionDTO2);
        evaluationQuestionDTO2.setId(evaluationQuestionDTO1.getId());
        assertThat(evaluationQuestionDTO1).isEqualTo(evaluationQuestionDTO2);
        evaluationQuestionDTO2.setId(2L);
        assertThat(evaluationQuestionDTO1).isNotEqualTo(evaluationQuestionDTO2);
        evaluationQuestionDTO1.setId(null);
        assertThat(evaluationQuestionDTO1).isNotEqualTo(evaluationQuestionDTO2);
    }
}
