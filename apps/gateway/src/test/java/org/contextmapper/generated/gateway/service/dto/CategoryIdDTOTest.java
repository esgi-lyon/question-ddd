package org.contextmapper.generated.gateway.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CategoryIdDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CategoryIdDTO.class);
        CategoryIdDTO categoryIdDTO1 = new CategoryIdDTO();
        categoryIdDTO1.setId(1L);
        CategoryIdDTO categoryIdDTO2 = new CategoryIdDTO();
        assertThat(categoryIdDTO1).isNotEqualTo(categoryIdDTO2);
        categoryIdDTO2.setId(categoryIdDTO1.getId());
        assertThat(categoryIdDTO1).isEqualTo(categoryIdDTO2);
        categoryIdDTO2.setId(2L);
        assertThat(categoryIdDTO1).isNotEqualTo(categoryIdDTO2);
        categoryIdDTO1.setId(null);
        assertThat(categoryIdDTO1).isNotEqualTo(categoryIdDTO2);
    }
}
