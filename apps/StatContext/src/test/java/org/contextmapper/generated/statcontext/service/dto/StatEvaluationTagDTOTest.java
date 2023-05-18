package org.contextmapper.generated.statcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.statcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class StatEvaluationTagDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(StatEvaluationTagDTO.class);
        StatEvaluationTagDTO statEvaluationTagDTO1 = new StatEvaluationTagDTO();
        statEvaluationTagDTO1.setId(1L);
        StatEvaluationTagDTO statEvaluationTagDTO2 = new StatEvaluationTagDTO();
        assertThat(statEvaluationTagDTO1).isNotEqualTo(statEvaluationTagDTO2);
        statEvaluationTagDTO2.setId(statEvaluationTagDTO1.getId());
        assertThat(statEvaluationTagDTO1).isEqualTo(statEvaluationTagDTO2);
        statEvaluationTagDTO2.setId(2L);
        assertThat(statEvaluationTagDTO1).isNotEqualTo(statEvaluationTagDTO2);
        statEvaluationTagDTO1.setId(null);
        assertThat(statEvaluationTagDTO1).isNotEqualTo(statEvaluationTagDTO2);
    }
}
