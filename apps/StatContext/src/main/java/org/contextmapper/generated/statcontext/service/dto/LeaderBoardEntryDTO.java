package org.contextmapper.generated.statcontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.statcontext.domain.LeaderBoardEntry} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class LeaderBoardEntryDTO implements Serializable {

    private Long id;

    private String userLevel;

    private Integer score;

    private StatisticSubjectUserDTO users;

    private LeaderBoardDTO leaderBoard;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public StatisticSubjectUserDTO getUsers() {
        return users;
    }

    public void setUsers(StatisticSubjectUserDTO users) {
        this.users = users;
    }

    public LeaderBoardDTO getLeaderBoard() {
        return leaderBoard;
    }

    public void setLeaderBoard(LeaderBoardDTO leaderBoard) {
        this.leaderBoard = leaderBoard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LeaderBoardEntryDTO)) {
            return false;
        }

        LeaderBoardEntryDTO leaderBoardEntryDTO = (LeaderBoardEntryDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, leaderBoardEntryDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LeaderBoardEntryDTO{" +
            "id=" + getId() +
            ", userLevel='" + getUserLevel() + "'" +
            ", score=" + getScore() +
            ", users=" + getUsers() +
            ", leaderBoard=" + getLeaderBoard() +
            "}";
    }
}
