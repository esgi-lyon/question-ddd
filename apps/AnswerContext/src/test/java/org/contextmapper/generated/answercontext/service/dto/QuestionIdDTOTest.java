package org.contextmapper.generated.answercontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.answercontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class QuestionIdDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuestionIdDTO.class);
        QuestionIdDTO questionIdDTO1 = new QuestionIdDTO();
        questionIdDTO1.setId(1L);
        QuestionIdDTO questionIdDTO2 = new QuestionIdDTO();
        assertThat(questionIdDTO1).isNotEqualTo(questionIdDTO2);
        questionIdDTO2.setId(questionIdDTO1.getId());
        assertThat(questionIdDTO1).isEqualTo(questionIdDTO2);
        questionIdDTO2.setId(2L);
        assertThat(questionIdDTO1).isNotEqualTo(questionIdDTO2);
        questionIdDTO1.setId(null);
        assertThat(questionIdDTO1).isNotEqualTo(questionIdDTO2);
    }
}
