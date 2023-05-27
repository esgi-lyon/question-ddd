package org.contextmapper.generated.evaluationcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.evaluationcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CheckAnswerCommandDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CheckAnswerCommandDTO.class);
        CheckAnswerCommandDTO checkAnswerCommandDTO1 = new CheckAnswerCommandDTO();
        checkAnswerCommandDTO1.setId(1L);
        CheckAnswerCommandDTO checkAnswerCommandDTO2 = new CheckAnswerCommandDTO();
        assertThat(checkAnswerCommandDTO1).isNotEqualTo(checkAnswerCommandDTO2);
        checkAnswerCommandDTO2.setId(checkAnswerCommandDTO1.getId());
        assertThat(checkAnswerCommandDTO1).isEqualTo(checkAnswerCommandDTO2);
        checkAnswerCommandDTO2.setId(2L);
        assertThat(checkAnswerCommandDTO1).isNotEqualTo(checkAnswerCommandDTO2);
        checkAnswerCommandDTO1.setId(null);
        assertThat(checkAnswerCommandDTO1).isNotEqualTo(checkAnswerCommandDTO2);
    }
}
