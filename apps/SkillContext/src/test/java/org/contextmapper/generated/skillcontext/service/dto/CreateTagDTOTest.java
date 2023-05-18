package org.contextmapper.generated.skillcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.skillcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CreateTagDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CreateTagDTO.class);
        CreateTagDTO createTagDTO1 = new CreateTagDTO();
        createTagDTO1.setId(1L);
        CreateTagDTO createTagDTO2 = new CreateTagDTO();
        assertThat(createTagDTO1).isNotEqualTo(createTagDTO2);
        createTagDTO2.setId(createTagDTO1.getId());
        assertThat(createTagDTO1).isEqualTo(createTagDTO2);
        createTagDTO2.setId(2L);
        assertThat(createTagDTO1).isNotEqualTo(createTagDTO2);
        createTagDTO1.setId(null);
        assertThat(createTagDTO1).isNotEqualTo(createTagDTO2);
    }
}
