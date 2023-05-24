package org.contextmapper.generated.evaluationcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.evaluationcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class NewAnswerNotifiedEventDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NewAnswerNotifiedEventDTO.class);
        NewAnswerNotifiedEventDTO newAnswerNotifiedEventDTO1 = new NewAnswerNotifiedEventDTO();
        newAnswerNotifiedEventDTO1.setId(1L);
        NewAnswerNotifiedEventDTO newAnswerNotifiedEventDTO2 = new NewAnswerNotifiedEventDTO();
        assertThat(newAnswerNotifiedEventDTO1).isNotEqualTo(newAnswerNotifiedEventDTO2);
        newAnswerNotifiedEventDTO2.setId(newAnswerNotifiedEventDTO1.getId());
        assertThat(newAnswerNotifiedEventDTO1).isEqualTo(newAnswerNotifiedEventDTO2);
        newAnswerNotifiedEventDTO2.setId(2L);
        assertThat(newAnswerNotifiedEventDTO1).isNotEqualTo(newAnswerNotifiedEventDTO2);
        newAnswerNotifiedEventDTO1.setId(null);
        assertThat(newAnswerNotifiedEventDTO1).isNotEqualTo(newAnswerNotifiedEventDTO2);
    }
}
