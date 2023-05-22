package org.contextmapper.generated.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.contextmapper.generated.gateway.domain.CreateTagCommand;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link CreateTagCommand}, with proper type conversions.
 */
@Service
public class CreateTagCommandRowMapper implements BiFunction<Row, String, CreateTagCommand> {

    private final ColumnConverter converter;

    public CreateTagCommandRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link CreateTagCommand} stored in the database.
     */
    @Override
    public CreateTagCommand apply(Row row, String prefix) {
        CreateTagCommand entity = new CreateTagCommand();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setTagId(converter.fromRow(row, prefix + "_tag_id", Long.class));
        return entity;
    }
}
