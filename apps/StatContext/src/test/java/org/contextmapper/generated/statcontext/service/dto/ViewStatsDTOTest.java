package org.contextmapper.generated.statcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.statcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ViewStatsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ViewStatsDTO.class);
        ViewStatsDTO viewStatsDTO1 = new ViewStatsDTO();
        viewStatsDTO1.setId(1L);
        ViewStatsDTO viewStatsDTO2 = new ViewStatsDTO();
        assertThat(viewStatsDTO1).isNotEqualTo(viewStatsDTO2);
        viewStatsDTO2.setId(viewStatsDTO1.getId());
        assertThat(viewStatsDTO1).isEqualTo(viewStatsDTO2);
        viewStatsDTO2.setId(2L);
        assertThat(viewStatsDTO1).isNotEqualTo(viewStatsDTO2);
        viewStatsDTO1.setId(null);
        assertThat(viewStatsDTO1).isNotEqualTo(viewStatsDTO2);
    }
}
