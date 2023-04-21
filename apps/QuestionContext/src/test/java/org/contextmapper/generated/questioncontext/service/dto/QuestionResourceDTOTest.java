package org.contextmapper.generated.questioncontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.questioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class QuestionResourceDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuestionResourceDTO.class);
        QuestionResourceDTO questionResourceDTO1 = new QuestionResourceDTO();
        questionResourceDTO1.setId(1L);
        QuestionResourceDTO questionResourceDTO2 = new QuestionResourceDTO();
        assertThat(questionResourceDTO1).isNotEqualTo(questionResourceDTO2);
        questionResourceDTO2.setId(questionResourceDTO1.getId());
        assertThat(questionResourceDTO1).isEqualTo(questionResourceDTO2);
        questionResourceDTO2.setId(2L);
        assertThat(questionResourceDTO1).isNotEqualTo(questionResourceDTO2);
        questionResourceDTO1.setId(null);
        assertThat(questionResourceDTO1).isNotEqualTo(questionResourceDTO2);
    }
}
