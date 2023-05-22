package org.contextmapper.generated.sendquestioncontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.sendquestioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ResourceIdTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ResourceId.class);
        ResourceId resourceId1 = new ResourceId();
        resourceId1.setId(1L);
        ResourceId resourceId2 = new ResourceId();
        resourceId2.setId(resourceId1.getId());
        assertThat(resourceId1).isEqualTo(resourceId2);
        resourceId2.setId(2L);
        assertThat(resourceId1).isNotEqualTo(resourceId2);
        resourceId1.setId(null);
        assertThat(resourceId1).isNotEqualTo(resourceId2);
    }
}
