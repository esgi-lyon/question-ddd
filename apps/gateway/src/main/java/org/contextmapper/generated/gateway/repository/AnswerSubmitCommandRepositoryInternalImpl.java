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
import org.contextmapper.generated.gateway.domain.AnswerSubmitCommand;
import org.contextmapper.generated.gateway.repository.rowmapper.AnswerRowMapper;
import org.contextmapper.generated.gateway.repository.rowmapper.AnswerSubmitCommandRowMapper;
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
 * Spring Data R2DBC custom repository implementation for the AnswerSubmitCommand entity.
 */
@SuppressWarnings("unused")
class AnswerSubmitCommandRepositoryInternalImpl
    extends SimpleR2dbcRepository<AnswerSubmitCommand, Long>
    implements AnswerSubmitCommandRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final AnswerRowMapper answerMapper;
    private final AnswerSubmitCommandRowMapper answersubmitcommandMapper;

    private static final Table entityTable = Table.aliased("answer_submit_command", EntityManager.ENTITY_ALIAS);
    private static final Table answerTable = Table.aliased("answer", "answer");

    public AnswerSubmitCommandRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        AnswerRowMapper answerMapper,
        AnswerSubmitCommandRowMapper answersubmitcommandMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(AnswerSubmitCommand.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.answerMapper = answerMapper;
        this.answersubmitcommandMapper = answersubmitcommandMapper;
    }

    @Override
    public Flux<AnswerSubmitCommand> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<AnswerSubmitCommand> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = AnswerSubmitCommandSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(AnswerSqlHelper.getColumns(answerTable, "answer"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(answerTable)
            .on(Column.create("answer_id", entityTable))
            .equals(Column.create("id", answerTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, AnswerSubmitCommand.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<AnswerSubmitCommand> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<AnswerSubmitCommand> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    private AnswerSubmitCommand process(Row row, RowMetadata metadata) {
        AnswerSubmitCommand entity = answersubmitcommandMapper.apply(row, "e");
        entity.setAnswer(answerMapper.apply(row, "answer"));
        return entity;
    }

    @Override
    public <S extends AnswerSubmitCommand> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
