package org.contextmapper.generated.statcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.statcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ViewStatsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ViewStats.class);
        ViewStats viewStats1 = new ViewStats();
        viewStats1.setId(1L);
        ViewStats viewStats2 = new ViewStats();
        viewStats2.setId(viewStats1.getId());
        assertThat(viewStats1).isEqualTo(viewStats2);
        viewStats2.setId(2L);
        assertThat(viewStats1).isNotEqualTo(viewStats2);
        viewStats1.setId(null);
        assertThat(viewStats1).isNotEqualTo(viewStats2);
    }
}
