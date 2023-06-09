package org.contextmapper.generated.statcontext.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A LoggedUserStatsViewedEvent.
 */
@Entity
@Table(name = "logged_user_stats_viewed_event")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class LoggedUserStatsViewedEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @JsonIgnoreProperties(value = { "evaluations" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private EvaluationStats stat;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public LoggedUserStatsViewedEvent id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EvaluationStats getStat() {
        return this.stat;
    }

    public void setStat(EvaluationStats evaluationStats) {
        this.stat = evaluationStats;
    }

    public LoggedUserStatsViewedEvent stat(EvaluationStats evaluationStats) {
        this.setStat(evaluationStats);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LoggedUserStatsViewedEvent)) {
            return false;
        }
        return id != null && id.equals(((LoggedUserStatsViewedEvent) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LoggedUserStatsViewedEvent{" +
            "id=" + getId() +
            "}";
    }
}
