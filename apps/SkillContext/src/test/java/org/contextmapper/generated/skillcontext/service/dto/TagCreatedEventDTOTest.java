package org.contextmapper.generated.skillcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.skillcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TagCreatedEventDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TagCreatedEventDTO.class);
        TagCreatedEventDTO tagCreatedEventDTO1 = new TagCreatedEventDTO();
        tagCreatedEventDTO1.setId(1L);
        TagCreatedEventDTO tagCreatedEventDTO2 = new TagCreatedEventDTO();
        assertThat(tagCreatedEventDTO1).isNotEqualTo(tagCreatedEventDTO2);
        tagCreatedEventDTO2.setId(tagCreatedEventDTO1.getId());
        assertThat(tagCreatedEventDTO1).isEqualTo(tagCreatedEventDTO2);
        tagCreatedEventDTO2.setId(2L);
        assertThat(tagCreatedEventDTO1).isNotEqualTo(tagCreatedEventDTO2);
        tagCreatedEventDTO1.setId(null);
        assertThat(tagCreatedEventDTO1).isNotEqualTo(tagCreatedEventDTO2);
    }
}
