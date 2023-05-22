package org.contextmapper.generated.gateway.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class UserPreferencesTagInfosSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("tag_id", table, columnPrefix + "_tag_id"));
        columns.add(Column.aliased("name", table, columnPrefix + "_name"));

        columns.add(Column.aliased("user_preferences_id", table, columnPrefix + "_user_preferences_id"));
        return columns;
    }
}
