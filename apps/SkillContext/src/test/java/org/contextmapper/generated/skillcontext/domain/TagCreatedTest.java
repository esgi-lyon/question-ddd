package org.contextmapper.generated.skillcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.skillcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TagCreatedTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TagCreated.class);
        TagCreated tagCreated1 = new TagCreated();
        tagCreated1.setId(1L);
        TagCreated tagCreated2 = new TagCreated();
        tagCreated2.setId(tagCreated1.getId());
        assertThat(tagCreated1).isEqualTo(tagCreated2);
        tagCreated2.setId(2L);
        assertThat(tagCreated1).isNotEqualTo(tagCreated2);
        tagCreated1.setId(null);
        assertThat(tagCreated1).isNotEqualTo(tagCreated2);
    }
}
