package org.contextmapper.generated.sendquestioncontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.sendquestioncontext.domain.UserWithPreferencesId} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UserWithPreferencesIdDTO implements Serializable {

    private Long id;

    private String mail;

    private NotifiedUsersDTO notifiedUsers;

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

    public NotifiedUsersDTO getNotifiedUsers() {
        return notifiedUsers;
    }

    public void setNotifiedUsers(NotifiedUsersDTO notifiedUsers) {
        this.notifiedUsers = notifiedUsers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserWithPreferencesIdDTO)) {
            return false;
        }

        UserWithPreferencesIdDTO userWithPreferencesIdDTO = (UserWithPreferencesIdDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, userWithPreferencesIdDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserWithPreferencesIdDTO{" +
            "id=" + getId() +
            ", mail='" + getMail() + "'" +
            ", notifiedUsers=" + getNotifiedUsers() +
            "}";
    }
}
