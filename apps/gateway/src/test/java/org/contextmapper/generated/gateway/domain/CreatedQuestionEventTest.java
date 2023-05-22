package org.contextmapper.generated.gateway.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CreatedQuestionEventTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CreatedQuestionEvent.class);
        CreatedQuestionEvent createdQuestionEvent1 = new CreatedQuestionEvent();
        createdQuestionEvent1.setId(1L);
        CreatedQuestionEvent createdQuestionEvent2 = new CreatedQuestionEvent();
        createdQuestionEvent2.setId(createdQuestionEvent1.getId());
        assertThat(createdQuestionEvent1).isEqualTo(createdQuestionEvent2);
        createdQuestionEvent2.setId(2L);
        assertThat(createdQuestionEvent1).isNotEqualTo(createdQuestionEvent2);
        createdQuestionEvent1.setId(null);
        assertThat(createdQuestionEvent1).isNotEqualTo(createdQuestionEvent2);
    }
}
