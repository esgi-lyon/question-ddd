package org.contextmapper.generated.skillcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.skillcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CreatedByIdTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CreatedById.class);
        CreatedById createdById1 = new CreatedById();
        createdById1.setId(1L);
        CreatedById createdById2 = new CreatedById();
        createdById2.setId(createdById1.getId());
        assertThat(createdById1).isEqualTo(createdById2);
        createdById2.setId(2L);
        assertThat(createdById1).isNotEqualTo(createdById2);
        createdById1.setId(null);
        assertThat(createdById1).isNotEqualTo(createdById2);
    }
}
