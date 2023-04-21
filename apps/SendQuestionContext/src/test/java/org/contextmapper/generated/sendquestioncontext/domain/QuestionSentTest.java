package org.contextmapper.generated.sendquestioncontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.sendquestioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class QuestionSentTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuestionSent.class);
        QuestionSent questionSent1 = new QuestionSent();
        questionSent1.setId(1L);
        QuestionSent questionSent2 = new QuestionSent();
        questionSent2.setId(questionSent1.getId());
        assertThat(questionSent1).isEqualTo(questionSent2);
        questionSent2.setId(2L);
        assertThat(questionSent1).isNotEqualTo(questionSent2);
        questionSent1.setId(null);
        assertThat(questionSent1).isNotEqualTo(questionSent2);
    }
}
