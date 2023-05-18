package org.contextmapper.generated.evaluationcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.evaluationcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AnsweringUserTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AnsweringUser.class);
        AnsweringUser answeringUser1 = new AnsweringUser();
        answeringUser1.setId(1L);
        AnsweringUser answeringUser2 = new AnsweringUser();
        answeringUser2.setId(answeringUser1.getId());
        assertThat(answeringUser1).isEqualTo(answeringUser2);
        answeringUser2.setId(2L);
        assertThat(answeringUser1).isNotEqualTo(answeringUser2);
        answeringUser1.setId(null);
        assertThat(answeringUser1).isNotEqualTo(answeringUser2);
    }
}
