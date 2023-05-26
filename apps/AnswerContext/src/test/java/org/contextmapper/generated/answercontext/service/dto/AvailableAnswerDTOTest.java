package org.contextmapper.generated.answercontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.answercontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AvailableAnswerDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AvailableAnswerDTO.class);
        AvailableAnswerDTO availableAnswerDTO1 = new AvailableAnswerDTO();
        availableAnswerDTO1.setId(1L);
        AvailableAnswerDTO availableAnswerDTO2 = new AvailableAnswerDTO();
        assertThat(availableAnswerDTO1).isNotEqualTo(availableAnswerDTO2);
        availableAnswerDTO2.setId(availableAnswerDTO1.getId());
        assertThat(availableAnswerDTO1).isEqualTo(availableAnswerDTO2);
        availableAnswerDTO2.setId(2L);
        assertThat(availableAnswerDTO1).isNotEqualTo(availableAnswerDTO2);
        availableAnswerDTO1.setId(null);
        assertThat(availableAnswerDTO1).isNotEqualTo(availableAnswerDTO2);
    }
}
