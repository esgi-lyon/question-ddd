package org.contextmapper.generated.sendquestioncontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.sendquestioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ResourceIdDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ResourceIdDTO.class);
        ResourceIdDTO resourceIdDTO1 = new ResourceIdDTO();
        resourceIdDTO1.setId(1L);
        ResourceIdDTO resourceIdDTO2 = new ResourceIdDTO();
        assertThat(resourceIdDTO1).isNotEqualTo(resourceIdDTO2);
        resourceIdDTO2.setId(resourceIdDTO1.getId());
        assertThat(resourceIdDTO1).isEqualTo(resourceIdDTO2);
        resourceIdDTO2.setId(2L);
        assertThat(resourceIdDTO1).isNotEqualTo(resourceIdDTO2);
        resourceIdDTO1.setId(null);
        assertThat(resourceIdDTO1).isNotEqualTo(resourceIdDTO2);
    }
}
