package org.contextmapper.generated.gateway.repository;

import org.contextmapper.generated.gateway.domain.TagChoicesListCommand;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the TagChoicesListCommand entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TagChoicesListCommandRepository
    extends ReactiveCrudRepository<TagChoicesListCommand, Long>, TagChoicesListCommandRepositoryInternal {
    @Override
    <S extends TagChoicesListCommand> Mono<S> save(S entity);

    @Override
    Flux<TagChoicesListCommand> findAll();

    @Override
    Mono<TagChoicesListCommand> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface TagChoicesListCommandRepositoryInternal {
    <S extends TagChoicesListCommand> Mono<S> save(S entity);

    Flux<TagChoicesListCommand> findAllBy(Pageable pageable);

    Flux<TagChoicesListCommand> findAll();

    Mono<TagChoicesListCommand> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<TagChoicesListCommand> findAllBy(Pageable pageable, Criteria criteria);

}
