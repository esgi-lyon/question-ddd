package org.contextmapper.generated.gateway.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CategoryIdTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CategoryId.class);
        CategoryId categoryId1 = new CategoryId();
        categoryId1.setId(1L);
        CategoryId categoryId2 = new CategoryId();
        categoryId2.setId(categoryId1.getId());
        assertThat(categoryId1).isEqualTo(categoryId2);
        categoryId2.setId(2L);
        assertThat(categoryId1).isNotEqualTo(categoryId2);
        categoryId1.setId(null);
        assertThat(categoryId1).isNotEqualTo(categoryId2);
    }
}
