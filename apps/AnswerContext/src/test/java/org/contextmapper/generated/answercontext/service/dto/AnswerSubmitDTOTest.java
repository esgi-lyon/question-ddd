package org.contextmapper.generated.answercontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.answercontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AnswerSubmitDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AnswerSubmitDTO.class);
        AnswerSubmitDTO answerSubmitDTO1 = new AnswerSubmitDTO();
        answerSubmitDTO1.setId(1L);
        AnswerSubmitDTO answerSubmitDTO2 = new AnswerSubmitDTO();
        assertThat(answerSubmitDTO1).isNotEqualTo(answerSubmitDTO2);
        answerSubmitDTO2.setId(answerSubmitDTO1.getId());
        assertThat(answerSubmitDTO1).isEqualTo(answerSubmitDTO2);
        answerSubmitDTO2.setId(2L);
        assertThat(answerSubmitDTO1).isNotEqualTo(answerSubmitDTO2);
        answerSubmitDTO1.setId(null);
        assertThat(answerSubmitDTO1).isNotEqualTo(answerSubmitDTO2);
    }
}
