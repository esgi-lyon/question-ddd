package org.contextmapper.generated.answercontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.answercontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AnsweredTagDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AnsweredTagDTO.class);
        AnsweredTagDTO answeredTagDTO1 = new AnsweredTagDTO();
        answeredTagDTO1.setId(1L);
        AnsweredTagDTO answeredTagDTO2 = new AnsweredTagDTO();
        assertThat(answeredTagDTO1).isNotEqualTo(answeredTagDTO2);
        answeredTagDTO2.setId(answeredTagDTO1.getId());
        assertThat(answeredTagDTO1).isEqualTo(answeredTagDTO2);
        answeredTagDTO2.setId(2L);
        assertThat(answeredTagDTO1).isNotEqualTo(answeredTagDTO2);
        answeredTagDTO1.setId(null);
        assertThat(answeredTagDTO1).isNotEqualTo(answeredTagDTO2);
    }
}
