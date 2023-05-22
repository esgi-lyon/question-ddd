package org.contextmapper.generated.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.contextmapper.generated.gateway.domain.ResourceRefusedAssociationEvent;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link ResourceRefusedAssociationEvent}, with proper type conversions.
 */
@Service
public class ResourceRefusedAssociationEventRowMapper implements BiFunction<Row, String, ResourceRefusedAssociationEvent> {

    private final ColumnConverter converter;

    public ResourceRefusedAssociationEventRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link ResourceRefusedAssociationEvent} stored in the database.
     */
    @Override
    public ResourceRefusedAssociationEvent apply(Row row, String prefix) {
        ResourceRefusedAssociationEvent entity = new ResourceRefusedAssociationEvent();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setQuestionIdId(converter.fromRow(row, prefix + "_question_id_id", Long.class));
        entity.setTagIdId(converter.fromRow(row, prefix + "_tag_id_id", Long.class));
        return entity;
    }
}
