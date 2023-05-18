package org.contextmapper.generated.sendquestioncontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.sendquestioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CreateQuestionDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CreateQuestionDTO.class);
        CreateQuestionDTO createQuestionDTO1 = new CreateQuestionDTO();
        createQuestionDTO1.setId(1L);
        CreateQuestionDTO createQuestionDTO2 = new CreateQuestionDTO();
        assertThat(createQuestionDTO1).isNotEqualTo(createQuestionDTO2);
        createQuestionDTO2.setId(createQuestionDTO1.getId());
        assertThat(createQuestionDTO1).isEqualTo(createQuestionDTO2);
        createQuestionDTO2.setId(2L);
        assertThat(createQuestionDTO1).isNotEqualTo(createQuestionDTO2);
        createQuestionDTO1.setId(null);
        assertThat(createQuestionDTO1).isNotEqualTo(createQuestionDTO2);
    }
}
