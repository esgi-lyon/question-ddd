package org.contextmapper.generated.gateway.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.gateway.domain.UserStatsViewedEvent} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UserStatsViewedEventDTO implements Serializable {

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
        if (!(o instanceof UserStatsViewedEventDTO)) {
            return false;
        }

        UserStatsViewedEventDTO userStatsViewedEventDTO = (UserStatsViewedEventDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, userStatsViewedEventDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserStatsViewedEventDTO{" +
            "id=" + getId() +
            ", user=" + getUser() +
            "}";
    }
}
