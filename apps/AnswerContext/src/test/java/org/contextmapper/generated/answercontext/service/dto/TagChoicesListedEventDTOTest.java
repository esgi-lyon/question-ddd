package org.contextmapper.generated.answercontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.answercontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TagChoicesListedEventDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TagChoicesListedEventDTO.class);
        TagChoicesListedEventDTO tagChoicesListedEventDTO1 = new TagChoicesListedEventDTO();
        tagChoicesListedEventDTO1.setId(1L);
        TagChoicesListedEventDTO tagChoicesListedEventDTO2 = new TagChoicesListedEventDTO();
        assertThat(tagChoicesListedEventDTO1).isNotEqualTo(tagChoicesListedEventDTO2);
        tagChoicesListedEventDTO2.setId(tagChoicesListedEventDTO1.getId());
        assertThat(tagChoicesListedEventDTO1).isEqualTo(tagChoicesListedEventDTO2);
        tagChoicesListedEventDTO2.setId(2L);
        assertThat(tagChoicesListedEventDTO1).isNotEqualTo(tagChoicesListedEventDTO2);
        tagChoicesListedEventDTO1.setId(null);
        assertThat(tagChoicesListedEventDTO1).isNotEqualTo(tagChoicesListedEventDTO2);
    }
}
