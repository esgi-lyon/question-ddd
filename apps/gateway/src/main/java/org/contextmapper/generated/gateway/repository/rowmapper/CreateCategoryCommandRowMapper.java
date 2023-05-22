package org.contextmapper.generated.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.contextmapper.generated.gateway.domain.CreateCategoryCommand;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link CreateCategoryCommand}, with proper type conversions.
 */
@Service
public class CreateCategoryCommandRowMapper implements BiFunction<Row, String, CreateCategoryCommand> {

    private final ColumnConverter converter;

    public CreateCategoryCommandRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link CreateCategoryCommand} stored in the database.
     */
    @Override
    public CreateCategoryCommand apply(Row row, String prefix) {
        CreateCategoryCommand entity = new CreateCategoryCommand();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setCategoryId(converter.fromRow(row, prefix + "_category_id", Long.class));
        return entity;
    }
}
