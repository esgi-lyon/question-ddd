package org.contextmapper.generated.evaluationcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.evaluationcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ViewUserEvaluationCommandTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ViewUserEvaluationCommand.class);
        ViewUserEvaluationCommand viewUserEvaluationCommand1 = new ViewUserEvaluationCommand();
        viewUserEvaluationCommand1.setId(1L);
        ViewUserEvaluationCommand viewUserEvaluationCommand2 = new ViewUserEvaluationCommand();
        viewUserEvaluationCommand2.setId(viewUserEvaluationCommand1.getId());
        assertThat(viewUserEvaluationCommand1).isEqualTo(viewUserEvaluationCommand2);
        viewUserEvaluationCommand2.setId(2L);
        assertThat(viewUserEvaluationCommand1).isNotEqualTo(viewUserEvaluationCommand2);
        viewUserEvaluationCommand1.setId(null);
        assertThat(viewUserEvaluationCommand1).isNotEqualTo(viewUserEvaluationCommand2);
    }
}
