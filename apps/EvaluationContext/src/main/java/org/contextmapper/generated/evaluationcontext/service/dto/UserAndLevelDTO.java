package org.contextmapper.generated.evaluationcontext.service.dto;

import java.io.Serializable;
import java.util.Objects;
import org.contextmapper.generated.evaluationcontext.domain.enumeration.UserLevel;

/**
 * A DTO for the {@link org.contextmapper.generated.evaluationcontext.domain.UserAndLevel} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UserAndLevelDTO implements Serializable {

    private Long id;

    private String mail;

    private UserLevel userLevel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public UserLevel getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(UserLevel userLevel) {
        this.userLevel = userLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserAndLevelDTO)) {
            return false;
        }

        UserAndLevelDTO userAndLevelDTO = (UserAndLevelDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, userAndLevelDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserAndLevelDTO{" +
            "id=" + getId() +
            ", mail='" + getMail() + "'" +
            ", userLevel='" + getUserLevel() + "'" +
            "}";
    }
}
