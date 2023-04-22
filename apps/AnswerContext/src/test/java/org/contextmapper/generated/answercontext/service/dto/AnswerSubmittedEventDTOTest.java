package org.contextmapper.generated.answercontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.answercontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AnswerSubmittedEventDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AnswerSubmittedEventDTO.class);
        AnswerSubmittedEventDTO answerSubmittedEventDTO1 = new AnswerSubmittedEventDTO();
        answerSubmittedEventDTO1.setId(1L);
        AnswerSubmittedEventDTO answerSubmittedEventDTO2 = new AnswerSubmittedEventDTO();
        assertThat(answerSubmittedEventDTO1).isNotEqualTo(answerSubmittedEventDTO2);
        answerSubmittedEventDTO2.setId(answerSubmittedEventDTO1.getId());
        assertThat(answerSubmittedEventDTO1).isEqualTo(answerSubmittedEventDTO2);
        answerSubmittedEventDTO2.setId(2L);
        assertThat(answerSubmittedEventDTO1).isNotEqualTo(answerSubmittedEventDTO2);
        answerSubmittedEventDTO1.setId(null);
        assertThat(answerSubmittedEventDTO1).isNotEqualTo(answerSubmittedEventDTO2);
    }
}
