package org.contextmapper.generated.gateway.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class QuestionSentTagIdTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuestionSentTagId.class);
        QuestionSentTagId questionSentTagId1 = new QuestionSentTagId();
        questionSentTagId1.setId(1L);
        QuestionSentTagId questionSentTagId2 = new QuestionSentTagId();
        questionSentTagId2.setId(questionSentTagId1.getId());
        assertThat(questionSentTagId1).isEqualTo(questionSentTagId2);
        questionSentTagId2.setId(2L);
        assertThat(questionSentTagId1).isNotEqualTo(questionSentTagId2);
        questionSentTagId1.setId(null);
        assertThat(questionSentTagId1).isNotEqualTo(questionSentTagId2);
    }
}
