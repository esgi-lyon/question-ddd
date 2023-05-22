package org.contextmapper.generated.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.contextmapper.generated.gateway.domain.TagCreatedEvent;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link TagCreatedEvent}, with proper type conversions.
 */
@Service
public class TagCreatedEventRowMapper implements BiFunction<Row, String, TagCreatedEvent> {

    private final ColumnConverter converter;

    public TagCreatedEventRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link TagCreatedEvent} stored in the database.
     */
    @Override
    public TagCreatedEvent apply(Row row, String prefix) {
        TagCreatedEvent entity = new TagCreatedEvent();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setTagIdId(converter.fromRow(row, prefix + "_tag_id_id", Long.class));
        return entity;
    }
}
