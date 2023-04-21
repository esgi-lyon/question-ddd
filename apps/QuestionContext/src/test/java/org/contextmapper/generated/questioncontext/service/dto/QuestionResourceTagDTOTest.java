package org.contextmapper.generated.questioncontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.questioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class QuestionResourceTagDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuestionResourceTagDTO.class);
        QuestionResourceTagDTO questionResourceTagDTO1 = new QuestionResourceTagDTO();
        questionResourceTagDTO1.setId(1L);
        QuestionResourceTagDTO questionResourceTagDTO2 = new QuestionResourceTagDTO();
        assertThat(questionResourceTagDTO1).isNotEqualTo(questionResourceTagDTO2);
        questionResourceTagDTO2.setId(questionResourceTagDTO1.getId());
        assertThat(questionResourceTagDTO1).isEqualTo(questionResourceTagDTO2);
        questionResourceTagDTO2.setId(2L);
        assertThat(questionResourceTagDTO1).isNotEqualTo(questionResourceTagDTO2);
        questionResourceTagDTO1.setId(null);
        assertThat(questionResourceTagDTO1).isNotEqualTo(questionResourceTagDTO2);
    }
}
