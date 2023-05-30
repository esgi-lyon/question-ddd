package org.contextmapper.generated.evaluationcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.evaluationcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AnswerCheckedDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AnswerCheckedDTO.class);
        AnswerCheckedDTO answerCheckedDTO1 = new AnswerCheckedDTO();
        answerCheckedDTO1.setId(1L);
        AnswerCheckedDTO answerCheckedDTO2 = new AnswerCheckedDTO();
        assertThat(answerCheckedDTO1).isNotEqualTo(answerCheckedDTO2);
        answerCheckedDTO2.setId(answerCheckedDTO1.getId());
        assertThat(answerCheckedDTO1).isEqualTo(answerCheckedDTO2);
        answerCheckedDTO2.setId(2L);
        assertThat(answerCheckedDTO1).isNotEqualTo(answerCheckedDTO2);
        answerCheckedDTO1.setId(null);
        assertThat(answerCheckedDTO1).isNotEqualTo(answerCheckedDTO2);
    }
}
