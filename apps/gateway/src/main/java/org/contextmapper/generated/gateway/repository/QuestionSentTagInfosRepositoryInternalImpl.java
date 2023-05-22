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
import org.contextmapper.generated.gateway.domain.QuestionSentTagInfos;
import org.contextmapper.generated.gateway.repository.rowmapper.QuestionRowMapper;
import org.contextmapper.generated.gateway.repository.rowmapper.QuestionSentTagInfosRowMapper;
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
 * Spring Data R2DBC custom repository implementation for the QuestionSentTagInfos entity.
 */
@SuppressWarnings("unused")
class QuestionSentTagInfosRepositoryInternalImpl
    extends SimpleR2dbcRepository<QuestionSentTagInfos, Long>
    implements QuestionSentTagInfosRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final QuestionRowMapper questionMapper;
    private final QuestionSentTagInfosRowMapper questionsenttaginfosMapper;

    private static final Table entityTable = Table.aliased("question_sent_tag_infos", EntityManager.ENTITY_ALIAS);
    private static final Table questionTable = Table.aliased("question", "question");

    public QuestionSentTagInfosRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        QuestionRowMapper questionMapper,
        QuestionSentTagInfosRowMapper questionsenttaginfosMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(QuestionSentTagInfos.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.questionMapper = questionMapper;
        this.questionsenttaginfosMapper = questionsenttaginfosMapper;
    }

    @Override
    public Flux<QuestionSentTagInfos> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<QuestionSentTagInfos> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = QuestionSentTagInfosSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(QuestionSqlHelper.getColumns(questionTable, "question"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(questionTable)
            .on(Column.create("question_id", entityTable))
            .equals(Column.create("id", questionTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, QuestionSentTagInfos.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<QuestionSentTagInfos> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<QuestionSentTagInfos> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    private QuestionSentTagInfos process(Row row, RowMetadata metadata) {
        QuestionSentTagInfos entity = questionsenttaginfosMapper.apply(row, "e");
        entity.setQuestion(questionMapper.apply(row, "question"));
        return entity;
    }

    @Override
    public <S extends QuestionSentTagInfos> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
