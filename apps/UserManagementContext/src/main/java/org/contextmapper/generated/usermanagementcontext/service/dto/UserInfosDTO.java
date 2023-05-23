package org.contextmapper.generated.usermanagementcontext.service.dto;

import java.io.Serializable;
import java.util.Objects;
import org.contextmapper.generated.usermanagementcontext.domain.enumeration.Roles;
import org.contextmapper.generated.usermanagementcontext.domain.enumeration.UserStatus;

/**
 * A DTO for the {@link org.contextmapper.generated.usermanagementcontext.domain.UserInfos} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UserInfosDTO implements Serializable {

    private Long id;

    private String firstname;

    private String lastname;

    private String password;

    private String mail;

    private Roles role;

    private UserStatus status;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserInfosDTO)) {
            return false;
        }

        UserInfosDTO userInfosDTO = (UserInfosDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, userInfosDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserInfosDTO{" +
            "id=" + getId() +
            ", firstname='" + getFirstname() + "'" +
            ", lastname='" + getLastname() + "'" +
            ", password='" + getPassword() + "'" +
            ", mail='" + getMail() + "'" +
            ", role='" + getRole() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
