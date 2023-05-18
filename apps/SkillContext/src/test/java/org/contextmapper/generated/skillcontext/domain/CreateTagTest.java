package org.contextmapper.generated.skillcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.skillcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CreateTagTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CreateTag.class);
        CreateTag createTag1 = new CreateTag();
        createTag1.setId(1L);
        CreateTag createTag2 = new CreateTag();
        createTag2.setId(createTag1.getId());
        assertThat(createTag1).isEqualTo(createTag2);
        createTag2.setId(2L);
        assertThat(createTag1).isNotEqualTo(createTag2);
        createTag1.setId(null);
        assertThat(createTag1).isNotEqualTo(createTag2);
    }
}
