package org.contextmapper.generated.answercontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.answercontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class QuestionSentIdDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuestionSentIdDTO.class);
        QuestionSentIdDTO questionSentIdDTO1 = new QuestionSentIdDTO();
        questionSentIdDTO1.setId(1L);
        QuestionSentIdDTO questionSentIdDTO2 = new QuestionSentIdDTO();
        assertThat(questionSentIdDTO1).isNotEqualTo(questionSentIdDTO2);
        questionSentIdDTO2.setId(questionSentIdDTO1.getId());
        assertThat(questionSentIdDTO1).isEqualTo(questionSentIdDTO2);
        questionSentIdDTO2.setId(2L);
        assertThat(questionSentIdDTO1).isNotEqualTo(questionSentIdDTO2);
        questionSentIdDTO1.setId(null);
        assertThat(questionSentIdDTO1).isNotEqualTo(questionSentIdDTO2);
    }
}
