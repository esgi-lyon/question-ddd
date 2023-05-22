package org.contextmapper.generated.gateway.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TagStatsViewedEventDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TagStatsViewedEventDTO.class);
        TagStatsViewedEventDTO tagStatsViewedEventDTO1 = new TagStatsViewedEventDTO();
        tagStatsViewedEventDTO1.setId(1L);
        TagStatsViewedEventDTO tagStatsViewedEventDTO2 = new TagStatsViewedEventDTO();
        assertThat(tagStatsViewedEventDTO1).isNotEqualTo(tagStatsViewedEventDTO2);
        tagStatsViewedEventDTO2.setId(tagStatsViewedEventDTO1.getId());
        assertThat(tagStatsViewedEventDTO1).isEqualTo(tagStatsViewedEventDTO2);
        tagStatsViewedEventDTO2.setId(2L);
        assertThat(tagStatsViewedEventDTO1).isNotEqualTo(tagStatsViewedEventDTO2);
        tagStatsViewedEventDTO1.setId(null);
        assertThat(tagStatsViewedEventDTO1).isNotEqualTo(tagStatsViewedEventDTO2);
    }
}
