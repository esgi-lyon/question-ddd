package org.contextmapper.generated.skillcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.skillcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TagInfosDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TagInfosDTO.class);
        TagInfosDTO tagInfosDTO1 = new TagInfosDTO();
        tagInfosDTO1.setId(1L);
        TagInfosDTO tagInfosDTO2 = new TagInfosDTO();
        assertThat(tagInfosDTO1).isNotEqualTo(tagInfosDTO2);
        tagInfosDTO2.setId(tagInfosDTO1.getId());
        assertThat(tagInfosDTO1).isEqualTo(tagInfosDTO2);
        tagInfosDTO2.setId(2L);
        assertThat(tagInfosDTO1).isNotEqualTo(tagInfosDTO2);
        tagInfosDTO1.setId(null);
        assertThat(tagInfosDTO1).isNotEqualTo(tagInfosDTO2);
    }
}
