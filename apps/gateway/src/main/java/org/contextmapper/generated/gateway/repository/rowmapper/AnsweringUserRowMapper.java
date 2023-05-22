package org.contextmapper.generated.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.contextmapper.generated.gateway.domain.AnsweringUser;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link AnsweringUser}, with proper type conversions.
 */
@Service
public class AnsweringUserRowMapper implements BiFunction<Row, String, AnsweringUser> {

    private final ColumnConverter converter;

    public AnsweringUserRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link AnsweringUser} stored in the database.
     */
    @Override
    public AnsweringUser apply(Row row, String prefix) {
        AnsweringUser entity = new AnsweringUser();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setUserId(converter.fromRow(row, prefix + "_user_id", Integer.class));
        entity.setName(converter.fromRow(row, prefix + "_name", String.class));
        return entity;
    }
}
