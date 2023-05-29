package org.contextmapper.generated.statcontext.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A LeaderBoard.
 */
@Entity
@Table(name = "leader_board")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class LeaderBoard implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "leaderBoard")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "users", "leaderBoard" }, allowSetters = true)
    private Set<LeaderBoardEntry> entries = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public LeaderBoard id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<LeaderBoardEntry> getEntries() {
        return this.entries;
    }

    public void setEntries(Set<LeaderBoardEntry> leaderBoardEntries) {
        if (this.entries != null) {
            this.entries.forEach(i -> i.setLeaderBoard(null));
        }
        if (leaderBoardEntries != null) {
            leaderBoardEntries.forEach(i -> i.setLeaderBoard(this));
        }
        this.entries = leaderBoardEntries;
    }

    public LeaderBoard entries(Set<LeaderBoardEntry> leaderBoardEntries) {
        this.setEntries(leaderBoardEntries);
        return this;
    }

    public LeaderBoard addEntries(LeaderBoardEntry leaderBoardEntry) {
        this.entries.add(leaderBoardEntry);
        leaderBoardEntry.setLeaderBoard(this);
        return this;
    }

    public LeaderBoard removeEntries(LeaderBoardEntry leaderBoardEntry) {
        this.entries.remove(leaderBoardEntry);
        leaderBoardEntry.setLeaderBoard(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LeaderBoard)) {
            return false;
        }
        return id != null && id.equals(((LeaderBoard) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LeaderBoard{" +
            "id=" + getId() +
            "}";
    }
}
