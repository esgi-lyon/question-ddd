package org.contextmapper.generated.usermanagementcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.usermanagementcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UserViewedEventDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserViewedEventDTO.class);
        UserViewedEventDTO userViewedEventDTO1 = new UserViewedEventDTO();
        userViewedEventDTO1.setId(1L);
        UserViewedEventDTO userViewedEventDTO2 = new UserViewedEventDTO();
        assertThat(userViewedEventDTO1).isNotEqualTo(userViewedEventDTO2);
        userViewedEventDTO2.setId(userViewedEventDTO1.getId());
        assertThat(userViewedEventDTO1).isEqualTo(userViewedEventDTO2);
        userViewedEventDTO2.setId(2L);
        assertThat(userViewedEventDTO1).isNotEqualTo(userViewedEventDTO2);
        userViewedEventDTO1.setId(null);
        assertThat(userViewedEventDTO1).isNotEqualTo(userViewedEventDTO2);
    }
}
