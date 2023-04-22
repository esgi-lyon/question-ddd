package org.contextmapper.generated.skillcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.skillcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CategoryCreatedEventDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CategoryCreatedEventDTO.class);
        CategoryCreatedEventDTO categoryCreatedEventDTO1 = new CategoryCreatedEventDTO();
        categoryCreatedEventDTO1.setId(1L);
        CategoryCreatedEventDTO categoryCreatedEventDTO2 = new CategoryCreatedEventDTO();
        assertThat(categoryCreatedEventDTO1).isNotEqualTo(categoryCreatedEventDTO2);
        categoryCreatedEventDTO2.setId(categoryCreatedEventDTO1.getId());
        assertThat(categoryCreatedEventDTO1).isEqualTo(categoryCreatedEventDTO2);
        categoryCreatedEventDTO2.setId(2L);
        assertThat(categoryCreatedEventDTO1).isNotEqualTo(categoryCreatedEventDTO2);
        categoryCreatedEventDTO1.setId(null);
        assertThat(categoryCreatedEventDTO1).isNotEqualTo(categoryCreatedEventDTO2);
    }
}
