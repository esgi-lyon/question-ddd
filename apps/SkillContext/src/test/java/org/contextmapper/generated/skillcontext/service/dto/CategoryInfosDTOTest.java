package org.contextmapper.generated.skillcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.skillcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CategoryInfosDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CategoryInfosDTO.class);
        CategoryInfosDTO categoryInfosDTO1 = new CategoryInfosDTO();
        categoryInfosDTO1.setId(1L);
        CategoryInfosDTO categoryInfosDTO2 = new CategoryInfosDTO();
        assertThat(categoryInfosDTO1).isNotEqualTo(categoryInfosDTO2);
        categoryInfosDTO2.setId(categoryInfosDTO1.getId());
        assertThat(categoryInfosDTO1).isEqualTo(categoryInfosDTO2);
        categoryInfosDTO2.setId(2L);
        assertThat(categoryInfosDTO1).isNotEqualTo(categoryInfosDTO2);
        categoryInfosDTO1.setId(null);
        assertThat(categoryInfosDTO1).isNotEqualTo(categoryInfosDTO2);
    }
}
