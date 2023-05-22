package org.contextmapper.generated.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.contextmapper.generated.gateway.domain.AwardedPointEvent;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link AwardedPointEvent}, with proper type conversions.
 */
@Service
public class AwardedPointEventRowMapper implements BiFunction<Row, String, AwardedPointEvent> {

    private final ColumnConverter converter;

    public AwardedPointEventRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link AwardedPointEvent} stored in the database.
     */
    @Override
    public AwardedPointEvent apply(Row row, String prefix) {
        AwardedPointEvent entity = new AwardedPointEvent();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setAnswerId(converter.fromRow(row, prefix + "_answer_id", Long.class));
        return entity;
    }
}
