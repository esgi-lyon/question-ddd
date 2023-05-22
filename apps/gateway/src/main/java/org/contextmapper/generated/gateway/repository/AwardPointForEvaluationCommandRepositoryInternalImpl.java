package org.contextmapper.generated.gateway.repository;

import static org.springframework.data.relational.core.query.Criteria.where;

import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.BiFunction;
import org.apache.commons.lang3.StringUtils;
import org.contextmapper.generated.gateway.domain.AwardPointForEvaluationCommand;
import org.contextmapper.generated.gateway.repository.rowmapper.AwardPointForEvaluationCommandRowMapper;
import org.contextmapper.generated.gateway.repository.rowmapper.EvaluationRowMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.convert.R2dbcConverter;
import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.repository.support.SimpleR2dbcRepository;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Comparison;
import org.springframework.data.relational.core.sql.Condition;
import org.springframework.data.relational.core.sql.Conditions;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Select;
import org.springframework.data.relational.core.sql.SelectBuilder.SelectFromAndJoinCondition;
import org.springframework.data.relational.core.sql.Table;
import org.springframework.data.relational.repository.support.MappingRelationalEntityInformation;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.r2dbc.core.RowsFetchSpec;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC custom repository implementation for the AwardPointForEvaluationCommand entity.
 */
@SuppressWarnings("unused")
class AwardPointForEvaluationCommandRepositoryInternalImpl
    extends SimpleR2dbcRepository<AwardPointForEvaluationCommand, Long>
    implements AwardPointForEvaluationCommandRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final EvaluationRowMapper evaluationMapper;
    private final AwardPointForEvaluationCommandRowMapper awardpointforevaluationcommandMapper;

    private static final Table entityTable = Table.aliased("award_point_for_evaluation_command", EntityManager.ENTITY_ALIAS);
    private static final Table evaluationTable = Table.aliased("evaluation", "evaluation");

    public AwardPointForEvaluationCommandRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        EvaluationRowMapper evaluationMapper,
        AwardPointForEvaluationCommandRowMapper awardpointforevaluationcommandMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(
                converter.getMappingContext().getRequiredPersistentEntity(AwardPointForEvaluationCommand.class)
            ),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.evaluationMapper = evaluationMapper;
        this.awardpointforevaluationcommandMapper = awardpointforevaluationcommandMapper;
    }

    @Override
    public Flux<AwardPointForEvaluationCommand> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<AwardPointForEvaluationCommand> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = AwardPointForEvaluationCommandSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(EvaluationSqlHelper.getColumns(evaluationTable, "evaluation"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(evaluationTable)
            .on(Column.create("evaluation_id", entityTable))
            .equals(Column.create("id", evaluationTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, AwardPointForEvaluationCommand.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<AwardPointForEvaluationCommand> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<AwardPointForEvaluationCommand> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    private AwardPointForEvaluationCommand process(Row row, RowMetadata metadata) {
        AwardPointForEvaluationCommand entity = awardpointforevaluationcommandMapper.apply(row, "e");
        entity.setEvaluation(evaluationMapper.apply(row, "evaluation"));
        return entity;
    }

    @Override
    public <S extends AwardPointForEvaluationCommand> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
