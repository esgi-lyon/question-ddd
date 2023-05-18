package org.contextmapper.generated.answercontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.answercontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class QuestionIdTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuestionId.class);
        QuestionId questionId1 = new QuestionId();
        questionId1.setId(1L);
        QuestionId questionId2 = new QuestionId();
        questionId2.setId(questionId1.getId());
        assertThat(questionId1).isEqualTo(questionId2);
        questionId2.setId(2L);
        assertThat(questionId1).isNotEqualTo(questionId2);
        questionId1.setId(null);
        assertThat(questionId1).isNotEqualTo(questionId2);
    }
}
