package org.contextmapper.generated.sendquestioncontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.sendquestioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class NotifiedUsersDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NotifiedUsersDTO.class);
        NotifiedUsersDTO notifiedUsersDTO1 = new NotifiedUsersDTO();
        notifiedUsersDTO1.setId(1L);
        NotifiedUsersDTO notifiedUsersDTO2 = new NotifiedUsersDTO();
        assertThat(notifiedUsersDTO1).isNotEqualTo(notifiedUsersDTO2);
        notifiedUsersDTO2.setId(notifiedUsersDTO1.getId());
        assertThat(notifiedUsersDTO1).isEqualTo(notifiedUsersDTO2);
        notifiedUsersDTO2.setId(2L);
        assertThat(notifiedUsersDTO1).isNotEqualTo(notifiedUsersDTO2);
        notifiedUsersDTO1.setId(null);
        assertThat(notifiedUsersDTO1).isNotEqualTo(notifiedUsersDTO2);
    }
}
