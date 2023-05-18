package org.contextmapper.generated.sendquestioncontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.sendquestioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class QuestionSentTagInfosDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuestionSentTagInfosDTO.class);
        QuestionSentTagInfosDTO questionSentTagInfosDTO1 = new QuestionSentTagInfosDTO();
        questionSentTagInfosDTO1.setId(1L);
        QuestionSentTagInfosDTO questionSentTagInfosDTO2 = new QuestionSentTagInfosDTO();
        assertThat(questionSentTagInfosDTO1).isNotEqualTo(questionSentTagInfosDTO2);
        questionSentTagInfosDTO2.setId(questionSentTagInfosDTO1.getId());
        assertThat(questionSentTagInfosDTO1).isEqualTo(questionSentTagInfosDTO2);
        questionSentTagInfosDTO2.setId(2L);
        assertThat(questionSentTagInfosDTO1).isNotEqualTo(questionSentTagInfosDTO2);
        questionSentTagInfosDTO1.setId(null);
        assertThat(questionSentTagInfosDTO1).isNotEqualTo(questionSentTagInfosDTO2);
    }
}
