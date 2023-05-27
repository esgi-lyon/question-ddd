package org.contextmapper.generated.usermanagementcontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.usermanagementcontext.domain.ViewUserByEmailCommand} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ViewUserByEmailCommandDTO implements Serializable {

    private Long id;

    private String mail;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ViewUserByEmailCommandDTO)) {
            return false;
        }

        ViewUserByEmailCommandDTO viewUserByEmailCommandDTO = (ViewUserByEmailCommandDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, viewUserByEmailCommandDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ViewUserByEmailCommandDTO{" +
            "id=" + getId() +
            ", mail='" + getMail() + "'" +
            "}";
    }
}
