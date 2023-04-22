package org.contextmapper.generated.questioncontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.questioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ResourceAcceptedAssociationEventDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ResourceAcceptedAssociationEventDTO.class);
        ResourceAcceptedAssociationEventDTO resourceAcceptedAssociationEventDTO1 = new ResourceAcceptedAssociationEventDTO();
        resourceAcceptedAssociationEventDTO1.setId(1L);
        ResourceAcceptedAssociationEventDTO resourceAcceptedAssociationEventDTO2 = new ResourceAcceptedAssociationEventDTO();
        assertThat(resourceAcceptedAssociationEventDTO1).isNotEqualTo(resourceAcceptedAssociationEventDTO2);
        resourceAcceptedAssociationEventDTO2.setId(resourceAcceptedAssociationEventDTO1.getId());
        assertThat(resourceAcceptedAssociationEventDTO1).isEqualTo(resourceAcceptedAssociationEventDTO2);
        resourceAcceptedAssociationEventDTO2.setId(2L);
        assertThat(resourceAcceptedAssociationEventDTO1).isNotEqualTo(resourceAcceptedAssociationEventDTO2);
        resourceAcceptedAssociationEventDTO1.setId(null);
        assertThat(resourceAcceptedAssociationEventDTO1).isNotEqualTo(resourceAcceptedAssociationEventDTO2);
    }
}
