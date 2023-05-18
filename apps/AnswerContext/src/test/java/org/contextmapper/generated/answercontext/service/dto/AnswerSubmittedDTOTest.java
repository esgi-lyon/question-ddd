package org.contextmapper.generated.answercontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.answercontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AnswerSubmittedDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AnswerSubmittedDTO.class);
        AnswerSubmittedDTO answerSubmittedDTO1 = new AnswerSubmittedDTO();
        answerSubmittedDTO1.setId(1L);
        AnswerSubmittedDTO answerSubmittedDTO2 = new AnswerSubmittedDTO();
        assertThat(answerSubmittedDTO1).isNotEqualTo(answerSubmittedDTO2);
        answerSubmittedDTO2.setId(answerSubmittedDTO1.getId());
        assertThat(answerSubmittedDTO1).isEqualTo(answerSubmittedDTO2);
        answerSubmittedDTO2.setId(2L);
        assertThat(answerSubmittedDTO1).isNotEqualTo(answerSubmittedDTO2);
        answerSubmittedDTO1.setId(null);
        assertThat(answerSubmittedDTO1).isNotEqualTo(answerSubmittedDTO2);
    }
}
