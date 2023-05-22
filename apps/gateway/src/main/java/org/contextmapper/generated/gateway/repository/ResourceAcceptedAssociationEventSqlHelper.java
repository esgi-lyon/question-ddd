package org.contextmapper.generated.gateway.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class ResourceAcceptedAssociationEventSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));

        columns.add(Column.aliased("question_id_id", table, columnPrefix + "_question_id_id"));
        columns.add(Column.aliased("tag_id_id", table, columnPrefix + "_tag_id_id"));
        return columns;
    }
}
