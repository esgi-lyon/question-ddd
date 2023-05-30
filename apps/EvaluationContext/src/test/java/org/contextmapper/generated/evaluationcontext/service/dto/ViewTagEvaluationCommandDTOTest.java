package org.contextmapper.generated.evaluationcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.evaluationcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ViewTagEvaluationCommandDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ViewTagEvaluationCommandDTO.class);
        ViewTagEvaluationCommandDTO viewTagEvaluationCommandDTO1 = new ViewTagEvaluationCommandDTO();
        viewTagEvaluationCommandDTO1.setId(1L);
        ViewTagEvaluationCommandDTO viewTagEvaluationCommandDTO2 = new ViewTagEvaluationCommandDTO();
        assertThat(viewTagEvaluationCommandDTO1).isNotEqualTo(viewTagEvaluationCommandDTO2);
        viewTagEvaluationCommandDTO2.setId(viewTagEvaluationCommandDTO1.getId());
        assertThat(viewTagEvaluationCommandDTO1).isEqualTo(viewTagEvaluationCommandDTO2);
        viewTagEvaluationCommandDTO2.setId(2L);
        assertThat(viewTagEvaluationCommandDTO1).isNotEqualTo(viewTagEvaluationCommandDTO2);
        viewTagEvaluationCommandDTO1.setId(null);
        assertThat(viewTagEvaluationCommandDTO1).isNotEqualTo(viewTagEvaluationCommandDTO2);
    }
}
