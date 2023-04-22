package org.contextmapper.generated.skillcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.skillcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TagCreatedEventTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TagCreatedEvent.class);
        TagCreatedEvent tagCreatedEvent1 = new TagCreatedEvent();
        tagCreatedEvent1.setId(1L);
        TagCreatedEvent tagCreatedEvent2 = new TagCreatedEvent();
        tagCreatedEvent2.setId(tagCreatedEvent1.getId());
        assertThat(tagCreatedEvent1).isEqualTo(tagCreatedEvent2);
        tagCreatedEvent2.setId(2L);
        assertThat(tagCreatedEvent1).isNotEqualTo(tagCreatedEvent2);
        tagCreatedEvent1.setId(null);
        assertThat(tagCreatedEvent1).isNotEqualTo(tagCreatedEvent2);
    }
}
