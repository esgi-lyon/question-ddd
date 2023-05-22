package org.contextmapper.generated.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.contextmapper.generated.gateway.domain.StatisticSubjectQuestion;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link StatisticSubjectQuestion}, with proper type conversions.
 */
@Service
public class StatisticSubjectQuestionRowMapper implements BiFunction<Row, String, StatisticSubjectQuestion> {

    private final ColumnConverter converter;

    public StatisticSubjectQuestionRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link StatisticSubjectQuestion} stored in the database.
     */
    @Override
    public StatisticSubjectQuestion apply(Row row, String prefix) {
        StatisticSubjectQuestion entity = new StatisticSubjectQuestion();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setQuestionId(converter.fromRow(row, prefix + "_question_id", Integer.class));
        return entity;
    }
}
