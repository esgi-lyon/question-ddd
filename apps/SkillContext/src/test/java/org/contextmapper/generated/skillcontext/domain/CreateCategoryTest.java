package org.contextmapper.generated.skillcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.skillcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CreateCategoryTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CreateCategory.class);
        CreateCategory createCategory1 = new CreateCategory();
        createCategory1.setId(1L);
        CreateCategory createCategory2 = new CreateCategory();
        createCategory2.setId(createCategory1.getId());
        assertThat(createCategory1).isEqualTo(createCategory2);
        createCategory2.setId(2L);
        assertThat(createCategory1).isNotEqualTo(createCategory2);
        createCategory1.setId(null);
        assertThat(createCategory1).isNotEqualTo(createCategory2);
    }
}
