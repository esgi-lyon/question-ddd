package org.contextmapper.generated.skillcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.skillcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TagWithCategoryDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TagWithCategoryDTO.class);
        TagWithCategoryDTO tagWithCategoryDTO1 = new TagWithCategoryDTO();
        tagWithCategoryDTO1.setId(1L);
        TagWithCategoryDTO tagWithCategoryDTO2 = new TagWithCategoryDTO();
        assertThat(tagWithCategoryDTO1).isNotEqualTo(tagWithCategoryDTO2);
        tagWithCategoryDTO2.setId(tagWithCategoryDTO1.getId());
        assertThat(tagWithCategoryDTO1).isEqualTo(tagWithCategoryDTO2);
        tagWithCategoryDTO2.setId(2L);
        assertThat(tagWithCategoryDTO1).isNotEqualTo(tagWithCategoryDTO2);
        tagWithCategoryDTO1.setId(null);
        assertThat(tagWithCategoryDTO1).isNotEqualTo(tagWithCategoryDTO2);
    }
}
