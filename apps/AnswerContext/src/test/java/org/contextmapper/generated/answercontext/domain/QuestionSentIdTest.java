package org.contextmapper.generated.answercontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.answercontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class QuestionSentIdTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuestionSentId.class);
        QuestionSentId questionSentId1 = new QuestionSentId();
        questionSentId1.setId(1L);
        QuestionSentId questionSentId2 = new QuestionSentId();
        questionSentId2.setId(questionSentId1.getId());
        assertThat(questionSentId1).isEqualTo(questionSentId2);
        questionSentId2.setId(2L);
        assertThat(questionSentId1).isNotEqualTo(questionSentId2);
        questionSentId1.setId(null);
        assertThat(questionSentId1).isNotEqualTo(questionSentId2);
    }
}
