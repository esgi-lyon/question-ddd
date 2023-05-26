package org.contextmapper.generated.answercontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.answercontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AnswerCreatedEventDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AnswerCreatedEventDTO.class);
        AnswerCreatedEventDTO answerCreatedEventDTO1 = new AnswerCreatedEventDTO();
        answerCreatedEventDTO1.setId(1L);
        AnswerCreatedEventDTO answerCreatedEventDTO2 = new AnswerCreatedEventDTO();
        assertThat(answerCreatedEventDTO1).isNotEqualTo(answerCreatedEventDTO2);
        answerCreatedEventDTO2.setId(answerCreatedEventDTO1.getId());
        assertThat(answerCreatedEventDTO1).isEqualTo(answerCreatedEventDTO2);
        answerCreatedEventDTO2.setId(2L);
        assertThat(answerCreatedEventDTO1).isNotEqualTo(answerCreatedEventDTO2);
        answerCreatedEventDTO1.setId(null);
        assertThat(answerCreatedEventDTO1).isNotEqualTo(answerCreatedEventDTO2);
    }
}
