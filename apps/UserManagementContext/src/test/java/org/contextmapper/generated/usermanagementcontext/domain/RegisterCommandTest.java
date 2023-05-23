package org.contextmapper.generated.usermanagementcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.usermanagementcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RegisterCommandTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RegisterCommand.class);
        RegisterCommand registerCommand1 = new RegisterCommand();
        registerCommand1.setId(1L);
        RegisterCommand registerCommand2 = new RegisterCommand();
        registerCommand2.setId(registerCommand1.getId());
        assertThat(registerCommand1).isEqualTo(registerCommand2);
        registerCommand2.setId(2L);
        assertThat(registerCommand1).isNotEqualTo(registerCommand2);
        registerCommand1.setId(null);
        assertThat(registerCommand1).isNotEqualTo(registerCommand2);
    }
}
