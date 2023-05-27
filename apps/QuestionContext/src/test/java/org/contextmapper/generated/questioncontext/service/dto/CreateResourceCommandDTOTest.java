package org.contextmapper.generated.questioncontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.questioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CreateResourceCommandDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CreateResourceCommandDTO.class);
        CreateResourceCommandDTO createResourceCommandDTO1 = new CreateResourceCommandDTO();
        createResourceCommandDTO1.setId(1L);
        CreateResourceCommandDTO createResourceCommandDTO2 = new CreateResourceCommandDTO();
        assertThat(createResourceCommandDTO1).isNotEqualTo(createResourceCommandDTO2);
        createResourceCommandDTO2.setId(createResourceCommandDTO1.getId());
        assertThat(createResourceCommandDTO1).isEqualTo(createResourceCommandDTO2);
        createResourceCommandDTO2.setId(2L);
        assertThat(createResourceCommandDTO1).isNotEqualTo(createResourceCommandDTO2);
        createResourceCommandDTO1.setId(null);
        assertThat(createResourceCommandDTO1).isNotEqualTo(createResourceCommandDTO2);
    }
}
