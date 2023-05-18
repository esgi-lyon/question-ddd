package org.contextmapper.generated.sendquestioncontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.sendquestioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CreateQuestionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CreateQuestion.class);
        CreateQuestion createQuestion1 = new CreateQuestion();
        createQuestion1.setId(1L);
        CreateQuestion createQuestion2 = new CreateQuestion();
        createQuestion2.setId(createQuestion1.getId());
        assertThat(createQuestion1).isEqualTo(createQuestion2);
        createQuestion2.setId(2L);
        assertThat(createQuestion1).isNotEqualTo(createQuestion2);
        createQuestion1.setId(null);
        assertThat(createQuestion1).isNotEqualTo(createQuestion2);
    }
}
