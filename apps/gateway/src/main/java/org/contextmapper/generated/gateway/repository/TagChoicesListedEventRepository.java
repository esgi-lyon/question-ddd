package org.contextmapper.generated.gateway.repository;

import org.contextmapper.generated.gateway.domain.TagChoicesListedEvent;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the TagChoicesListedEvent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TagChoicesListedEventRepository
    extends ReactiveCrudRepository<TagChoicesListedEvent, Long>, TagChoicesListedEventRepositoryInternal {
    @Override
    <S extends TagChoicesListedEvent> Mono<S> save(S entity);

    @Override
    Flux<TagChoicesListedEvent> findAll();

    @Override
    Mono<TagChoicesListedEvent> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface TagChoicesListedEventRepositoryInternal {
    <S extends TagChoicesListedEvent> Mono<S> save(S entity);

    Flux<TagChoicesListedEvent> findAllBy(Pageable pageable);

    Flux<TagChoicesListedEvent> findAll();

    Mono<TagChoicesListedEvent> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<TagChoicesListedEvent> findAllBy(Pageable pageable, Criteria criteria);

}
