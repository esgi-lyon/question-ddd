package org.contextmapper.generated.evaluationcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.evaluationcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class NotifyNewAnswerCommandDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NotifyNewAnswerCommandDTO.class);
        NotifyNewAnswerCommandDTO notifyNewAnswerCommandDTO1 = new NotifyNewAnswerCommandDTO();
        notifyNewAnswerCommandDTO1.setId(1L);
        NotifyNewAnswerCommandDTO notifyNewAnswerCommandDTO2 = new NotifyNewAnswerCommandDTO();
        assertThat(notifyNewAnswerCommandDTO1).isNotEqualTo(notifyNewAnswerCommandDTO2);
        notifyNewAnswerCommandDTO2.setId(notifyNewAnswerCommandDTO1.getId());
        assertThat(notifyNewAnswerCommandDTO1).isEqualTo(notifyNewAnswerCommandDTO2);
        notifyNewAnswerCommandDTO2.setId(2L);
        assertThat(notifyNewAnswerCommandDTO1).isNotEqualTo(notifyNewAnswerCommandDTO2);
        notifyNewAnswerCommandDTO1.setId(null);
        assertThat(notifyNewAnswerCommandDTO1).isNotEqualTo(notifyNewAnswerCommandDTO2);
    }
}
