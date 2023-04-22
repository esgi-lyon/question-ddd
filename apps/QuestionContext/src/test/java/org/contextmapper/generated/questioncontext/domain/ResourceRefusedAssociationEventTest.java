package org.contextmapper.generated.questioncontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.questioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ResourceRefusedAssociationEventTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ResourceRefusedAssociationEvent.class);
        ResourceRefusedAssociationEvent resourceRefusedAssociationEvent1 = new ResourceRefusedAssociationEvent();
        resourceRefusedAssociationEvent1.setId(1L);
        ResourceRefusedAssociationEvent resourceRefusedAssociationEvent2 = new ResourceRefusedAssociationEvent();
        resourceRefusedAssociationEvent2.setId(resourceRefusedAssociationEvent1.getId());
        assertThat(resourceRefusedAssociationEvent1).isEqualTo(resourceRefusedAssociationEvent2);
        resourceRefusedAssociationEvent2.setId(2L);
        assertThat(resourceRefusedAssociationEvent1).isNotEqualTo(resourceRefusedAssociationEvent2);
        resourceRefusedAssociationEvent1.setId(null);
        assertThat(resourceRefusedAssociationEvent1).isNotEqualTo(resourceRefusedAssociationEvent2);
    }
}
