package org.contextmapper.generated.questioncontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.questioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RejectResourceTagTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RejectResourceTag.class);
        RejectResourceTag rejectResourceTag1 = new RejectResourceTag();
        rejectResourceTag1.setId(1L);
        RejectResourceTag rejectResourceTag2 = new RejectResourceTag();
        rejectResourceTag2.setId(rejectResourceTag1.getId());
        assertThat(rejectResourceTag1).isEqualTo(rejectResourceTag2);
        rejectResourceTag2.setId(2L);
        assertThat(rejectResourceTag1).isNotEqualTo(rejectResourceTag2);
        rejectResourceTag1.setId(null);
        assertThat(rejectResourceTag1).isNotEqualTo(rejectResourceTag2);
    }
}
