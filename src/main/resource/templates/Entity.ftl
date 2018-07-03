package ${package_name}.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
* 描述：${table_comment}模型
* @author ${author}
* @date ${date}
*/
public class ${table_name}Entity {

<#if model_column?exists>
    <#list model_column as model>
    /**
    *${model.columnComment!}
    */

    private ${model.changeColumnType} ${model.changeColumnName?uncap_first};

    </#list>
</#if>

<#if model_column?exists>
    <#list model_column as model>

    public ${model.changeColumnType} get${model.changeColumnName}() {
        return this.${model.changeColumnName?uncap_first};
    }

    public void set${model.changeColumnName}(${model.changeColumnType} ${model.changeColumnName?uncap_first}) {
        this.${model.changeColumnName?uncap_first} = ${model.changeColumnName?uncap_first};
    }

    </#list>
</#if>

}