package org.contextmapper.generated.skillcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.skillcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CategoryInfosTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CategoryInfos.class);
        CategoryInfos categoryInfos1 = new CategoryInfos();
        categoryInfos1.setId(1L);
        CategoryInfos categoryInfos2 = new CategoryInfos();
        categoryInfos2.setId(categoryInfos1.getId());
        assertThat(categoryInfos1).isEqualTo(categoryInfos2);
        categoryInfos2.setId(2L);
        assertThat(categoryInfos1).isNotEqualTo(categoryInfos2);
        categoryInfos1.setId(null);
        assertThat(categoryInfos1).isNotEqualTo(categoryInfos2);
    }
}
