package org.contextmapper.generated.usermanagementcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.usermanagementcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RejectUserCommandTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RejectUserCommand.class);
        RejectUserCommand rejectUserCommand1 = new RejectUserCommand();
        rejectUserCommand1.setId(1L);
        RejectUserCommand rejectUserCommand2 = new RejectUserCommand();
        rejectUserCommand2.setId(rejectUserCommand1.getId());
        assertThat(rejectUserCommand1).isEqualTo(rejectUserCommand2);
        rejectUserCommand2.setId(2L);
        assertThat(rejectUserCommand1).isNotEqualTo(rejectUserCommand2);
        rejectUserCommand1.setId(null);
        assertThat(rejectUserCommand1).isNotEqualTo(rejectUserCommand2);
    }
}
