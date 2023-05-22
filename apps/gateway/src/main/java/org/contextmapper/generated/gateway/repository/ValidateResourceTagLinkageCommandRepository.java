package org.contextmapper.generated.gateway.repository;

import org.contextmapper.generated.gateway.domain.ValidateResourceTagLinkageCommand;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the ValidateResourceTagLinkageCommand entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ValidateResourceTagLinkageCommandRepository
    extends ReactiveCrudRepository<ValidateResourceTagLinkageCommand, Long>, ValidateResourceTagLinkageCommandRepositoryInternal {
    @Query("SELECT * FROM validate_resource_tag_linkage_command entity WHERE entity.question_id_id = :id")
    Flux<ValidateResourceTagLinkageCommand> findByQuestionId(Long id);

    @Query("SELECT * FROM validate_resource_tag_linkage_command entity WHERE entity.question_id_id IS NULL")
    Flux<ValidateResourceTagLinkageCommand> findAllWhereQuestionIdIsNull();

    @Override
    <S extends ValidateResourceTagLinkageCommand> Mono<S> save(S entity);

    @Override
    Flux<ValidateResourceTagLinkageCommand> findAll();

    @Override
    Mono<ValidateResourceTagLinkageCommand> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface ValidateResourceTagLinkageCommandRepositoryInternal {
    <S extends ValidateResourceTagLinkageCommand> Mono<S> save(S entity);

    Flux<ValidateResourceTagLinkageCommand> findAllBy(Pageable pageable);

    Flux<ValidateResourceTagLinkageCommand> findAll();

    Mono<ValidateResourceTagLinkageCommand> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<ValidateResourceTagLinkageCommand> findAllBy(Pageable pageable, Criteria criteria);

}
