package org.contextmapper.generated.evaluationcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.evaluationcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ViewUserEvaluationCommandDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ViewUserEvaluationCommandDTO.class);
        ViewUserEvaluationCommandDTO viewUserEvaluationCommandDTO1 = new ViewUserEvaluationCommandDTO();
        viewUserEvaluationCommandDTO1.setId(1L);
        ViewUserEvaluationCommandDTO viewUserEvaluationCommandDTO2 = new ViewUserEvaluationCommandDTO();
        assertThat(viewUserEvaluationCommandDTO1).isNotEqualTo(viewUserEvaluationCommandDTO2);
        viewUserEvaluationCommandDTO2.setId(viewUserEvaluationCommandDTO1.getId());
        assertThat(viewUserEvaluationCommandDTO1).isEqualTo(viewUserEvaluationCommandDTO2);
        viewUserEvaluationCommandDTO2.setId(2L);
        assertThat(viewUserEvaluationCommandDTO1).isNotEqualTo(viewUserEvaluationCommandDTO2);
        viewUserEvaluationCommandDTO1.setId(null);
        assertThat(viewUserEvaluationCommandDTO1).isNotEqualTo(viewUserEvaluationCommandDTO2);
    }
}
