package org.contextmapper.generated.answercontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.answercontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TagChoicesListedTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TagChoicesListed.class);
        TagChoicesListed tagChoicesListed1 = new TagChoicesListed();
        tagChoicesListed1.setId(1L);
        TagChoicesListed tagChoicesListed2 = new TagChoicesListed();
        tagChoicesListed2.setId(tagChoicesListed1.getId());
        assertThat(tagChoicesListed1).isEqualTo(tagChoicesListed2);
        tagChoicesListed2.setId(2L);
        assertThat(tagChoicesListed1).isNotEqualTo(tagChoicesListed2);
        tagChoicesListed1.setId(null);
        assertThat(tagChoicesListed1).isNotEqualTo(tagChoicesListed2);
    }
}
