package org.contextmapper.generated.questioncontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.questioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class QuestionResourceTagTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuestionResourceTag.class);
        QuestionResourceTag questionResourceTag1 = new QuestionResourceTag();
        questionResourceTag1.setId(1L);
        QuestionResourceTag questionResourceTag2 = new QuestionResourceTag();
        questionResourceTag2.setId(questionResourceTag1.getId());
        assertThat(questionResourceTag1).isEqualTo(questionResourceTag2);
        questionResourceTag2.setId(2L);
        assertThat(questionResourceTag1).isNotEqualTo(questionResourceTag2);
        questionResourceTag1.setId(null);
        assertThat(questionResourceTag1).isNotEqualTo(questionResourceTag2);
    }
}
