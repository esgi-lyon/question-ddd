package org.contextmapper.generated.questioncontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.questioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class QuestionResourceTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuestionResource.class);
        QuestionResource questionResource1 = new QuestionResource();
        questionResource1.setId(1L);
        QuestionResource questionResource2 = new QuestionResource();
        questionResource2.setId(questionResource1.getId());
        assertThat(questionResource1).isEqualTo(questionResource2);
        questionResource2.setId(2L);
        assertThat(questionResource1).isNotEqualTo(questionResource2);
        questionResource1.setId(null);
        assertThat(questionResource1).isNotEqualTo(questionResource2);
    }
}
