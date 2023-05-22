package org.contextmapper.generated.gateway.repository;

import org.contextmapper.generated.gateway.domain.CheckAnswerCommand;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the CheckAnswerCommand entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CheckAnswerCommandRepository
    extends ReactiveCrudRepository<CheckAnswerCommand, Long>, CheckAnswerCommandRepositoryInternal {
    @Query("SELECT * FROM check_answer_command entity WHERE entity.answer_id = :id")
    Flux<CheckAnswerCommand> findByAnswer(Long id);

    @Query("SELECT * FROM check_answer_command entity WHERE entity.answer_id IS NULL")
    Flux<CheckAnswerCommand> findAllWhereAnswerIsNull();

    @Override
    <S extends CheckAnswerCommand> Mono<S> save(S entity);

    @Override
    Flux<CheckAnswerCommand> findAll();

    @Override
    Mono<CheckAnswerCommand> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface CheckAnswerCommandRepositoryInternal {
    <S extends CheckAnswerCommand> Mono<S> save(S entity);

    Flux<CheckAnswerCommand> findAllBy(Pageable pageable);

    Flux<CheckAnswerCommand> findAll();

    Mono<CheckAnswerCommand> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<CheckAnswerCommand> findAllBy(Pageable pageable, Criteria criteria);

}
