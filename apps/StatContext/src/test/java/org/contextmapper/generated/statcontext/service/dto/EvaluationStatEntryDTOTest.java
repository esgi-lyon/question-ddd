package org.contextmapper.generated.statcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.statcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EvaluationStatEntryDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EvaluationStatEntryDTO.class);
        EvaluationStatEntryDTO evaluationStatEntryDTO1 = new EvaluationStatEntryDTO();
        evaluationStatEntryDTO1.setId(1L);
        EvaluationStatEntryDTO evaluationStatEntryDTO2 = new EvaluationStatEntryDTO();
        assertThat(evaluationStatEntryDTO1).isNotEqualTo(evaluationStatEntryDTO2);
        evaluationStatEntryDTO2.setId(evaluationStatEntryDTO1.getId());
        assertThat(evaluationStatEntryDTO1).isEqualTo(evaluationStatEntryDTO2);
        evaluationStatEntryDTO2.setId(2L);
        assertThat(evaluationStatEntryDTO1).isNotEqualTo(evaluationStatEntryDTO2);
        evaluationStatEntryDTO1.setId(null);
        assertThat(evaluationStatEntryDTO1).isNotEqualTo(evaluationStatEntryDTO2);
    }
}
