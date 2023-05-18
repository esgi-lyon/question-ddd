package org.contextmapper.generated.skillcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.skillcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CategoryCreatedDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CategoryCreatedDTO.class);
        CategoryCreatedDTO categoryCreatedDTO1 = new CategoryCreatedDTO();
        categoryCreatedDTO1.setId(1L);
        CategoryCreatedDTO categoryCreatedDTO2 = new CategoryCreatedDTO();
        assertThat(categoryCreatedDTO1).isNotEqualTo(categoryCreatedDTO2);
        categoryCreatedDTO2.setId(categoryCreatedDTO1.getId());
        assertThat(categoryCreatedDTO1).isEqualTo(categoryCreatedDTO2);
        categoryCreatedDTO2.setId(2L);
        assertThat(categoryCreatedDTO1).isNotEqualTo(categoryCreatedDTO2);
        categoryCreatedDTO1.setId(null);
        assertThat(categoryCreatedDTO1).isNotEqualTo(categoryCreatedDTO2);
    }
}
