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
import org.contextmapper.generated.gateway.domain.CreateEvaluationCommand;
import org.contextmapper.generated.gateway.repository.rowmapper.CreateEvaluationCommandRowMapper;
import org.contextmapper.generated.gateway.repository.rowmapper.EvaluatedAnswerRowMapper;
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
 * Spring Data R2DBC custom repository implementation for the CreateEvaluationCommand entity.
 */
@SuppressWarnings("unused")
class CreateEvaluationCommandRepositoryInternalImpl
    extends SimpleR2dbcRepository<CreateEvaluationCommand, Long>
    implements CreateEvaluationCommandRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final EvaluatedAnswerRowMapper evaluatedanswerMapper;
    private final CreateEvaluationCommandRowMapper createevaluationcommandMapper;

    private static final Table entityTable = Table.aliased("create_evaluation_command", EntityManager.ENTITY_ALIAS);
    private static final Table answerTable = Table.aliased("evaluated_answer", "answer");

    public CreateEvaluationCommandRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        EvaluatedAnswerRowMapper evaluatedanswerMapper,
        CreateEvaluationCommandRowMapper createevaluationcommandMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(
                converter.getMappingContext().getRequiredPersistentEntity(CreateEvaluationCommand.class)
            ),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.evaluatedanswerMapper = evaluatedanswerMapper;
        this.createevaluationcommandMapper = createevaluationcommandMapper;
    }

    @Override
    public Flux<CreateEvaluationCommand> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<CreateEvaluationCommand> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = CreateEvaluationCommandSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(EvaluatedAnswerSqlHelper.getColumns(answerTable, "answer"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(answerTable)
            .on(Column.create("answer_id", entityTable))
            .equals(Column.create("id", answerTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, CreateEvaluationCommand.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<CreateEvaluationCommand> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<CreateEvaluationCommand> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    private CreateEvaluationCommand process(Row row, RowMetadata metadata) {
        CreateEvaluationCommand entity = createevaluationcommandMapper.apply(row, "e");
        entity.setAnswer(evaluatedanswerMapper.apply(row, "answer"));
        return entity;
    }

    @Override
    public <S extends CreateEvaluationCommand> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
