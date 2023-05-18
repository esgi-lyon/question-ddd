package org.contextmapper.generated.statcontext.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A UserStatsViewedEvent.
 */
@Entity
@Table(name = "user_stats_viewed_event")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UserStatsViewedEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(unique = true)
    private StatisticSubjectUser user;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public UserStatsViewedEvent id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatisticSubjectUser getUser() {
        return this.user;
    }

    public void setUser(StatisticSubjectUser statisticSubjectUser) {
        this.user = statisticSubjectUser;
    }

    public UserStatsViewedEvent user(StatisticSubjectUser statisticSubjectUser) {
        this.setUser(statisticSubjectUser);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserStatsViewedEvent)) {
            return false;
        }
        return id != null && id.equals(((UserStatsViewedEvent) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserStatsViewedEvent{" +
            "id=" + getId() +
            "}";
    }
}
