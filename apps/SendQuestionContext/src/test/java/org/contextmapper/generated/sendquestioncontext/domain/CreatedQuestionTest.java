package org.contextmapper.generated.sendquestioncontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.sendquestioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CreatedQuestionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CreatedQuestion.class);
        CreatedQuestion createdQuestion1 = new CreatedQuestion();
        createdQuestion1.setId(1L);
        CreatedQuestion createdQuestion2 = new CreatedQuestion();
        createdQuestion2.setId(createdQuestion1.getId());
        assertThat(createdQuestion1).isEqualTo(createdQuestion2);
        createdQuestion2.setId(2L);
        assertThat(createdQuestion1).isNotEqualTo(createdQuestion2);
        createdQuestion1.setId(null);
        assertThat(createdQuestion1).isNotEqualTo(createdQuestion2);
    }
}
