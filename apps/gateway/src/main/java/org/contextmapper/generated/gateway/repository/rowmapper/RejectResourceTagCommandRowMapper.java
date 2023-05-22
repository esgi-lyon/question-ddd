package org.contextmapper.generated.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.contextmapper.generated.gateway.domain.RejectResourceTagCommand;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link RejectResourceTagCommand}, with proper type conversions.
 */
@Service
public class RejectResourceTagCommandRowMapper implements BiFunction<Row, String, RejectResourceTagCommand> {

    private final ColumnConverter converter;

    public RejectResourceTagCommandRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link RejectResourceTagCommand} stored in the database.
     */
    @Override
    public RejectResourceTagCommand apply(Row row, String prefix) {
        RejectResourceTagCommand entity = new RejectResourceTagCommand();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setQuestionIdId(converter.fromRow(row, prefix + "_question_id_id", Long.class));
        return entity;
    }
}
