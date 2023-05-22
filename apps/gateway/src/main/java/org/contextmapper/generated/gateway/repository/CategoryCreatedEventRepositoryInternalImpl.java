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
import org.contextmapper.generated.gateway.domain.CategoryCreatedEvent;
import org.contextmapper.generated.gateway.repository.rowmapper.CategoryCreatedEventRowMapper;
import org.contextmapper.generated.gateway.repository.rowmapper.CategoryIdRowMapper;
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
 * Spring Data R2DBC custom repository implementation for the CategoryCreatedEvent entity.
 */
@SuppressWarnings("unused")
class CategoryCreatedEventRepositoryInternalImpl
    extends SimpleR2dbcRepository<CategoryCreatedEvent, Long>
    implements CategoryCreatedEventRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final CategoryIdRowMapper categoryidMapper;
    private final CategoryCreatedEventRowMapper categorycreatedeventMapper;

    private static final Table entityTable = Table.aliased("category_created_event", EntityManager.ENTITY_ALIAS);
    private static final Table categoryIdTable = Table.aliased("category_id", "categoryId");

    public CategoryCreatedEventRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        CategoryIdRowMapper categoryidMapper,
        CategoryCreatedEventRowMapper categorycreatedeventMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(CategoryCreatedEvent.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.categoryidMapper = categoryidMapper;
        this.categorycreatedeventMapper = categorycreatedeventMapper;
    }

    @Override
    public Flux<CategoryCreatedEvent> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<CategoryCreatedEvent> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = CategoryCreatedEventSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(CategoryIdSqlHelper.getColumns(categoryIdTable, "categoryId"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(categoryIdTable)
            .on(Column.create("category_id_id", entityTable))
            .equals(Column.create("id", categoryIdTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, CategoryCreatedEvent.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<CategoryCreatedEvent> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<CategoryCreatedEvent> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    private CategoryCreatedEvent process(Row row, RowMetadata metadata) {
        CategoryCreatedEvent entity = categorycreatedeventMapper.apply(row, "e");
        entity.setCategoryId(categoryidMapper.apply(row, "categoryId"));
        return entity;
    }

    @Override
    public <S extends CategoryCreatedEvent> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
