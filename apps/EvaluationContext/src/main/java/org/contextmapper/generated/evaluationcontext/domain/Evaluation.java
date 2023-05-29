package org.contextmapper.generated.evaluationcontext.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.contextmapper.generated.evaluationcontext.domain.enumeration.DifficultyLevel;
import org.contextmapper.generated.evaluationcontext.domain.enumeration.Status;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Evaluation.
 */
@Entity
@Table(name = "evaluation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Evaluation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "score")
    private Integer score;

    @Column(name = "evaluator_mail")
    private String evaluatorMail;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(name = "answered_question_difficulty_level")
    private DifficultyLevel answeredQuestionDifficultyLevel;

    @OneToOne
    @JoinColumn(unique = true)
    private EvaluationTag tag;

    @OneToOne
    @JoinColumn(unique = true)
    private EvaluationQuestion question;

    @OneToOne
    @JoinColumn(unique = true)
    private AnsweringUser user;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Evaluation id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getScore() {
        return this.score;
    }

    public Evaluation score(Integer score) {
        this.setScore(score);
        return this;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getEvaluatorMail() {
        return this.evaluatorMail;
    }

    public Evaluation evaluatorMail(String evaluatorMail) {
        this.setEvaluatorMail(evaluatorMail);
        return this;
    }

    public void setEvaluatorMail(String evaluatorMail) {
        this.evaluatorMail = evaluatorMail;
    }

    public Status getStatus() {
        return this.status;
    }

    public Evaluation status(Status status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public DifficultyLevel getAnsweredQuestionDifficultyLevel() {
        return this.answeredQuestionDifficultyLevel;
    }

    public Evaluation answeredQuestionDifficultyLevel(DifficultyLevel answeredQuestionDifficultyLevel) {
        this.setAnsweredQuestionDifficultyLevel(answeredQuestionDifficultyLevel);
        return this;
    }

    public void setAnsweredQuestionDifficultyLevel(DifficultyLevel answeredQuestionDifficultyLevel) {
        this.answeredQuestionDifficultyLevel = answeredQuestionDifficultyLevel;
    }

    public EvaluationTag getTag() {
        return this.tag;
    }

    public void setTag(EvaluationTag evaluationTag) {
        this.tag = evaluationTag;
    }

    public Evaluation tag(EvaluationTag evaluationTag) {
        this.setTag(evaluationTag);
        return this;
    }

    public EvaluationQuestion getQuestion() {
        return this.question;
    }

    public void setQuestion(EvaluationQuestion evaluationQuestion) {
        this.question = evaluationQuestion;
    }

    public Evaluation question(EvaluationQuestion evaluationQuestion) {
        this.setQuestion(evaluationQuestion);
        return this;
    }

    public AnsweringUser getUser() {
        return this.user;
    }

    public void setUser(AnsweringUser answeringUser) {
        this.user = answeringUser;
    }

    public Evaluation user(AnsweringUser answeringUser) {
        this.setUser(answeringUser);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Evaluation)) {
            return false;
        }
        return id != null && id.equals(((Evaluation) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Evaluation{" +
            "id=" + getId() +
            ", score=" + getScore() +
            ", evaluatorMail='" + getEvaluatorMail() + "'" +
            ", status='" + getStatus() + "'" +
            ", answeredQuestionDifficultyLevel='" + getAnsweredQuestionDifficultyLevel() + "'" +
            "}";
    }
}
