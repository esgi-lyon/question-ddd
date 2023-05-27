package org.contextmapper.generated.statcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.statcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ViewStatsCommandDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ViewStatsCommandDTO.class);
        ViewStatsCommandDTO viewStatsCommandDTO1 = new ViewStatsCommandDTO();
        viewStatsCommandDTO1.setId(1L);
        ViewStatsCommandDTO viewStatsCommandDTO2 = new ViewStatsCommandDTO();
        assertThat(viewStatsCommandDTO1).isNotEqualTo(viewStatsCommandDTO2);
        viewStatsCommandDTO2.setId(viewStatsCommandDTO1.getId());
        assertThat(viewStatsCommandDTO1).isEqualTo(viewStatsCommandDTO2);
        viewStatsCommandDTO2.setId(2L);
        assertThat(viewStatsCommandDTO1).isNotEqualTo(viewStatsCommandDTO2);
        viewStatsCommandDTO1.setId(null);
        assertThat(viewStatsCommandDTO1).isNotEqualTo(viewStatsCommandDTO2);
    }
}
