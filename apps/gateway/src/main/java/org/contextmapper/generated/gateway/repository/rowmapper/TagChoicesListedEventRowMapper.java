package org.contextmapper.generated.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.contextmapper.generated.gateway.domain.TagChoicesListedEvent;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link TagChoicesListedEvent}, with proper type conversions.
 */
@Service
public class TagChoicesListedEventRowMapper implements BiFunction<Row, String, TagChoicesListedEvent> {

    private final ColumnConverter converter;

    public TagChoicesListedEventRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link TagChoicesListedEvent} stored in the database.
     */
    @Override
    public TagChoicesListedEvent apply(Row row, String prefix) {
        TagChoicesListedEvent entity = new TagChoicesListedEvent();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        return entity;
    }
}
