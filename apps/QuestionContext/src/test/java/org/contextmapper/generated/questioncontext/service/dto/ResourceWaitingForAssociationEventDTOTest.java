package org.contextmapper.generated.questioncontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.questioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ResourceWaitingForAssociationEventDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ResourceWaitingForAssociationEventDTO.class);
        ResourceWaitingForAssociationEventDTO resourceWaitingForAssociationEventDTO1 = new ResourceWaitingForAssociationEventDTO();
        resourceWaitingForAssociationEventDTO1.setId(1L);
        ResourceWaitingForAssociationEventDTO resourceWaitingForAssociationEventDTO2 = new ResourceWaitingForAssociationEventDTO();
        assertThat(resourceWaitingForAssociationEventDTO1).isNotEqualTo(resourceWaitingForAssociationEventDTO2);
        resourceWaitingForAssociationEventDTO2.setId(resourceWaitingForAssociationEventDTO1.getId());
        assertThat(resourceWaitingForAssociationEventDTO1).isEqualTo(resourceWaitingForAssociationEventDTO2);
        resourceWaitingForAssociationEventDTO2.setId(2L);
        assertThat(resourceWaitingForAssociationEventDTO1).isNotEqualTo(resourceWaitingForAssociationEventDTO2);
        resourceWaitingForAssociationEventDTO1.setId(null);
        assertThat(resourceWaitingForAssociationEventDTO1).isNotEqualTo(resourceWaitingForAssociationEventDTO2);
    }
}
