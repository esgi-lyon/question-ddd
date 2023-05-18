package org.contextmapper.generated.sendquestioncontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.sendquestioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class NotifiedQuestionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NotifiedQuestion.class);
        NotifiedQuestion notifiedQuestion1 = new NotifiedQuestion();
        notifiedQuestion1.setId(1L);
        NotifiedQuestion notifiedQuestion2 = new NotifiedQuestion();
        notifiedQuestion2.setId(notifiedQuestion1.getId());
        assertThat(notifiedQuestion1).isEqualTo(notifiedQuestion2);
        notifiedQuestion2.setId(2L);
        assertThat(notifiedQuestion1).isNotEqualTo(notifiedQuestion2);
        notifiedQuestion1.setId(null);
        assertThat(notifiedQuestion1).isNotEqualTo(notifiedQuestion2);
    }
}
