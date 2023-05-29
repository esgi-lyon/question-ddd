package org.contextmapper.generated.answercontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.answercontext.domain.ViewUserEvaluationCommand} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ViewUserEvaluationCommandDTO implements Serializable {

    private Long id;

    private UserEmailDTO user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEmailDTO getUser() {
        return user;
    }

    public void setUser(UserEmailDTO user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ViewUserEvaluationCommandDTO)) {
            return false;
        }

        ViewUserEvaluationCommandDTO viewUserEvaluationCommandDTO = (ViewUserEvaluationCommandDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, viewUserEvaluationCommandDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ViewUserEvaluationCommandDTO{" +
            "id=" + getId() +
            ", user=" + getUser() +
            "}";
    }
}
