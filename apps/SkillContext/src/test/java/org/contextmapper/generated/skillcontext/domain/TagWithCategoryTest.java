package org.contextmapper.generated.skillcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.skillcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TagWithCategoryTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TagWithCategory.class);
        TagWithCategory tagWithCategory1 = new TagWithCategory();
        tagWithCategory1.setId(1L);
        TagWithCategory tagWithCategory2 = new TagWithCategory();
        tagWithCategory2.setId(tagWithCategory1.getId());
        assertThat(tagWithCategory1).isEqualTo(tagWithCategory2);
        tagWithCategory2.setId(2L);
        assertThat(tagWithCategory1).isNotEqualTo(tagWithCategory2);
        tagWithCategory1.setId(null);
        assertThat(tagWithCategory1).isNotEqualTo(tagWithCategory2);
    }
}
