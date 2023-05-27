package org.contextmapper.generated.skillcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.skillcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CreateTagCommandDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CreateTagCommandDTO.class);
        CreateTagCommandDTO createTagCommandDTO1 = new CreateTagCommandDTO();
        createTagCommandDTO1.setId(1L);
        CreateTagCommandDTO createTagCommandDTO2 = new CreateTagCommandDTO();
        assertThat(createTagCommandDTO1).isNotEqualTo(createTagCommandDTO2);
        createTagCommandDTO2.setId(createTagCommandDTO1.getId());
        assertThat(createTagCommandDTO1).isEqualTo(createTagCommandDTO2);
        createTagCommandDTO2.setId(2L);
        assertThat(createTagCommandDTO1).isNotEqualTo(createTagCommandDTO2);
        createTagCommandDTO1.setId(null);
        assertThat(createTagCommandDTO1).isNotEqualTo(createTagCommandDTO2);
    }
}
