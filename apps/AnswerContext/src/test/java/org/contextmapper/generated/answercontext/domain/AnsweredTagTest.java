package org.contextmapper.generated.answercontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.answercontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AnsweredTagTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AnsweredTag.class);
        AnsweredTag answeredTag1 = new AnsweredTag();
        answeredTag1.setId(1L);
        AnsweredTag answeredTag2 = new AnsweredTag();
        answeredTag2.setId(answeredTag1.getId());
        assertThat(answeredTag1).isEqualTo(answeredTag2);
        answeredTag2.setId(2L);
        assertThat(answeredTag1).isNotEqualTo(answeredTag2);
        answeredTag1.setId(null);
        assertThat(answeredTag1).isNotEqualTo(answeredTag2);
    }
}
