package org.contextmapper.generated.statcontext.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A LeaderBoardEntry.
 */
@Entity
@Table(name = "leader_board_entry")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class LeaderBoardEntry implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "user_level")
    private String userLevel;

    @Column(name = "score")
    private Integer score;

    @OneToOne
    @JoinColumn(unique = true)
    private StatisticSubjectUser users;

    @ManyToOne
    @JsonIgnoreProperties(value = { "entries" }, allowSetters = true)
    private LeaderBoard leaderBoard;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public LeaderBoardEntry id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserLevel() {
        return this.userLevel;
    }

    public LeaderBoardEntry userLevel(String userLevel) {
        this.setUserLevel(userLevel);
        return this;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public Integer getScore() {
        return this.score;
    }

    public LeaderBoardEntry score(Integer score) {
        this.setScore(score);
        return this;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public StatisticSubjectUser getUsers() {
        return this.users;
    }

    public void setUsers(StatisticSubjectUser statisticSubjectUser) {
        this.users = statisticSubjectUser;
    }

    public LeaderBoardEntry users(StatisticSubjectUser statisticSubjectUser) {
        this.setUsers(statisticSubjectUser);
        return this;
    }

    public LeaderBoard getLeaderBoard() {
        return this.leaderBoard;
    }

    public void setLeaderBoard(LeaderBoard leaderBoard) {
        this.leaderBoard = leaderBoard;
    }

    public LeaderBoardEntry leaderBoard(LeaderBoard leaderBoard) {
        this.setLeaderBoard(leaderBoard);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LeaderBoardEntry)) {
            return false;
        }
        return id != null && id.equals(((LeaderBoardEntry) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LeaderBoardEntry{" +
            "id=" + getId() +
            ", userLevel='" + getUserLevel() + "'" +
            ", score=" + getScore() +
            "}";
    }
}
