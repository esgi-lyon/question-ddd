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
import org.contextmapper.generated.gateway.domain.Question;
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
 * Spring Data R2DBC custom repository implementation for the Question entity.
 */
@SuppressWarnings("unused")
class QuestionRepositoryInternalImpl extends SimpleR2dbcRepository<Question, Long> implements QuestionRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final QuestionSentTagInfosRowMapper questionsenttaginfosMapper;
    private final QuestionRowMapper questionMapper;

    private static final Table entityTable = Table.aliased("question", EntityManager.ENTITY_ALIAS);
    private static final Table resourceCorrectTagTable = Table.aliased("question_sent_tag_infos", "resourceCorrectTag");

    public QuestionRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        QuestionSentTagInfosRowMapper questionsenttaginfosMapper,
        QuestionRowMapper questionMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(Question.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.questionsenttaginfosMapper = questionsenttaginfosMapper;
        this.questionMapper = questionMapper;
    }

    @Override
    public Flux<Question> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<Question> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = QuestionSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(QuestionSentTagInfosSqlHelper.getColumns(resourceCorrectTagTable, "resourceCorrectTag"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(resourceCorrectTagTable)
            .on(Column.create("resource_correct_tag_id", entityTable))
            .equals(Column.create("id", resourceCorrectTagTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, Question.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<Question> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<Question> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    private Question process(Row row, RowMetadata metadata) {
        Question entity = questionMapper.apply(row, "e");
        entity.setResourceCorrectTag(questionsenttaginfosMapper.apply(row, "resourceCorrectTag"));
        return entity;
    }

    @Override
    public <S extends Question> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
