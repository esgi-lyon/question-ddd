package org.contextmapper.generated.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.contextmapper.generated.gateway.domain.SendQuestionByTagsPreferencesCommand;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link SendQuestionByTagsPreferencesCommand}, with proper type conversions.
 */
@Service
public class SendQuestionByTagsPreferencesCommandRowMapper implements BiFunction<Row, String, SendQuestionByTagsPreferencesCommand> {

    private final ColumnConverter converter;

    public SendQuestionByTagsPreferencesCommandRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link SendQuestionByTagsPreferencesCommand} stored in the database.
     */
    @Override
    public SendQuestionByTagsPreferencesCommand apply(Row row, String prefix) {
        SendQuestionByTagsPreferencesCommand entity = new SendQuestionByTagsPreferencesCommand();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setQuestionSentId(converter.fromRow(row, prefix + "_question_sent_id", Long.class));
        return entity;
    }
}
