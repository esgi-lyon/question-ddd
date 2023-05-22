package org.contextmapper.generated.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.contextmapper.generated.gateway.domain.UserPreferencesTagInfos;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link UserPreferencesTagInfos}, with proper type conversions.
 */
@Service
public class UserPreferencesTagInfosRowMapper implements BiFunction<Row, String, UserPreferencesTagInfos> {

    private final ColumnConverter converter;

    public UserPreferencesTagInfosRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link UserPreferencesTagInfos} stored in the database.
     */
    @Override
    public UserPreferencesTagInfos apply(Row row, String prefix) {
        UserPreferencesTagInfos entity = new UserPreferencesTagInfos();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setTagId(converter.fromRow(row, prefix + "_tag_id", Integer.class));
        entity.setName(converter.fromRow(row, prefix + "_name", String.class));
        entity.setUserPreferencesId(converter.fromRow(row, prefix + "_user_preferences_id", Long.class));
        return entity;
    }
}
