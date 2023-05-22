package org.contextmapper.generated.gateway.repository;

import org.contextmapper.generated.gateway.domain.QuestionResourceTagInfos;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the QuestionResourceTagInfos entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuestionResourceTagInfosRepository
    extends ReactiveCrudRepository<QuestionResourceTagInfos, Long>, QuestionResourceTagInfosRepositoryInternal {
    @Override
    <S extends QuestionResourceTagInfos> Mono<S> save(S entity);

    @Override
    Flux<QuestionResourceTagInfos> findAll();

    @Override
    Mono<QuestionResourceTagInfos> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface QuestionResourceTagInfosRepositoryInternal {
    <S extends QuestionResourceTagInfos> Mono<S> save(S entity);

    Flux<QuestionResourceTagInfos> findAllBy(Pageable pageable);

    Flux<QuestionResourceTagInfos> findAll();

    Mono<QuestionResourceTagInfos> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<QuestionResourceTagInfos> findAllBy(Pageable pageable, Criteria criteria);

}
