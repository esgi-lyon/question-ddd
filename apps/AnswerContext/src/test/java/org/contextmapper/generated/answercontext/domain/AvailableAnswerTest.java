package org.contextmapper.generated.answercontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.answercontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AvailableAnswerTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AvailableAnswer.class);
        AvailableAnswer availableAnswer1 = new AvailableAnswer();
        availableAnswer1.setId(1L);
        AvailableAnswer availableAnswer2 = new AvailableAnswer();
        availableAnswer2.setId(availableAnswer1.getId());
        assertThat(availableAnswer1).isEqualTo(availableAnswer2);
        availableAnswer2.setId(2L);
        assertThat(availableAnswer1).isNotEqualTo(availableAnswer2);
        availableAnswer1.setId(null);
        assertThat(availableAnswer1).isNotEqualTo(availableAnswer2);
    }
}
