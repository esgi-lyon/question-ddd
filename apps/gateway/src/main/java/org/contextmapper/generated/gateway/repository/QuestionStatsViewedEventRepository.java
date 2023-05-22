package org.contextmapper.generated.gateway.repository;

import org.contextmapper.generated.gateway.domain.QuestionStatsViewedEvent;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the QuestionStatsViewedEvent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuestionStatsViewedEventRepository
    extends ReactiveCrudRepository<QuestionStatsViewedEvent, Long>, QuestionStatsViewedEventRepositoryInternal {
    @Query("SELECT * FROM question_stats_viewed_event entity WHERE entity.question_id = :id")
    Flux<QuestionStatsViewedEvent> findByQuestion(Long id);

    @Query("SELECT * FROM question_stats_viewed_event entity WHERE entity.question_id IS NULL")
    Flux<QuestionStatsViewedEvent> findAllWhereQuestionIsNull();

    @Override
    <S extends QuestionStatsViewedEvent> Mono<S> save(S entity);

    @Override
    Flux<QuestionStatsViewedEvent> findAll();

    @Override
    Mono<QuestionStatsViewedEvent> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface QuestionStatsViewedEventRepositoryInternal {
    <S extends QuestionStatsViewedEvent> Mono<S> save(S entity);

    Flux<QuestionStatsViewedEvent> findAllBy(Pageable pageable);

    Flux<QuestionStatsViewedEvent> findAll();

    Mono<QuestionStatsViewedEvent> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<QuestionStatsViewedEvent> findAllBy(Pageable pageable, Criteria criteria);

}
