package org.contextmapper.generated.gateway.domain;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A TagStatsViewedEvent.
 */
@Table("tag_stats_viewed_event")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TagStatsViewedEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Transient
    private StatisticSubjectTag tag;

    @Column("tag_id")
    private Long tagId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TagStatsViewedEvent id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatisticSubjectTag getTag() {
        return this.tag;
    }

    public void setTag(StatisticSubjectTag statisticSubjectTag) {
        this.tag = statisticSubjectTag;
        this.tagId = statisticSubjectTag != null ? statisticSubjectTag.getId() : null;
    }

    public TagStatsViewedEvent tag(StatisticSubjectTag statisticSubjectTag) {
        this.setTag(statisticSubjectTag);
        return this;
    }

    public Long getTagId() {
        return this.tagId;
    }

    public void setTagId(Long statisticSubjectTag) {
        this.tagId = statisticSubjectTag;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TagStatsViewedEvent)) {
            return false;
        }
        return id != null && id.equals(((TagStatsViewedEvent) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TagStatsViewedEvent{" +
            "id=" + getId() +
            "}";
    }
}
