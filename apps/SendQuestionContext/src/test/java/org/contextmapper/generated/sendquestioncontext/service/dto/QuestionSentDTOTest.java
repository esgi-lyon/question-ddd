package org.contextmapper.generated.sendquestioncontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.sendquestioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class QuestionSentDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuestionSentDTO.class);
        QuestionSentDTO questionSentDTO1 = new QuestionSentDTO();
        questionSentDTO1.setId(1L);
        QuestionSentDTO questionSentDTO2 = new QuestionSentDTO();
        assertThat(questionSentDTO1).isNotEqualTo(questionSentDTO2);
        questionSentDTO2.setId(questionSentDTO1.getId());
        assertThat(questionSentDTO1).isEqualTo(questionSentDTO2);
        questionSentDTO2.setId(2L);
        assertThat(questionSentDTO1).isNotEqualTo(questionSentDTO2);
        questionSentDTO1.setId(null);
        assertThat(questionSentDTO1).isNotEqualTo(questionSentDTO2);
    }
}
