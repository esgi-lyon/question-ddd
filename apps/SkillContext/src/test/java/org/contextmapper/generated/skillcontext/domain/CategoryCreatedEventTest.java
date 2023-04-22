package org.contextmapper.generated.skillcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.skillcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CategoryCreatedEventTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CategoryCreatedEvent.class);
        CategoryCreatedEvent categoryCreatedEvent1 = new CategoryCreatedEvent();
        categoryCreatedEvent1.setId(1L);
        CategoryCreatedEvent categoryCreatedEvent2 = new CategoryCreatedEvent();
        categoryCreatedEvent2.setId(categoryCreatedEvent1.getId());
        assertThat(categoryCreatedEvent1).isEqualTo(categoryCreatedEvent2);
        categoryCreatedEvent2.setId(2L);
        assertThat(categoryCreatedEvent1).isNotEqualTo(categoryCreatedEvent2);
        categoryCreatedEvent1.setId(null);
        assertThat(categoryCreatedEvent1).isNotEqualTo(categoryCreatedEvent2);
    }
}
