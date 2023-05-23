package org.contextmapper.generated.usermanagementcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.usermanagementcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ValidateUserCommandTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ValidateUserCommand.class);
        ValidateUserCommand validateUserCommand1 = new ValidateUserCommand();
        validateUserCommand1.setId(1L);
        ValidateUserCommand validateUserCommand2 = new ValidateUserCommand();
        validateUserCommand2.setId(validateUserCommand1.getId());
        assertThat(validateUserCommand1).isEqualTo(validateUserCommand2);
        validateUserCommand2.setId(2L);
        assertThat(validateUserCommand1).isNotEqualTo(validateUserCommand2);
        validateUserCommand1.setId(null);
        assertThat(validateUserCommand1).isNotEqualTo(validateUserCommand2);
    }
}
