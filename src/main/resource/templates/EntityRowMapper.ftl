package ${package_name}.entity;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
* 描述：${table_name}RowMapper
* @author ${author}
* @date ${date}
*/
public class ${table_name}RowMapper implements RowMapper<${table_name}Entity> {

    @Override
    public ${table_name}Entity mapRow(ResultSet resultSet, int i) throws SQLException {
        ${table_name}Entity entity = new ${table_name}Entity();
        <#if model_column?exists>
            <#list model_column as model>
            <#if model.changeColumnType == "Integer">
        entity.set${model.changeColumnName}(resultSet.getInt("${model.columnName}"));
            <#else >
            </#if>
        entity.set${model.changeColumnName}(resultSet.get${model.changeColumnType}("${model.columnName}"));
            </#list>
        </#if>
        return entity;
    }
}
