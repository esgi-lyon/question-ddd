package org.contextmapper.generated.sendquestioncontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.sendquestioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class NotifiedQuestionEventDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NotifiedQuestionEventDTO.class);
        NotifiedQuestionEventDTO notifiedQuestionEventDTO1 = new NotifiedQuestionEventDTO();
        notifiedQuestionEventDTO1.setId(1L);
        NotifiedQuestionEventDTO notifiedQuestionEventDTO2 = new NotifiedQuestionEventDTO();
        assertThat(notifiedQuestionEventDTO1).isNotEqualTo(notifiedQuestionEventDTO2);
        notifiedQuestionEventDTO2.setId(notifiedQuestionEventDTO1.getId());
        assertThat(notifiedQuestionEventDTO1).isEqualTo(notifiedQuestionEventDTO2);
        notifiedQuestionEventDTO2.setId(2L);
        assertThat(notifiedQuestionEventDTO1).isNotEqualTo(notifiedQuestionEventDTO2);
        notifiedQuestionEventDTO1.setId(null);
        assertThat(notifiedQuestionEventDTO1).isNotEqualTo(notifiedQuestionEventDTO2);
    }
}
