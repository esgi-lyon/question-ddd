package org.contextmapper.generated.gateway.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class QuestionSentTagInfosTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuestionSentTagInfos.class);
        QuestionSentTagInfos questionSentTagInfos1 = new QuestionSentTagInfos();
        questionSentTagInfos1.setId(1L);
        QuestionSentTagInfos questionSentTagInfos2 = new QuestionSentTagInfos();
        questionSentTagInfos2.setId(questionSentTagInfos1.getId());
        assertThat(questionSentTagInfos1).isEqualTo(questionSentTagInfos2);
        questionSentTagInfos2.setId(2L);
        assertThat(questionSentTagInfos1).isNotEqualTo(questionSentTagInfos2);
        questionSentTagInfos1.setId(null);
        assertThat(questionSentTagInfos1).isNotEqualTo(questionSentTagInfos2);
    }
}
