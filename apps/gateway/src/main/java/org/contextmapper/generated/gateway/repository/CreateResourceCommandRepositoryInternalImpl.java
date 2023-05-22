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
import org.contextmapper.generated.gateway.domain.CreateResourceCommand;
import org.contextmapper.generated.gateway.repository.rowmapper.CreateResourceCommandRowMapper;
import org.contextmapper.generated.gateway.repository.rowmapper.QuestionResourceRowMapper;
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
 * Spring Data R2DBC custom repository implementation for the CreateResourceCommand entity.
 */
@SuppressWarnings("unused")
class CreateResourceCommandRepositoryInternalImpl
    extends SimpleR2dbcRepository<CreateResourceCommand, Long>
    implements CreateResourceCommandRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final QuestionResourceRowMapper questionresourceMapper;
    private final CreateResourceCommandRowMapper createresourcecommandMapper;

    private static final Table entityTable = Table.aliased("create_resource_command", EntityManager.ENTITY_ALIAS);
    private static final Table questionIdTable = Table.aliased("question_resource", "questionId");

    public CreateResourceCommandRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        QuestionResourceRowMapper questionresourceMapper,
        CreateResourceCommandRowMapper createresourcecommandMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(CreateResourceCommand.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.questionresourceMapper = questionresourceMapper;
        this.createresourcecommandMapper = createresourcecommandMapper;
    }

    @Override
    public Flux<CreateResourceCommand> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<CreateResourceCommand> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = CreateResourceCommandSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(QuestionResourceSqlHelper.getColumns(questionIdTable, "questionId"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(questionIdTable)
            .on(Column.create("question_id_id", entityTable))
            .equals(Column.create("id", questionIdTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, CreateResourceCommand.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<CreateResourceCommand> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<CreateResourceCommand> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    private CreateResourceCommand process(Row row, RowMetadata metadata) {
        CreateResourceCommand entity = createresourcecommandMapper.apply(row, "e");
        entity.setQuestionId(questionresourceMapper.apply(row, "questionId"));
        return entity;
    }

    @Override
    public <S extends CreateResourceCommand> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
