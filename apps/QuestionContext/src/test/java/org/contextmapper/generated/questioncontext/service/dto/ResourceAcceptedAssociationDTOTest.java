package org.contextmapper.generated.questioncontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.questioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ResourceAcceptedAssociationDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ResourceAcceptedAssociationDTO.class);
        ResourceAcceptedAssociationDTO resourceAcceptedAssociationDTO1 = new ResourceAcceptedAssociationDTO();
        resourceAcceptedAssociationDTO1.setId(1L);
        ResourceAcceptedAssociationDTO resourceAcceptedAssociationDTO2 = new ResourceAcceptedAssociationDTO();
        assertThat(resourceAcceptedAssociationDTO1).isNotEqualTo(resourceAcceptedAssociationDTO2);
        resourceAcceptedAssociationDTO2.setId(resourceAcceptedAssociationDTO1.getId());
        assertThat(resourceAcceptedAssociationDTO1).isEqualTo(resourceAcceptedAssociationDTO2);
        resourceAcceptedAssociationDTO2.setId(2L);
        assertThat(resourceAcceptedAssociationDTO1).isNotEqualTo(resourceAcceptedAssociationDTO2);
        resourceAcceptedAssociationDTO1.setId(null);
        assertThat(resourceAcceptedAssociationDTO1).isNotEqualTo(resourceAcceptedAssociationDTO2);
    }
}
