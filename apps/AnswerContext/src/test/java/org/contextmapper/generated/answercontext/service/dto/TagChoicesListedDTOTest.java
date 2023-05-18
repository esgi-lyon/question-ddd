package org.contextmapper.generated.answercontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.answercontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TagChoicesListedDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TagChoicesListedDTO.class);
        TagChoicesListedDTO tagChoicesListedDTO1 = new TagChoicesListedDTO();
        tagChoicesListedDTO1.setId(1L);
        TagChoicesListedDTO tagChoicesListedDTO2 = new TagChoicesListedDTO();
        assertThat(tagChoicesListedDTO1).isNotEqualTo(tagChoicesListedDTO2);
        tagChoicesListedDTO2.setId(tagChoicesListedDTO1.getId());
        assertThat(tagChoicesListedDTO1).isEqualTo(tagChoicesListedDTO2);
        tagChoicesListedDTO2.setId(2L);
        assertThat(tagChoicesListedDTO1).isNotEqualTo(tagChoicesListedDTO2);
        tagChoicesListedDTO1.setId(null);
        assertThat(tagChoicesListedDTO1).isNotEqualTo(tagChoicesListedDTO2);
    }
}
