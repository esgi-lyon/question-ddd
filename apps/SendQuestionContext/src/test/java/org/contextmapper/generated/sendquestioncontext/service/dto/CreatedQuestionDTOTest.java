package org.contextmapper.generated.sendquestioncontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.sendquestioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CreatedQuestionDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CreatedQuestionDTO.class);
        CreatedQuestionDTO createdQuestionDTO1 = new CreatedQuestionDTO();
        createdQuestionDTO1.setId(1L);
        CreatedQuestionDTO createdQuestionDTO2 = new CreatedQuestionDTO();
        assertThat(createdQuestionDTO1).isNotEqualTo(createdQuestionDTO2);
        createdQuestionDTO2.setId(createdQuestionDTO1.getId());
        assertThat(createdQuestionDTO1).isEqualTo(createdQuestionDTO2);
        createdQuestionDTO2.setId(2L);
        assertThat(createdQuestionDTO1).isNotEqualTo(createdQuestionDTO2);
        createdQuestionDTO1.setId(null);
        assertThat(createdQuestionDTO1).isNotEqualTo(createdQuestionDTO2);
    }
}
