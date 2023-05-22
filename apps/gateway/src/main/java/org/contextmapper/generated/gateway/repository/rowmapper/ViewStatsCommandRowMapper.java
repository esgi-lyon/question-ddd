package org.contextmapper.generated.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.contextmapper.generated.gateway.domain.ViewStatsCommand;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link ViewStatsCommand}, with proper type conversions.
 */
@Service
public class ViewStatsCommandRowMapper implements BiFunction<Row, String, ViewStatsCommand> {

    private final ColumnConverter converter;

    public ViewStatsCommandRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link ViewStatsCommand} stored in the database.
     */
    @Override
    public ViewStatsCommand apply(Row row, String prefix) {
        ViewStatsCommand entity = new ViewStatsCommand();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setUserId(converter.fromRow(row, prefix + "_user_id", Long.class));
        entity.setQuestionId(converter.fromRow(row, prefix + "_question_id", Long.class));
        entity.setTagId(converter.fromRow(row, prefix + "_tag_id", Long.class));
        return entity;
    }
}
