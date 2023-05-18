package org.contextmapper.generated.questioncontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.questioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class QuestionResourceTagInfosDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuestionResourceTagInfosDTO.class);
        QuestionResourceTagInfosDTO questionResourceTagInfosDTO1 = new QuestionResourceTagInfosDTO();
        questionResourceTagInfosDTO1.setId(1L);
        QuestionResourceTagInfosDTO questionResourceTagInfosDTO2 = new QuestionResourceTagInfosDTO();
        assertThat(questionResourceTagInfosDTO1).isNotEqualTo(questionResourceTagInfosDTO2);
        questionResourceTagInfosDTO2.setId(questionResourceTagInfosDTO1.getId());
        assertThat(questionResourceTagInfosDTO1).isEqualTo(questionResourceTagInfosDTO2);
        questionResourceTagInfosDTO2.setId(2L);
        assertThat(questionResourceTagInfosDTO1).isNotEqualTo(questionResourceTagInfosDTO2);
        questionResourceTagInfosDTO1.setId(null);
        assertThat(questionResourceTagInfosDTO1).isNotEqualTo(questionResourceTagInfosDTO2);
    }
}
