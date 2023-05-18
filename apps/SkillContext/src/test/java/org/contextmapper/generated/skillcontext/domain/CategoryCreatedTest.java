package org.contextmapper.generated.skillcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.skillcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CategoryCreatedTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CategoryCreated.class);
        CategoryCreated categoryCreated1 = new CategoryCreated();
        categoryCreated1.setId(1L);
        CategoryCreated categoryCreated2 = new CategoryCreated();
        categoryCreated2.setId(categoryCreated1.getId());
        assertThat(categoryCreated1).isEqualTo(categoryCreated2);
        categoryCreated2.setId(2L);
        assertThat(categoryCreated1).isNotEqualTo(categoryCreated2);
        categoryCreated1.setId(null);
        assertThat(categoryCreated1).isNotEqualTo(categoryCreated2);
    }
}
