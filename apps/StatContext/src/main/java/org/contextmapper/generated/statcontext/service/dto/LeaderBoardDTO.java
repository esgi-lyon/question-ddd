package org.contextmapper.generated.statcontext.service.dto;

import java.io.Serializable;
import java.util.Objects;
import org.contextmapper.generated.statcontext.domain.enumeration.DifficultyLevel;

/**
 * A DTO for the {@link org.contextmapper.generated.statcontext.domain.LeaderBoard} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class LeaderBoardDTO implements Serializable {

    private Long id;

    private DifficultyLevel difficultyLevel;

    private StatEvaluationTagDTO tagId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public StatEvaluationTagDTO getTagId() {
        return tagId;
    }

    public void setTagId(StatEvaluationTagDTO tagId) {
        this.tagId = tagId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LeaderBoardDTO)) {
            return false;
        }

        LeaderBoardDTO leaderBoardDTO = (LeaderBoardDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, leaderBoardDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LeaderBoardDTO{" +
            "id=" + getId() +
            ", difficultyLevel='" + getDifficultyLevel() + "'" +
            ", tagId=" + getTagId() +
            "}";
    }
}
