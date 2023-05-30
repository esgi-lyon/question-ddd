package org.contextmapper.generated.evaluationcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.evaluationcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ViewTagEvaluationCommandTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ViewTagEvaluationCommand.class);
        ViewTagEvaluationCommand viewTagEvaluationCommand1 = new ViewTagEvaluationCommand();
        viewTagEvaluationCommand1.setId(1L);
        ViewTagEvaluationCommand viewTagEvaluationCommand2 = new ViewTagEvaluationCommand();
        viewTagEvaluationCommand2.setId(viewTagEvaluationCommand1.getId());
        assertThat(viewTagEvaluationCommand1).isEqualTo(viewTagEvaluationCommand2);
        viewTagEvaluationCommand2.setId(2L);
        assertThat(viewTagEvaluationCommand1).isNotEqualTo(viewTagEvaluationCommand2);
        viewTagEvaluationCommand1.setId(null);
        assertThat(viewTagEvaluationCommand1).isNotEqualTo(viewTagEvaluationCommand2);
    }
}
