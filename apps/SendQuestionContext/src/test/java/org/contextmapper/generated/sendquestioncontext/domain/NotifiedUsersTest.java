package org.contextmapper.generated.sendquestioncontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.sendquestioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class NotifiedUsersTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NotifiedUsers.class);
        NotifiedUsers notifiedUsers1 = new NotifiedUsers();
        notifiedUsers1.setId(1L);
        NotifiedUsers notifiedUsers2 = new NotifiedUsers();
        notifiedUsers2.setId(notifiedUsers1.getId());
        assertThat(notifiedUsers1).isEqualTo(notifiedUsers2);
        notifiedUsers2.setId(2L);
        assertThat(notifiedUsers1).isNotEqualTo(notifiedUsers2);
        notifiedUsers1.setId(null);
        assertThat(notifiedUsers1).isNotEqualTo(notifiedUsers2);
    }
}
