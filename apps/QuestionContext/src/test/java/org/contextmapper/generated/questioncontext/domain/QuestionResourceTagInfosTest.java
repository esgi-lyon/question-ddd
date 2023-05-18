package org.contextmapper.generated.questioncontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.questioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class QuestionResourceTagInfosTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuestionResourceTagInfos.class);
        QuestionResourceTagInfos questionResourceTagInfos1 = new QuestionResourceTagInfos();
        questionResourceTagInfos1.setId(1L);
        QuestionResourceTagInfos questionResourceTagInfos2 = new QuestionResourceTagInfos();
        questionResourceTagInfos2.setId(questionResourceTagInfos1.getId());
        assertThat(questionResourceTagInfos1).isEqualTo(questionResourceTagInfos2);
        questionResourceTagInfos2.setId(2L);
        assertThat(questionResourceTagInfos1).isNotEqualTo(questionResourceTagInfos2);
        questionResourceTagInfos1.setId(null);
        assertThat(questionResourceTagInfos1).isNotEqualTo(questionResourceTagInfos2);
    }
}
