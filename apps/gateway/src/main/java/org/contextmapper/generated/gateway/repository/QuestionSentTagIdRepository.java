package org.contextmapper.generated.gateway.repository;

import org.contextmapper.generated.gateway.domain.QuestionSentTagId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the QuestionSentTagId entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuestionSentTagIdRepository extends ReactiveCrudRepository<QuestionSentTagId, Long>, QuestionSentTagIdRepositoryInternal {
    @Query("SELECT * FROM question_sent_tag_id entity WHERE entity.question_sent_id = :id")
    Flux<QuestionSentTagId> findByQuestionSent(Long id);

    @Query("SELECT * FROM question_sent_tag_id entity WHERE entity.question_sent_id IS NULL")
    Flux<QuestionSentTagId> findAllWhereQuestionSentIsNull();

    @Override
    <S extends QuestionSentTagId> Mono<S> save(S entity);

    @Override
    Flux<QuestionSentTagId> findAll();

    @Override
    Mono<QuestionSentTagId> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface QuestionSentTagIdRepositoryInternal {
    <S extends QuestionSentTagId> Mono<S> save(S entity);

    Flux<QuestionSentTagId> findAllBy(Pageable pageable);

    Flux<QuestionSentTagId> findAll();

    Mono<QuestionSentTagId> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<QuestionSentTagId> findAllBy(Pageable pageable, Criteria criteria);

}
