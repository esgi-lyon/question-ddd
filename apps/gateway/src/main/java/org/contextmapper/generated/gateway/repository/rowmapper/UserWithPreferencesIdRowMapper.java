package org.contextmapper.generated.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.contextmapper.generated.gateway.domain.UserWithPreferencesId;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link UserWithPreferencesId}, with proper type conversions.
 */
@Service
public class UserWithPreferencesIdRowMapper implements BiFunction<Row, String, UserWithPreferencesId> {

    private final ColumnConverter converter;

    public UserWithPreferencesIdRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link UserWithPreferencesId} stored in the database.
     */
    @Override
    public UserWithPreferencesId apply(Row row, String prefix) {
        UserWithPreferencesId entity = new UserWithPreferencesId();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setUserId(converter.fromRow(row, prefix + "_user_id", Integer.class));
        return entity;
    }
}
