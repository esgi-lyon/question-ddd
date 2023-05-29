package org.contextmapper.generated.statcontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.statcontext.domain.LeaderBoardViewedEvent} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class LeaderBoardViewedEventDTO implements Serializable {

    private Long id;

    private StatisticSubjectTagDTO tag;

    private LeaderBoardDTO newUserLeaderboard;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatisticSubjectTagDTO getTag() {
        return tag;
    }

    public void setTag(StatisticSubjectTagDTO tag) {
        this.tag = tag;
    }

    public LeaderBoardDTO getNewUserLeaderboard() {
        return newUserLeaderboard;
    }

    public void setNewUserLeaderboard(LeaderBoardDTO newUserLeaderboard) {
        this.newUserLeaderboard = newUserLeaderboard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LeaderBoardViewedEventDTO)) {
            return false;
        }

        LeaderBoardViewedEventDTO leaderBoardViewedEventDTO = (LeaderBoardViewedEventDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, leaderBoardViewedEventDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LeaderBoardViewedEventDTO{" +
            "id=" + getId() +
            ", tag=" + getTag() +
            ", newUserLeaderboard=" + getNewUserLeaderboard() +
            "}";
    }
}
