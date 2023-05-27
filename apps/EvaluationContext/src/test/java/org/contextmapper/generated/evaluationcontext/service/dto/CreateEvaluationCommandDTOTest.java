package org.contextmapper.generated.evaluationcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.evaluationcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CreateEvaluationCommandDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CreateEvaluationCommandDTO.class);
        CreateEvaluationCommandDTO createEvaluationCommandDTO1 = new CreateEvaluationCommandDTO();
        createEvaluationCommandDTO1.setId(1L);
        CreateEvaluationCommandDTO createEvaluationCommandDTO2 = new CreateEvaluationCommandDTO();
        assertThat(createEvaluationCommandDTO1).isNotEqualTo(createEvaluationCommandDTO2);
        createEvaluationCommandDTO2.setId(createEvaluationCommandDTO1.getId());
        assertThat(createEvaluationCommandDTO1).isEqualTo(createEvaluationCommandDTO2);
        createEvaluationCommandDTO2.setId(2L);
        assertThat(createEvaluationCommandDTO1).isNotEqualTo(createEvaluationCommandDTO2);
        createEvaluationCommandDTO1.setId(null);
        assertThat(createEvaluationCommandDTO1).isNotEqualTo(createEvaluationCommandDTO2);
    }
}
