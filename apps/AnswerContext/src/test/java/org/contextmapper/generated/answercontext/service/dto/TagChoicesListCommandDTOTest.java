package org.contextmapper.generated.answercontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.answercontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TagChoicesListCommandDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TagChoicesListCommandDTO.class);
        TagChoicesListCommandDTO tagChoicesListCommandDTO1 = new TagChoicesListCommandDTO();
        tagChoicesListCommandDTO1.setId(1L);
        TagChoicesListCommandDTO tagChoicesListCommandDTO2 = new TagChoicesListCommandDTO();
        assertThat(tagChoicesListCommandDTO1).isNotEqualTo(tagChoicesListCommandDTO2);
        tagChoicesListCommandDTO2.setId(tagChoicesListCommandDTO1.getId());
        assertThat(tagChoicesListCommandDTO1).isEqualTo(tagChoicesListCommandDTO2);
        tagChoicesListCommandDTO2.setId(2L);
        assertThat(tagChoicesListCommandDTO1).isNotEqualTo(tagChoicesListCommandDTO2);
        tagChoicesListCommandDTO1.setId(null);
        assertThat(tagChoicesListCommandDTO1).isNotEqualTo(tagChoicesListCommandDTO2);
    }
}
