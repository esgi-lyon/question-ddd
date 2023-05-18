package org.contextmapper.generated.skillcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.skillcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CreateCategoryDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CreateCategoryDTO.class);
        CreateCategoryDTO createCategoryDTO1 = new CreateCategoryDTO();
        createCategoryDTO1.setId(1L);
        CreateCategoryDTO createCategoryDTO2 = new CreateCategoryDTO();
        assertThat(createCategoryDTO1).isNotEqualTo(createCategoryDTO2);
        createCategoryDTO2.setId(createCategoryDTO1.getId());
        assertThat(createCategoryDTO1).isEqualTo(createCategoryDTO2);
        createCategoryDTO2.setId(2L);
        assertThat(createCategoryDTO1).isNotEqualTo(createCategoryDTO2);
        createCategoryDTO1.setId(null);
        assertThat(createCategoryDTO1).isNotEqualTo(createCategoryDTO2);
    }
}
