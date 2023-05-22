package org.contextmapper.generated.gateway.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CreatedQuestionEventDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CreatedQuestionEventDTO.class);
        CreatedQuestionEventDTO createdQuestionEventDTO1 = new CreatedQuestionEventDTO();
        createdQuestionEventDTO1.setId(1L);
        CreatedQuestionEventDTO createdQuestionEventDTO2 = new CreatedQuestionEventDTO();
        assertThat(createdQuestionEventDTO1).isNotEqualTo(createdQuestionEventDTO2);
        createdQuestionEventDTO2.setId(createdQuestionEventDTO1.getId());
        assertThat(createdQuestionEventDTO1).isEqualTo(createdQuestionEventDTO2);
        createdQuestionEventDTO2.setId(2L);
        assertThat(createdQuestionEventDTO1).isNotEqualTo(createdQuestionEventDTO2);
        createdQuestionEventDTO1.setId(null);
        assertThat(createdQuestionEventDTO1).isNotEqualTo(createdQuestionEventDTO2);
    }
}
