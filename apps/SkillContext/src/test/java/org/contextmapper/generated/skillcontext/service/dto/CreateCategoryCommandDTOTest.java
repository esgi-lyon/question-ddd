package org.contextmapper.generated.skillcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.skillcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CreateCategoryCommandDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CreateCategoryCommandDTO.class);
        CreateCategoryCommandDTO createCategoryCommandDTO1 = new CreateCategoryCommandDTO();
        createCategoryCommandDTO1.setId(1L);
        CreateCategoryCommandDTO createCategoryCommandDTO2 = new CreateCategoryCommandDTO();
        assertThat(createCategoryCommandDTO1).isNotEqualTo(createCategoryCommandDTO2);
        createCategoryCommandDTO2.setId(createCategoryCommandDTO1.getId());
        assertThat(createCategoryCommandDTO1).isEqualTo(createCategoryCommandDTO2);
        createCategoryCommandDTO2.setId(2L);
        assertThat(createCategoryCommandDTO1).isNotEqualTo(createCategoryCommandDTO2);
        createCategoryCommandDTO1.setId(null);
        assertThat(createCategoryCommandDTO1).isNotEqualTo(createCategoryCommandDTO2);
    }
}
