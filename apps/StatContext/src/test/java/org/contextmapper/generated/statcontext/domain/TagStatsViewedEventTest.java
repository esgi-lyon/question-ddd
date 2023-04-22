package org.contextmapper.generated.statcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.statcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TagStatsViewedEventTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TagStatsViewedEvent.class);
        TagStatsViewedEvent tagStatsViewedEvent1 = new TagStatsViewedEvent();
        tagStatsViewedEvent1.setId(1L);
        TagStatsViewedEvent tagStatsViewedEvent2 = new TagStatsViewedEvent();
        tagStatsViewedEvent2.setId(tagStatsViewedEvent1.getId());
        assertThat(tagStatsViewedEvent1).isEqualTo(tagStatsViewedEvent2);
        tagStatsViewedEvent2.setId(2L);
        assertThat(tagStatsViewedEvent1).isNotEqualTo(tagStatsViewedEvent2);
        tagStatsViewedEvent1.setId(null);
        assertThat(tagStatsViewedEvent1).isNotEqualTo(tagStatsViewedEvent2);
    }
}
