package org.contextmapper.generated.statcontext.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ViewStats.
 */
@Entity
@Table(name = "view_stats")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ViewStats implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(unique = true)
    private StatisticSubjectUser user;

    @OneToOne
    @JoinColumn(unique = true)
    private StatisticSubjectQuestion question;

    @OneToOne
    @JoinColumn(unique = true)
    private StatisticSubjectTag tag;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ViewStats id(Long id) {
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

    public ViewStats user(StatisticSubjectUser statisticSubjectUser) {
        this.setUser(statisticSubjectUser);
        return this;
    }

    public StatisticSubjectQuestion getQuestion() {
        return this.question;
    }

    public void setQuestion(StatisticSubjectQuestion statisticSubjectQuestion) {
        this.question = statisticSubjectQuestion;
    }

    public ViewStats question(StatisticSubjectQuestion statisticSubjectQuestion) {
        this.setQuestion(statisticSubjectQuestion);
        return this;
    }

    public StatisticSubjectTag getTag() {
        return this.tag;
    }

    public void setTag(StatisticSubjectTag statisticSubjectTag) {
        this.tag = statisticSubjectTag;
    }

    public ViewStats tag(StatisticSubjectTag statisticSubjectTag) {
        this.setTag(statisticSubjectTag);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ViewStats)) {
            return false;
        }
        return id != null && id.equals(((ViewStats) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ViewStats{" +
            "id=" + getId() +
            "}";
    }
}
