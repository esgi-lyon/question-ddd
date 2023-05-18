package org.contextmapper.generated.questioncontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.questioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RejectResourceTagDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RejectResourceTagDTO.class);
        RejectResourceTagDTO rejectResourceTagDTO1 = new RejectResourceTagDTO();
        rejectResourceTagDTO1.setId(1L);
        RejectResourceTagDTO rejectResourceTagDTO2 = new RejectResourceTagDTO();
        assertThat(rejectResourceTagDTO1).isNotEqualTo(rejectResourceTagDTO2);
        rejectResourceTagDTO2.setId(rejectResourceTagDTO1.getId());
        assertThat(rejectResourceTagDTO1).isEqualTo(rejectResourceTagDTO2);
        rejectResourceTagDTO2.setId(2L);
        assertThat(rejectResourceTagDTO1).isNotEqualTo(rejectResourceTagDTO2);
        rejectResourceTagDTO1.setId(null);
        assertThat(rejectResourceTagDTO1).isNotEqualTo(rejectResourceTagDTO2);
    }
}
