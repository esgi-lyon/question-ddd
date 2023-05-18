package org.contextmapper.generated.evaluationcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.evaluationcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CreateEvaluationDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CreateEvaluationDTO.class);
        CreateEvaluationDTO createEvaluationDTO1 = new CreateEvaluationDTO();
        createEvaluationDTO1.setId(1L);
        CreateEvaluationDTO createEvaluationDTO2 = new CreateEvaluationDTO();
        assertThat(createEvaluationDTO1).isNotEqualTo(createEvaluationDTO2);
        createEvaluationDTO2.setId(createEvaluationDTO1.getId());
        assertThat(createEvaluationDTO1).isEqualTo(createEvaluationDTO2);
        createEvaluationDTO2.setId(2L);
        assertThat(createEvaluationDTO1).isNotEqualTo(createEvaluationDTO2);
        createEvaluationDTO1.setId(null);
        assertThat(createEvaluationDTO1).isNotEqualTo(createEvaluationDTO2);
    }
}
