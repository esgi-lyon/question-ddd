package org.contextmapper.generated.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.contextmapper.generated.gateway.domain.TagChoicesListCommand;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link TagChoicesListCommand}, with proper type conversions.
 */
@Service
public class TagChoicesListCommandRowMapper implements BiFunction<Row, String, TagChoicesListCommand> {

    private final ColumnConverter converter;

    public TagChoicesListCommandRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link TagChoicesListCommand} stored in the database.
     */
    @Override
    public TagChoicesListCommand apply(Row row, String prefix) {
        TagChoicesListCommand entity = new TagChoicesListCommand();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        return entity;
    }
}
