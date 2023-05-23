package org.contextmapper.generated.usermanagementcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.usermanagementcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UserRejectedEventDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserRejectedEventDTO.class);
        UserRejectedEventDTO userRejectedEventDTO1 = new UserRejectedEventDTO();
        userRejectedEventDTO1.setId(1L);
        UserRejectedEventDTO userRejectedEventDTO2 = new UserRejectedEventDTO();
        assertThat(userRejectedEventDTO1).isNotEqualTo(userRejectedEventDTO2);
        userRejectedEventDTO2.setId(userRejectedEventDTO1.getId());
        assertThat(userRejectedEventDTO1).isEqualTo(userRejectedEventDTO2);
        userRejectedEventDTO2.setId(2L);
        assertThat(userRejectedEventDTO1).isNotEqualTo(userRejectedEventDTO2);
        userRejectedEventDTO1.setId(null);
        assertThat(userRejectedEventDTO1).isNotEqualTo(userRejectedEventDTO2);
    }
}
