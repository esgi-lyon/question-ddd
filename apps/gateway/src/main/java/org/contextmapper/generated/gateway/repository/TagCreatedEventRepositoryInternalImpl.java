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
import org.contextmapper.generated.gateway.domain.TagCreatedEvent;
import org.contextmapper.generated.gateway.repository.rowmapper.TagCreatedEventRowMapper;
import org.contextmapper.generated.gateway.repository.rowmapper.TagInfosRowMapper;
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
 * Spring Data R2DBC custom repository implementation for the TagCreatedEvent entity.
 */
@SuppressWarnings("unused")
class TagCreatedEventRepositoryInternalImpl
    extends SimpleR2dbcRepository<TagCreatedEvent, Long>
    implements TagCreatedEventRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final TagInfosRowMapper taginfosMapper;
    private final TagCreatedEventRowMapper tagcreatedeventMapper;

    private static final Table entityTable = Table.aliased("tag_created_event", EntityManager.ENTITY_ALIAS);
    private static final Table tagIdTable = Table.aliased("tag_infos", "tagId");

    public TagCreatedEventRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        TagInfosRowMapper taginfosMapper,
        TagCreatedEventRowMapper tagcreatedeventMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(TagCreatedEvent.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.taginfosMapper = taginfosMapper;
        this.tagcreatedeventMapper = tagcreatedeventMapper;
    }

    @Override
    public Flux<TagCreatedEvent> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<TagCreatedEvent> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = TagCreatedEventSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(TagInfosSqlHelper.getColumns(tagIdTable, "tagId"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(tagIdTable)
            .on(Column.create("tag_id_id", entityTable))
            .equals(Column.create("id", tagIdTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, TagCreatedEvent.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<TagCreatedEvent> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<TagCreatedEvent> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    private TagCreatedEvent process(Row row, RowMetadata metadata) {
        TagCreatedEvent entity = tagcreatedeventMapper.apply(row, "e");
        entity.setTagId(taginfosMapper.apply(row, "tagId"));
        return entity;
    }

    @Override
    public <S extends TagCreatedEvent> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
