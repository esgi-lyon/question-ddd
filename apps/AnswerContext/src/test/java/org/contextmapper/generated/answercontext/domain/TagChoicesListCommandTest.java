package org.contextmapper.generated.answercontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.answercontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TagChoicesListCommandTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TagChoicesListCommand.class);
        TagChoicesListCommand tagChoicesListCommand1 = new TagChoicesListCommand();
        tagChoicesListCommand1.setId(1L);
        TagChoicesListCommand tagChoicesListCommand2 = new TagChoicesListCommand();
        tagChoicesListCommand2.setId(tagChoicesListCommand1.getId());
        assertThat(tagChoicesListCommand1).isEqualTo(tagChoicesListCommand2);
        tagChoicesListCommand2.setId(2L);
        assertThat(tagChoicesListCommand1).isNotEqualTo(tagChoicesListCommand2);
        tagChoicesListCommand1.setId(null);
        assertThat(tagChoicesListCommand1).isNotEqualTo(tagChoicesListCommand2);
    }
}
