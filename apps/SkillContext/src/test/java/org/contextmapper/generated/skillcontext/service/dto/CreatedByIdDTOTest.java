package org.contextmapper.generated.skillcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.skillcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CreatedByIdDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CreatedByIdDTO.class);
        CreatedByIdDTO createdByIdDTO1 = new CreatedByIdDTO();
        createdByIdDTO1.setId(1L);
        CreatedByIdDTO createdByIdDTO2 = new CreatedByIdDTO();
        assertThat(createdByIdDTO1).isNotEqualTo(createdByIdDTO2);
        createdByIdDTO2.setId(createdByIdDTO1.getId());
        assertThat(createdByIdDTO1).isEqualTo(createdByIdDTO2);
        createdByIdDTO2.setId(2L);
        assertThat(createdByIdDTO1).isNotEqualTo(createdByIdDTO2);
        createdByIdDTO1.setId(null);
        assertThat(createdByIdDTO1).isNotEqualTo(createdByIdDTO2);
    }
}
