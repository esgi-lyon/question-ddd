package org.contextmapper.generated.usermanagementcontext.service.dto;

import java.io.Serializable;
import java.util.Objects;
import org.contextmapper.generated.usermanagementcontext.domain.enumeration.Roles;

/**
 * A DTO for the {@link org.contextmapper.generated.usermanagementcontext.domain.RegisterCommand} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RegisterCommandDTO implements Serializable {

    private Long id;

    private String firstname;

    private String lastname;

    private String mail;

    private String password;

    private Roles role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RegisterCommandDTO)) {
            return false;
        }

        RegisterCommandDTO registerCommandDTO = (RegisterCommandDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, registerCommandDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RegisterCommandDTO{" +
            "id=" + getId() +
            ", firstname='" + getFirstname() + "'" +
            ", lastname='" + getLastname() + "'" +
            ", mail='" + getMail() + "'" +
            ", password='" + getPassword() + "'" +
            ", role='" + getRole() + "'" +
            "}";
    }
}
