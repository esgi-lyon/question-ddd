package org.contextmapper.generated.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.contextmapper.generated.gateway.domain.CategoryId;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link CategoryId}, with proper type conversions.
 */
@Service
public class CategoryIdRowMapper implements BiFunction<Row, String, CategoryId> {

    private final ColumnConverter converter;

    public CategoryIdRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link CategoryId} stored in the database.
     */
    @Override
    public CategoryId apply(Row row, String prefix) {
        CategoryId entity = new CategoryId();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setCategoryId(converter.fromRow(row, prefix + "_category_id", Integer.class));
        return entity;
    }
}
