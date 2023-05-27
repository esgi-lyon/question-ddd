package org.contextmapper.generated.answercontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.answercontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AnswerSubmitCommandDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AnswerSubmitCommandDTO.class);
        AnswerSubmitCommandDTO answerSubmitCommandDTO1 = new AnswerSubmitCommandDTO();
        answerSubmitCommandDTO1.setId(1L);
        AnswerSubmitCommandDTO answerSubmitCommandDTO2 = new AnswerSubmitCommandDTO();
        assertThat(answerSubmitCommandDTO1).isNotEqualTo(answerSubmitCommandDTO2);
        answerSubmitCommandDTO2.setId(answerSubmitCommandDTO1.getId());
        assertThat(answerSubmitCommandDTO1).isEqualTo(answerSubmitCommandDTO2);
        answerSubmitCommandDTO2.setId(2L);
        assertThat(answerSubmitCommandDTO1).isNotEqualTo(answerSubmitCommandDTO2);
        answerSubmitCommandDTO1.setId(null);
        assertThat(answerSubmitCommandDTO1).isNotEqualTo(answerSubmitCommandDTO2);
    }
}
