package org.contextmapper.generated.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.contextmapper.generated.gateway.domain.AwardPointForEvaluationCommand;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link AwardPointForEvaluationCommand}, with proper type conversions.
 */
@Service
public class AwardPointForEvaluationCommandRowMapper implements BiFunction<Row, String, AwardPointForEvaluationCommand> {

    private final ColumnConverter converter;

    public AwardPointForEvaluationCommandRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link AwardPointForEvaluationCommand} stored in the database.
     */
    @Override
    public AwardPointForEvaluationCommand apply(Row row, String prefix) {
        AwardPointForEvaluationCommand entity = new AwardPointForEvaluationCommand();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setEvaluationId(converter.fromRow(row, prefix + "_evaluation_id", Long.class));
        return entity;
    }
}
