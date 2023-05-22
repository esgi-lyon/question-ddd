package org.contextmapper.generated.gateway.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class NotifiedQuestionEventTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NotifiedQuestionEvent.class);
        NotifiedQuestionEvent notifiedQuestionEvent1 = new NotifiedQuestionEvent();
        notifiedQuestionEvent1.setId(1L);
        NotifiedQuestionEvent notifiedQuestionEvent2 = new NotifiedQuestionEvent();
        notifiedQuestionEvent2.setId(notifiedQuestionEvent1.getId());
        assertThat(notifiedQuestionEvent1).isEqualTo(notifiedQuestionEvent2);
        notifiedQuestionEvent2.setId(2L);
        assertThat(notifiedQuestionEvent1).isNotEqualTo(notifiedQuestionEvent2);
        notifiedQuestionEvent1.setId(null);
        assertThat(notifiedQuestionEvent1).isNotEqualTo(notifiedQuestionEvent2);
    }
}
