package org.contextmapper.generated.gateway.repository;

import org.contextmapper.generated.gateway.domain.AwardedPointEvent;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the AwardedPointEvent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AwardedPointEventRepository extends ReactiveCrudRepository<AwardedPointEvent, Long>, AwardedPointEventRepositoryInternal {
    @Query("SELECT * FROM awarded_point_event entity WHERE entity.answer_id = :id")
    Flux<AwardedPointEvent> findByAnswer(Long id);

    @Query("SELECT * FROM awarded_point_event entity WHERE entity.answer_id IS NULL")
    Flux<AwardedPointEvent> findAllWhereAnswerIsNull();

    @Override
    <S extends AwardedPointEvent> Mono<S> save(S entity);

    @Override
    Flux<AwardedPointEvent> findAll();

    @Override
    Mono<AwardedPointEvent> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface AwardedPointEventRepositoryInternal {
    <S extends AwardedPointEvent> Mono<S> save(S entity);

    Flux<AwardedPointEvent> findAllBy(Pageable pageable);

    Flux<AwardedPointEvent> findAll();

    Mono<AwardedPointEvent> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<AwardedPointEvent> findAllBy(Pageable pageable, Criteria criteria);

}
