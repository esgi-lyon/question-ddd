package org.contextmapper.generated.skillcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.skillcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TagIdTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TagId.class);
        TagId tagId1 = new TagId();
        tagId1.setId(1L);
        TagId tagId2 = new TagId();
        tagId2.setId(tagId1.getId());
        assertThat(tagId1).isEqualTo(tagId2);
        tagId2.setId(2L);
        assertThat(tagId1).isNotEqualTo(tagId2);
        tagId1.setId(null);
        assertThat(tagId1).isNotEqualTo(tagId2);
    }
}
