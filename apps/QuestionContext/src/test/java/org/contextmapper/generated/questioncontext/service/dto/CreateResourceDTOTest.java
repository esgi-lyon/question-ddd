package org.contextmapper.generated.questioncontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.questioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CreateResourceDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CreateResourceDTO.class);
        CreateResourceDTO createResourceDTO1 = new CreateResourceDTO();
        createResourceDTO1.setId(1L);
        CreateResourceDTO createResourceDTO2 = new CreateResourceDTO();
        assertThat(createResourceDTO1).isNotEqualTo(createResourceDTO2);
        createResourceDTO2.setId(createResourceDTO1.getId());
        assertThat(createResourceDTO1).isEqualTo(createResourceDTO2);
        createResourceDTO2.setId(2L);
        assertThat(createResourceDTO1).isNotEqualTo(createResourceDTO2);
        createResourceDTO1.setId(null);
        assertThat(createResourceDTO1).isNotEqualTo(createResourceDTO2);
    }
}
