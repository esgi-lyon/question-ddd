package org.contextmapper.generated.gateway.repository;

import org.contextmapper.generated.gateway.domain.CreateQuestionCommand;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the CreateQuestionCommand entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CreateQuestionCommandRepository
    extends ReactiveCrudRepository<CreateQuestionCommand, Long>, CreateQuestionCommandRepositoryInternal {
    @Query("SELECT * FROM create_question_command entity WHERE entity.resource_id = :id")
    Flux<CreateQuestionCommand> findByResource(Long id);

    @Query("SELECT * FROM create_question_command entity WHERE entity.resource_id IS NULL")
    Flux<CreateQuestionCommand> findAllWhereResourceIsNull();

    @Override
    <S extends CreateQuestionCommand> Mono<S> save(S entity);

    @Override
    Flux<CreateQuestionCommand> findAll();

    @Override
    Mono<CreateQuestionCommand> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface CreateQuestionCommandRepositoryInternal {
    <S extends CreateQuestionCommand> Mono<S> save(S entity);

    Flux<CreateQuestionCommand> findAllBy(Pageable pageable);

    Flux<CreateQuestionCommand> findAll();

    Mono<CreateQuestionCommand> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<CreateQuestionCommand> findAllBy(Pageable pageable, Criteria criteria);

}
