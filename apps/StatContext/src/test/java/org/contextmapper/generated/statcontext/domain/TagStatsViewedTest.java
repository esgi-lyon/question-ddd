package org.contextmapper.generated.statcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.statcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TagStatsViewedTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TagStatsViewed.class);
        TagStatsViewed tagStatsViewed1 = new TagStatsViewed();
        tagStatsViewed1.setId(1L);
        TagStatsViewed tagStatsViewed2 = new TagStatsViewed();
        tagStatsViewed2.setId(tagStatsViewed1.getId());
        assertThat(tagStatsViewed1).isEqualTo(tagStatsViewed2);
        tagStatsViewed2.setId(2L);
        assertThat(tagStatsViewed1).isNotEqualTo(tagStatsViewed2);
        tagStatsViewed1.setId(null);
        assertThat(tagStatsViewed1).isNotEqualTo(tagStatsViewed2);
    }
}
