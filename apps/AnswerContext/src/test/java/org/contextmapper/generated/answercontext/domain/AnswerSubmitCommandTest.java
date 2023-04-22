package org.contextmapper.generated.answercontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.answercontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AnswerSubmitCommandTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AnswerSubmitCommand.class);
        AnswerSubmitCommand answerSubmitCommand1 = new AnswerSubmitCommand();
        answerSubmitCommand1.setId(1L);
        AnswerSubmitCommand answerSubmitCommand2 = new AnswerSubmitCommand();
        answerSubmitCommand2.setId(answerSubmitCommand1.getId());
        assertThat(answerSubmitCommand1).isEqualTo(answerSubmitCommand2);
        answerSubmitCommand2.setId(2L);
        assertThat(answerSubmitCommand1).isNotEqualTo(answerSubmitCommand2);
        answerSubmitCommand1.setId(null);
        assertThat(answerSubmitCommand1).isNotEqualTo(answerSubmitCommand2);
    }
}
