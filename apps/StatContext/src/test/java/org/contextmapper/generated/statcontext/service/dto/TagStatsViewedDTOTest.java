package org.contextmapper.generated.statcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.statcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TagStatsViewedDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TagStatsViewedDTO.class);
        TagStatsViewedDTO tagStatsViewedDTO1 = new TagStatsViewedDTO();
        tagStatsViewedDTO1.setId(1L);
        TagStatsViewedDTO tagStatsViewedDTO2 = new TagStatsViewedDTO();
        assertThat(tagStatsViewedDTO1).isNotEqualTo(tagStatsViewedDTO2);
        tagStatsViewedDTO2.setId(tagStatsViewedDTO1.getId());
        assertThat(tagStatsViewedDTO1).isEqualTo(tagStatsViewedDTO2);
        tagStatsViewedDTO2.setId(2L);
        assertThat(tagStatsViewedDTO1).isNotEqualTo(tagStatsViewedDTO2);
        tagStatsViewedDTO1.setId(null);
        assertThat(tagStatsViewedDTO1).isNotEqualTo(tagStatsViewedDTO2);
    }
}
