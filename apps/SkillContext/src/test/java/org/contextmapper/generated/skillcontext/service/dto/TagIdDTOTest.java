package org.contextmapper.generated.skillcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.skillcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TagIdDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TagIdDTO.class);
        TagIdDTO tagIdDTO1 = new TagIdDTO();
        tagIdDTO1.setId(1L);
        TagIdDTO tagIdDTO2 = new TagIdDTO();
        assertThat(tagIdDTO1).isNotEqualTo(tagIdDTO2);
        tagIdDTO2.setId(tagIdDTO1.getId());
        assertThat(tagIdDTO1).isEqualTo(tagIdDTO2);
        tagIdDTO2.setId(2L);
        assertThat(tagIdDTO1).isNotEqualTo(tagIdDTO2);
        tagIdDTO1.setId(null);
        assertThat(tagIdDTO1).isNotEqualTo(tagIdDTO2);
    }
}
