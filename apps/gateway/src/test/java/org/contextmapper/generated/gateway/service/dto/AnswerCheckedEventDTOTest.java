package org.contextmapper.generated.gateway.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AnswerCheckedEventDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AnswerCheckedEventDTO.class);
        AnswerCheckedEventDTO answerCheckedEventDTO1 = new AnswerCheckedEventDTO();
        answerCheckedEventDTO1.setId(1L);
        AnswerCheckedEventDTO answerCheckedEventDTO2 = new AnswerCheckedEventDTO();
        assertThat(answerCheckedEventDTO1).isNotEqualTo(answerCheckedEventDTO2);
        answerCheckedEventDTO2.setId(answerCheckedEventDTO1.getId());
        assertThat(answerCheckedEventDTO1).isEqualTo(answerCheckedEventDTO2);
        answerCheckedEventDTO2.setId(2L);
        assertThat(answerCheckedEventDTO1).isNotEqualTo(answerCheckedEventDTO2);
        answerCheckedEventDTO1.setId(null);
        assertThat(answerCheckedEventDTO1).isNotEqualTo(answerCheckedEventDTO2);
    }
}
