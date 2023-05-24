package org.contextmapper.generated.evaluationcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.evaluationcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class NewAnswerNotifiedEventTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NewAnswerNotifiedEvent.class);
        NewAnswerNotifiedEvent newAnswerNotifiedEvent1 = new NewAnswerNotifiedEvent();
        newAnswerNotifiedEvent1.setId(1L);
        NewAnswerNotifiedEvent newAnswerNotifiedEvent2 = new NewAnswerNotifiedEvent();
        newAnswerNotifiedEvent2.setId(newAnswerNotifiedEvent1.getId());
        assertThat(newAnswerNotifiedEvent1).isEqualTo(newAnswerNotifiedEvent2);
        newAnswerNotifiedEvent2.setId(2L);
        assertThat(newAnswerNotifiedEvent1).isNotEqualTo(newAnswerNotifiedEvent2);
        newAnswerNotifiedEvent1.setId(null);
        assertThat(newAnswerNotifiedEvent1).isNotEqualTo(newAnswerNotifiedEvent2);
    }
}
