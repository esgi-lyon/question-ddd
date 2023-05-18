package org.contextmapper.generated.skillcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.skillcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TagCreatedDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TagCreatedDTO.class);
        TagCreatedDTO tagCreatedDTO1 = new TagCreatedDTO();
        tagCreatedDTO1.setId(1L);
        TagCreatedDTO tagCreatedDTO2 = new TagCreatedDTO();
        assertThat(tagCreatedDTO1).isNotEqualTo(tagCreatedDTO2);
        tagCreatedDTO2.setId(tagCreatedDTO1.getId());
        assertThat(tagCreatedDTO1).isEqualTo(tagCreatedDTO2);
        tagCreatedDTO2.setId(2L);
        assertThat(tagCreatedDTO1).isNotEqualTo(tagCreatedDTO2);
        tagCreatedDTO1.setId(null);
        assertThat(tagCreatedDTO1).isNotEqualTo(tagCreatedDTO2);
    }
}
