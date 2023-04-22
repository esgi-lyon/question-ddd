package org.contextmapper.generated.statcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.statcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ViewStatsCommandTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ViewStatsCommand.class);
        ViewStatsCommand viewStatsCommand1 = new ViewStatsCommand();
        viewStatsCommand1.setId(1L);
        ViewStatsCommand viewStatsCommand2 = new ViewStatsCommand();
        viewStatsCommand2.setId(viewStatsCommand1.getId());
        assertThat(viewStatsCommand1).isEqualTo(viewStatsCommand2);
        viewStatsCommand2.setId(2L);
        assertThat(viewStatsCommand1).isNotEqualTo(viewStatsCommand2);
        viewStatsCommand1.setId(null);
        assertThat(viewStatsCommand1).isNotEqualTo(viewStatsCommand2);
    }
}
