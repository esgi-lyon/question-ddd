package org.contextmapper.generated.skillcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.skillcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TagInfosTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TagInfos.class);
        TagInfos tagInfos1 = new TagInfos();
        tagInfos1.setId(1L);
        TagInfos tagInfos2 = new TagInfos();
        tagInfos2.setId(tagInfos1.getId());
        assertThat(tagInfos1).isEqualTo(tagInfos2);
        tagInfos2.setId(2L);
        assertThat(tagInfos1).isNotEqualTo(tagInfos2);
        tagInfos1.setId(null);
        assertThat(tagInfos1).isNotEqualTo(tagInfos2);
    }
}
