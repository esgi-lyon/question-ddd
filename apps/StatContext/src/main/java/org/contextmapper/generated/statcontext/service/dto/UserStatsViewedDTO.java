package org.contextmapper.generated.statcontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.statcontext.domain.UserStatsViewed} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UserStatsViewedDTO implements Serializable {

    private Long id;

    private StatisticSubjectUserDTO user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatisticSubjectUserDTO getUser() {
        return user;
    }

    public void setUser(StatisticSubjectUserDTO user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserStatsViewedDTO)) {
            return false;
        }

        UserStatsViewedDTO userStatsViewedDTO = (UserStatsViewedDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, userStatsViewedDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserStatsViewedDTO{" +
            "id=" + getId() +
            ", user=" + getUser() +
            "}";
    }
}
