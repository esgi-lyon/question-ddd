package org.contextmapper.generated.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.contextmapper.generated.gateway.domain.ResourceWaitingForAssociationEvent;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link ResourceWaitingForAssociationEvent}, with proper type conversions.
 */
@Service
public class ResourceWaitingForAssociationEventRowMapper implements BiFunction<Row, String, ResourceWaitingForAssociationEvent> {

    private final ColumnConverter converter;

    public ResourceWaitingForAssociationEventRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link ResourceWaitingForAssociationEvent} stored in the database.
     */
    @Override
    public ResourceWaitingForAssociationEvent apply(Row row, String prefix) {
        ResourceWaitingForAssociationEvent entity = new ResourceWaitingForAssociationEvent();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setQuestionIdId(converter.fromRow(row, prefix + "_question_id_id", Long.class));
        entity.setTagIdId(converter.fromRow(row, prefix + "_tag_id_id", Long.class));
        return entity;
    }
}
