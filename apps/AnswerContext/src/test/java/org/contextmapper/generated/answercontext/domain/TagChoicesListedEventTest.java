package org.contextmapper.generated.answercontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.answercontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TagChoicesListedEventTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TagChoicesListedEvent.class);
        TagChoicesListedEvent tagChoicesListedEvent1 = new TagChoicesListedEvent();
        tagChoicesListedEvent1.setId(1L);
        TagChoicesListedEvent tagChoicesListedEvent2 = new TagChoicesListedEvent();
        tagChoicesListedEvent2.setId(tagChoicesListedEvent1.getId());
        assertThat(tagChoicesListedEvent1).isEqualTo(tagChoicesListedEvent2);
        tagChoicesListedEvent2.setId(2L);
        assertThat(tagChoicesListedEvent1).isNotEqualTo(tagChoicesListedEvent2);
        tagChoicesListedEvent1.setId(null);
        assertThat(tagChoicesListedEvent1).isNotEqualTo(tagChoicesListedEvent2);
    }
}
