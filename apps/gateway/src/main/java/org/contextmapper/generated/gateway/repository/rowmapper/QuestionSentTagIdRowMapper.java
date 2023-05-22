package org.contextmapper.generated.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.contextmapper.generated.gateway.domain.QuestionSentTagId;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link QuestionSentTagId}, with proper type conversions.
 */
@Service
public class QuestionSentTagIdRowMapper implements BiFunction<Row, String, QuestionSentTagId> {

    private final ColumnConverter converter;

    public QuestionSentTagIdRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link QuestionSentTagId} stored in the database.
     */
    @Override
    public QuestionSentTagId apply(Row row, String prefix) {
        QuestionSentTagId entity = new QuestionSentTagId();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setTagId(converter.fromRow(row, prefix + "_tag_id", Integer.class));
        entity.setQuestionSentId(converter.fromRow(row, prefix + "_question_sent_id", Long.class));
        return entity;
    }
}
