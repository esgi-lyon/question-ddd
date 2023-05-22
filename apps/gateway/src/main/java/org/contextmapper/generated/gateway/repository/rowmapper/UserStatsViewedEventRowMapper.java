package org.contextmapper.generated.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.contextmapper.generated.gateway.domain.UserStatsViewedEvent;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link UserStatsViewedEvent}, with proper type conversions.
 */
@Service
public class UserStatsViewedEventRowMapper implements BiFunction<Row, String, UserStatsViewedEvent> {

    private final ColumnConverter converter;

    public UserStatsViewedEventRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link UserStatsViewedEvent} stored in the database.
     */
    @Override
    public UserStatsViewedEvent apply(Row row, String prefix) {
        UserStatsViewedEvent entity = new UserStatsViewedEvent();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setUserId(converter.fromRow(row, prefix + "_user_id", Long.class));
        return entity;
    }
}
