package org.contextmapper.generated.gateway.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CheckAnswerCommandTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CheckAnswerCommand.class);
        CheckAnswerCommand checkAnswerCommand1 = new CheckAnswerCommand();
        checkAnswerCommand1.setId(1L);
        CheckAnswerCommand checkAnswerCommand2 = new CheckAnswerCommand();
        checkAnswerCommand2.setId(checkAnswerCommand1.getId());
        assertThat(checkAnswerCommand1).isEqualTo(checkAnswerCommand2);
        checkAnswerCommand2.setId(2L);
        assertThat(checkAnswerCommand1).isNotEqualTo(checkAnswerCommand2);
        checkAnswerCommand1.setId(null);
        assertThat(checkAnswerCommand1).isNotEqualTo(checkAnswerCommand2);
    }
}
