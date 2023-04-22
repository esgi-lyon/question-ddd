package org.contextmapper.generated.questioncontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.questioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ResourceWaitingForAssociationEventTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ResourceWaitingForAssociationEvent.class);
        ResourceWaitingForAssociationEvent resourceWaitingForAssociationEvent1 = new ResourceWaitingForAssociationEvent();
        resourceWaitingForAssociationEvent1.setId(1L);
        ResourceWaitingForAssociationEvent resourceWaitingForAssociationEvent2 = new ResourceWaitingForAssociationEvent();
        resourceWaitingForAssociationEvent2.setId(resourceWaitingForAssociationEvent1.getId());
        assertThat(resourceWaitingForAssociationEvent1).isEqualTo(resourceWaitingForAssociationEvent2);
        resourceWaitingForAssociationEvent2.setId(2L);
        assertThat(resourceWaitingForAssociationEvent1).isNotEqualTo(resourceWaitingForAssociationEvent2);
        resourceWaitingForAssociationEvent1.setId(null);
        assertThat(resourceWaitingForAssociationEvent1).isNotEqualTo(resourceWaitingForAssociationEvent2);
    }
}
