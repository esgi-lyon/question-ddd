package org.contextmapper.generated.sendquestioncontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.sendquestioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class NotifiedQuestionDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NotifiedQuestionDTO.class);
        NotifiedQuestionDTO notifiedQuestionDTO1 = new NotifiedQuestionDTO();
        notifiedQuestionDTO1.setId(1L);
        NotifiedQuestionDTO notifiedQuestionDTO2 = new NotifiedQuestionDTO();
        assertThat(notifiedQuestionDTO1).isNotEqualTo(notifiedQuestionDTO2);
        notifiedQuestionDTO2.setId(notifiedQuestionDTO1.getId());
        assertThat(notifiedQuestionDTO1).isEqualTo(notifiedQuestionDTO2);
        notifiedQuestionDTO2.setId(2L);
        assertThat(notifiedQuestionDTO1).isNotEqualTo(notifiedQuestionDTO2);
        notifiedQuestionDTO1.setId(null);
        assertThat(notifiedQuestionDTO1).isNotEqualTo(notifiedQuestionDTO2);
    }
}
