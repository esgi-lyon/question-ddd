package org.contextmapper.generated.usermanagementcontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.usermanagementcontext.domain.UserRejectedEvent} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UserRejectedEventDTO implements Serializable {

    private Long id;

    private UserInfosDTO userInfos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserInfosDTO getUserInfos() {
        return userInfos;
    }

    public void setUserInfos(UserInfosDTO userInfos) {
        this.userInfos = userInfos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserRejectedEventDTO)) {
            return false;
        }

        UserRejectedEventDTO userRejectedEventDTO = (UserRejectedEventDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, userRejectedEventDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserRejectedEventDTO{" +
            "id=" + getId() +
            ", userInfos=" + getUserInfos() +
            "}";
    }
}
