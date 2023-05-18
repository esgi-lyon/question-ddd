package org.contextmapper.generated.evaluationcontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.evaluationcontext.domain.AnsweringUser} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AnsweringUserDTO implements Serializable {

    private Long id;

    private Integer userId;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AnsweringUserDTO)) {
            return false;
        }

        AnsweringUserDTO answeringUserDTO = (AnsweringUserDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, answeringUserDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AnsweringUserDTO{" +
            "id=" + getId() +
            ", userId=" + getUserId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
