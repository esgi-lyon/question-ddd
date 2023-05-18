package org.contextmapper.generated.answercontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.answercontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TagChoicesListTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TagChoicesList.class);
        TagChoicesList tagChoicesList1 = new TagChoicesList();
        tagChoicesList1.setId(1L);
        TagChoicesList tagChoicesList2 = new TagChoicesList();
        tagChoicesList2.setId(tagChoicesList1.getId());
        assertThat(tagChoicesList1).isEqualTo(tagChoicesList2);
        tagChoicesList2.setId(2L);
        assertThat(tagChoicesList1).isNotEqualTo(tagChoicesList2);
        tagChoicesList1.setId(null);
        assertThat(tagChoicesList1).isNotEqualTo(tagChoicesList2);
    }
}
