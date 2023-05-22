package org.contextmapper.generated.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.contextmapper.generated.gateway.domain.EvaluationId;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link EvaluationId}, with proper type conversions.
 */
@Service
public class EvaluationIdRowMapper implements BiFunction<Row, String, EvaluationId> {

    private final ColumnConverter converter;

    public EvaluationIdRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link EvaluationId} stored in the database.
     */
    @Override
    public EvaluationId apply(Row row, String prefix) {
        EvaluationId entity = new EvaluationId();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setEvaluationId(converter.fromRow(row, prefix + "_evaluation_id", Integer.class));
        entity.setLeaderBoardId(converter.fromRow(row, prefix + "_leader_board_id", Long.class));
        return entity;
    }
}
