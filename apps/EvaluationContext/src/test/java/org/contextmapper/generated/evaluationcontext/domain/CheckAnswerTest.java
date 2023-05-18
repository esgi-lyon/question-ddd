package org.contextmapper.generated.evaluationcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.evaluationcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CheckAnswerTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CheckAnswer.class);
        CheckAnswer checkAnswer1 = new CheckAnswer();
        checkAnswer1.setId(1L);
        CheckAnswer checkAnswer2 = new CheckAnswer();
        checkAnswer2.setId(checkAnswer1.getId());
        assertThat(checkAnswer1).isEqualTo(checkAnswer2);
        checkAnswer2.setId(2L);
        assertThat(checkAnswer1).isNotEqualTo(checkAnswer2);
        checkAnswer1.setId(null);
        assertThat(checkAnswer1).isNotEqualTo(checkAnswer2);
    }
}
