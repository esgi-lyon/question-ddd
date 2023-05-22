package org.contextmapper.generated.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.contextmapper.generated.gateway.domain.CategoryCreatedEvent;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link CategoryCreatedEvent}, with proper type conversions.
 */
@Service
public class CategoryCreatedEventRowMapper implements BiFunction<Row, String, CategoryCreatedEvent> {

    private final ColumnConverter converter;

    public CategoryCreatedEventRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link CategoryCreatedEvent} stored in the database.
     */
    @Override
    public CategoryCreatedEvent apply(Row row, String prefix) {
        CategoryCreatedEvent entity = new CategoryCreatedEvent();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setCategoryIdId(converter.fromRow(row, prefix + "_category_id_id", Long.class));
        return entity;
    }
}
