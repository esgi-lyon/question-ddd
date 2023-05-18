package org.contextmapper.generated.sendquestioncontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.sendquestioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class QuestionSentQuestionResourceTagIdDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuestionSentQuestionResourceTagIdDTO.class);
        QuestionSentQuestionResourceTagIdDTO questionSentQuestionResourceTagIdDTO1 = new QuestionSentQuestionResourceTagIdDTO();
        questionSentQuestionResourceTagIdDTO1.setId(1L);
        QuestionSentQuestionResourceTagIdDTO questionSentQuestionResourceTagIdDTO2 = new QuestionSentQuestionResourceTagIdDTO();
        assertThat(questionSentQuestionResourceTagIdDTO1).isNotEqualTo(questionSentQuestionResourceTagIdDTO2);
        questionSentQuestionResourceTagIdDTO2.setId(questionSentQuestionResourceTagIdDTO1.getId());
        assertThat(questionSentQuestionResourceTagIdDTO1).isEqualTo(questionSentQuestionResourceTagIdDTO2);
        questionSentQuestionResourceTagIdDTO2.setId(2L);
        assertThat(questionSentQuestionResourceTagIdDTO1).isNotEqualTo(questionSentQuestionResourceTagIdDTO2);
        questionSentQuestionResourceTagIdDTO1.setId(null);
        assertThat(questionSentQuestionResourceTagIdDTO1).isNotEqualTo(questionSentQuestionResourceTagIdDTO2);
    }
}
