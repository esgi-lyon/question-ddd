package org.contextmapper.generated.evaluationcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.evaluationcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CheckAnswerDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CheckAnswerDTO.class);
        CheckAnswerDTO checkAnswerDTO1 = new CheckAnswerDTO();
        checkAnswerDTO1.setId(1L);
        CheckAnswerDTO checkAnswerDTO2 = new CheckAnswerDTO();
        assertThat(checkAnswerDTO1).isNotEqualTo(checkAnswerDTO2);
        checkAnswerDTO2.setId(checkAnswerDTO1.getId());
        assertThat(checkAnswerDTO1).isEqualTo(checkAnswerDTO2);
        checkAnswerDTO2.setId(2L);
        assertThat(checkAnswerDTO1).isNotEqualTo(checkAnswerDTO2);
        checkAnswerDTO1.setId(null);
        assertThat(checkAnswerDTO1).isNotEqualTo(checkAnswerDTO2);
    }
}
