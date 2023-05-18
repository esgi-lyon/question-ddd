package org.contextmapper.generated.answercontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.answercontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TagChoicesListDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TagChoicesListDTO.class);
        TagChoicesListDTO tagChoicesListDTO1 = new TagChoicesListDTO();
        tagChoicesListDTO1.setId(1L);
        TagChoicesListDTO tagChoicesListDTO2 = new TagChoicesListDTO();
        assertThat(tagChoicesListDTO1).isNotEqualTo(tagChoicesListDTO2);
        tagChoicesListDTO2.setId(tagChoicesListDTO1.getId());
        assertThat(tagChoicesListDTO1).isEqualTo(tagChoicesListDTO2);
        tagChoicesListDTO2.setId(2L);
        assertThat(tagChoicesListDTO1).isNotEqualTo(tagChoicesListDTO2);
        tagChoicesListDTO1.setId(null);
        assertThat(tagChoicesListDTO1).isNotEqualTo(tagChoicesListDTO2);
    }
}
