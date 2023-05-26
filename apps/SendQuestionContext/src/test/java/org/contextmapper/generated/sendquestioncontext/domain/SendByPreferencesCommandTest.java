package org.contextmapper.generated.sendquestioncontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.sendquestioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SendByPreferencesCommandTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SendByPreferencesCommand.class);
        SendByPreferencesCommand sendByPreferencesCommand1 = new SendByPreferencesCommand();
        sendByPreferencesCommand1.setId(1L);
        SendByPreferencesCommand sendByPreferencesCommand2 = new SendByPreferencesCommand();
        sendByPreferencesCommand2.setId(sendByPreferencesCommand1.getId());
        assertThat(sendByPreferencesCommand1).isEqualTo(sendByPreferencesCommand2);
        sendByPreferencesCommand2.setId(2L);
        assertThat(sendByPreferencesCommand1).isNotEqualTo(sendByPreferencesCommand2);
        sendByPreferencesCommand1.setId(null);
        assertThat(sendByPreferencesCommand1).isNotEqualTo(sendByPreferencesCommand2);
    }
}
