package org.contextmapper.generated.evaluationcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.evaluationcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class NotifyNewAnswerCommandTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NotifyNewAnswerCommand.class);
        NotifyNewAnswerCommand notifyNewAnswerCommand1 = new NotifyNewAnswerCommand();
        notifyNewAnswerCommand1.setId(1L);
        NotifyNewAnswerCommand notifyNewAnswerCommand2 = new NotifyNewAnswerCommand();
        notifyNewAnswerCommand2.setId(notifyNewAnswerCommand1.getId());
        assertThat(notifyNewAnswerCommand1).isEqualTo(notifyNewAnswerCommand2);
        notifyNewAnswerCommand2.setId(2L);
        assertThat(notifyNewAnswerCommand1).isNotEqualTo(notifyNewAnswerCommand2);
        notifyNewAnswerCommand1.setId(null);
        assertThat(notifyNewAnswerCommand1).isNotEqualTo(notifyNewAnswerCommand2);
    }
}
