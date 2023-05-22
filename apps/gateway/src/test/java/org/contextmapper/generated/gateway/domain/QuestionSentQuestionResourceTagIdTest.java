package org.contextmapper.generated.gateway.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class QuestionSentQuestionResourceTagIdTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuestionSentQuestionResourceTagId.class);
        QuestionSentQuestionResourceTagId questionSentQuestionResourceTagId1 = new QuestionSentQuestionResourceTagId();
        questionSentQuestionResourceTagId1.setId(1L);
        QuestionSentQuestionResourceTagId questionSentQuestionResourceTagId2 = new QuestionSentQuestionResourceTagId();
        questionSentQuestionResourceTagId2.setId(questionSentQuestionResourceTagId1.getId());
        assertThat(questionSentQuestionResourceTagId1).isEqualTo(questionSentQuestionResourceTagId2);
        questionSentQuestionResourceTagId2.setId(2L);
        assertThat(questionSentQuestionResourceTagId1).isNotEqualTo(questionSentQuestionResourceTagId2);
        questionSentQuestionResourceTagId1.setId(null);
        assertThat(questionSentQuestionResourceTagId1).isNotEqualTo(questionSentQuestionResourceTagId2);
    }
}
