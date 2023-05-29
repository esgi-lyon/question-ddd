package org.contextmapper.generated.statcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.statcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ViewLeaderBoardCommandTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ViewLeaderBoardCommand.class);
        ViewLeaderBoardCommand viewLeaderBoardCommand1 = new ViewLeaderBoardCommand();
        viewLeaderBoardCommand1.setId(1L);
        ViewLeaderBoardCommand viewLeaderBoardCommand2 = new ViewLeaderBoardCommand();
        viewLeaderBoardCommand2.setId(viewLeaderBoardCommand1.getId());
        assertThat(viewLeaderBoardCommand1).isEqualTo(viewLeaderBoardCommand2);
        viewLeaderBoardCommand2.setId(2L);
        assertThat(viewLeaderBoardCommand1).isNotEqualTo(viewLeaderBoardCommand2);
        viewLeaderBoardCommand1.setId(null);
        assertThat(viewLeaderBoardCommand1).isNotEqualTo(viewLeaderBoardCommand2);
    }
}
