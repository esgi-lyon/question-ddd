package org.contextmapper.generated.gateway.repository;

import org.contextmapper.generated.gateway.domain.ViewStatsCommand;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the ViewStatsCommand entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ViewStatsCommandRepository extends ReactiveCrudRepository<ViewStatsCommand, Long>, ViewStatsCommandRepositoryInternal {
    @Query("SELECT * FROM view_stats_command entity WHERE entity.user_id = :id")
    Flux<ViewStatsCommand> findByUser(Long id);

    @Query("SELECT * FROM view_stats_command entity WHERE entity.user_id IS NULL")
    Flux<ViewStatsCommand> findAllWhereUserIsNull();

    @Query("SELECT * FROM view_stats_command entity WHERE entity.question_id = :id")
    Flux<ViewStatsCommand> findByQuestion(Long id);

    @Query("SELECT * FROM view_stats_command entity WHERE entity.question_id IS NULL")
    Flux<ViewStatsCommand> findAllWhereQuestionIsNull();

    @Query("SELECT * FROM view_stats_command entity WHERE entity.tag_id = :id")
    Flux<ViewStatsCommand> findByTag(Long id);

    @Query("SELECT * FROM view_stats_command entity WHERE entity.tag_id IS NULL")
    Flux<ViewStatsCommand> findAllWhereTagIsNull();

    @Override
    <S extends ViewStatsCommand> Mono<S> save(S entity);

    @Override
    Flux<ViewStatsCommand> findAll();

    @Override
    Mono<ViewStatsCommand> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface ViewStatsCommandRepositoryInternal {
    <S extends ViewStatsCommand> Mono<S> save(S entity);

    Flux<ViewStatsCommand> findAllBy(Pageable pageable);

    Flux<ViewStatsCommand> findAll();

    Mono<ViewStatsCommand> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<ViewStatsCommand> findAllBy(Pageable pageable, Criteria criteria);

}
