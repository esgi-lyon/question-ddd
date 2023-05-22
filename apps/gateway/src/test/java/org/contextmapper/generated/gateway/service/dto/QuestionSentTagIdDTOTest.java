package org.contextmapper.generated.gateway.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class QuestionSentTagIdDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuestionSentTagIdDTO.class);
        QuestionSentTagIdDTO questionSentTagIdDTO1 = new QuestionSentTagIdDTO();
        questionSentTagIdDTO1.setId(1L);
        QuestionSentTagIdDTO questionSentTagIdDTO2 = new QuestionSentTagIdDTO();
        assertThat(questionSentTagIdDTO1).isNotEqualTo(questionSentTagIdDTO2);
        questionSentTagIdDTO2.setId(questionSentTagIdDTO1.getId());
        assertThat(questionSentTagIdDTO1).isEqualTo(questionSentTagIdDTO2);
        questionSentTagIdDTO2.setId(2L);
        assertThat(questionSentTagIdDTO1).isNotEqualTo(questionSentTagIdDTO2);
        questionSentTagIdDTO1.setId(null);
        assertThat(questionSentTagIdDTO1).isNotEqualTo(questionSentTagIdDTO2);
    }
}
