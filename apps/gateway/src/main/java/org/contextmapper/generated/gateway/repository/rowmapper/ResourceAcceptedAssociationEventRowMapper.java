package org.contextmapper.generated.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.contextmapper.generated.gateway.domain.ResourceAcceptedAssociationEvent;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link ResourceAcceptedAssociationEvent}, with proper type conversions.
 */
@Service
public class ResourceAcceptedAssociationEventRowMapper implements BiFunction<Row, String, ResourceAcceptedAssociationEvent> {

    private final ColumnConverter converter;

    public ResourceAcceptedAssociationEventRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link ResourceAcceptedAssociationEvent} stored in the database.
     */
    @Override
    public ResourceAcceptedAssociationEvent apply(Row row, String prefix) {
        ResourceAcceptedAssociationEvent entity = new ResourceAcceptedAssociationEvent();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setQuestionIdId(converter.fromRow(row, prefix + "_question_id_id", Long.class));
        entity.setTagIdId(converter.fromRow(row, prefix + "_tag_id_id", Long.class));
        return entity;
    }
}
