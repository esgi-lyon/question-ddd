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
import org.contextmapper.generated.gateway.domain.CreateTagCommand;
import org.contextmapper.generated.gateway.repository.rowmapper.CreateTagCommandRowMapper;
import org.contextmapper.generated.gateway.repository.rowmapper.TagRowMapper;
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
 * Spring Data R2DBC custom repository implementation for the CreateTagCommand entity.
 */
@SuppressWarnings("unused")
class CreateTagCommandRepositoryInternalImpl
    extends SimpleR2dbcRepository<CreateTagCommand, Long>
    implements CreateTagCommandRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final TagRowMapper tagMapper;
    private final CreateTagCommandRowMapper createtagcommandMapper;

    private static final Table entityTable = Table.aliased("create_tag_command", EntityManager.ENTITY_ALIAS);
    private static final Table tagTable = Table.aliased("tag", "tag");

    public CreateTagCommandRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        TagRowMapper tagMapper,
        CreateTagCommandRowMapper createtagcommandMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(CreateTagCommand.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.tagMapper = tagMapper;
        this.createtagcommandMapper = createtagcommandMapper;
    }

    @Override
    public Flux<CreateTagCommand> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<CreateTagCommand> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = CreateTagCommandSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(TagSqlHelper.getColumns(tagTable, "tag"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(tagTable)
            .on(Column.create("tag_id", entityTable))
            .equals(Column.create("id", tagTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, CreateTagCommand.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<CreateTagCommand> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<CreateTagCommand> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    private CreateTagCommand process(Row row, RowMetadata metadata) {
        CreateTagCommand entity = createtagcommandMapper.apply(row, "e");
        entity.setTag(tagMapper.apply(row, "tag"));
        return entity;
    }

    @Override
    public <S extends CreateTagCommand> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
