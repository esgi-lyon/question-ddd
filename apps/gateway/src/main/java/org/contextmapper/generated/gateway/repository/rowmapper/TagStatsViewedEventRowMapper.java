package org.contextmapper.generated.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.contextmapper.generated.gateway.domain.TagStatsViewedEvent;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link TagStatsViewedEvent}, with proper type conversions.
 */
@Service
public class TagStatsViewedEventRowMapper implements BiFunction<Row, String, TagStatsViewedEvent> {

    private final ColumnConverter converter;

    public TagStatsViewedEventRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link TagStatsViewedEvent} stored in the database.
     */
    @Override
    public TagStatsViewedEvent apply(Row row, String prefix) {
        TagStatsViewedEvent entity = new TagStatsViewedEvent();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setTagId(converter.fromRow(row, prefix + "_tag_id", Long.class));
        return entity;
    }
}
