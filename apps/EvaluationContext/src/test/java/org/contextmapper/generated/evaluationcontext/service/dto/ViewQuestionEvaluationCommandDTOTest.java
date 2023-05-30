package org.contextmapper.generated.evaluationcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.evaluationcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ViewQuestionEvaluationCommandDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ViewQuestionEvaluationCommandDTO.class);
        ViewQuestionEvaluationCommandDTO viewQuestionEvaluationCommandDTO1 = new ViewQuestionEvaluationCommandDTO();
        viewQuestionEvaluationCommandDTO1.setId(1L);
        ViewQuestionEvaluationCommandDTO viewQuestionEvaluationCommandDTO2 = new ViewQuestionEvaluationCommandDTO();
        assertThat(viewQuestionEvaluationCommandDTO1).isNotEqualTo(viewQuestionEvaluationCommandDTO2);
        viewQuestionEvaluationCommandDTO2.setId(viewQuestionEvaluationCommandDTO1.getId());
        assertThat(viewQuestionEvaluationCommandDTO1).isEqualTo(viewQuestionEvaluationCommandDTO2);
        viewQuestionEvaluationCommandDTO2.setId(2L);
        assertThat(viewQuestionEvaluationCommandDTO1).isNotEqualTo(viewQuestionEvaluationCommandDTO2);
        viewQuestionEvaluationCommandDTO1.setId(null);
        assertThat(viewQuestionEvaluationCommandDTO1).isNotEqualTo(viewQuestionEvaluationCommandDTO2);
    }
}
