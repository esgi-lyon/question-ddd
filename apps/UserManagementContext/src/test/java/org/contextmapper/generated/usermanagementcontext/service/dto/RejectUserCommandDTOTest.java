package org.contextmapper.generated.usermanagementcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.usermanagementcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RejectUserCommandDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RejectUserCommandDTO.class);
        RejectUserCommandDTO rejectUserCommandDTO1 = new RejectUserCommandDTO();
        rejectUserCommandDTO1.setId(1L);
        RejectUserCommandDTO rejectUserCommandDTO2 = new RejectUserCommandDTO();
        assertThat(rejectUserCommandDTO1).isNotEqualTo(rejectUserCommandDTO2);
        rejectUserCommandDTO2.setId(rejectUserCommandDTO1.getId());
        assertThat(rejectUserCommandDTO1).isEqualTo(rejectUserCommandDTO2);
        rejectUserCommandDTO2.setId(2L);
        assertThat(rejectUserCommandDTO1).isNotEqualTo(rejectUserCommandDTO2);
        rejectUserCommandDTO1.setId(null);
        assertThat(rejectUserCommandDTO1).isNotEqualTo(rejectUserCommandDTO2);
    }
}
