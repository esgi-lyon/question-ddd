package org.contextmapper.generated.questioncontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.questioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RejectResourceTagCommandTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RejectResourceTagCommand.class);
        RejectResourceTagCommand rejectResourceTagCommand1 = new RejectResourceTagCommand();
        rejectResourceTagCommand1.setId(1L);
        RejectResourceTagCommand rejectResourceTagCommand2 = new RejectResourceTagCommand();
        rejectResourceTagCommand2.setId(rejectResourceTagCommand1.getId());
        assertThat(rejectResourceTagCommand1).isEqualTo(rejectResourceTagCommand2);
        rejectResourceTagCommand2.setId(2L);
        assertThat(rejectResourceTagCommand1).isNotEqualTo(rejectResourceTagCommand2);
        rejectResourceTagCommand1.setId(null);
        assertThat(rejectResourceTagCommand1).isNotEqualTo(rejectResourceTagCommand2);
    }
}
