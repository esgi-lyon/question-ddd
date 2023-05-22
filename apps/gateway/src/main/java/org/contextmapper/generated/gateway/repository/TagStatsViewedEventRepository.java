package org.contextmapper.generated.gateway.repository;

import org.contextmapper.generated.gateway.domain.TagStatsViewedEvent;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the TagStatsViewedEvent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TagStatsViewedEventRepository
    extends ReactiveCrudRepository<TagStatsViewedEvent, Long>, TagStatsViewedEventRepositoryInternal {
    @Query("SELECT * FROM tag_stats_viewed_event entity WHERE entity.tag_id = :id")
    Flux<TagStatsViewedEvent> findByTag(Long id);

    @Query("SELECT * FROM tag_stats_viewed_event entity WHERE entity.tag_id IS NULL")
    Flux<TagStatsViewedEvent> findAllWhereTagIsNull();

    @Override
    <S extends TagStatsViewedEvent> Mono<S> save(S entity);

    @Override
    Flux<TagStatsViewedEvent> findAll();

    @Override
    Mono<TagStatsViewedEvent> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface TagStatsViewedEventRepositoryInternal {
    <S extends TagStatsViewedEvent> Mono<S> save(S entity);

    Flux<TagStatsViewedEvent> findAllBy(Pageable pageable);

    Flux<TagStatsViewedEvent> findAll();

    Mono<TagStatsViewedEvent> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<TagStatsViewedEvent> findAllBy(Pageable pageable, Criteria criteria);

}
