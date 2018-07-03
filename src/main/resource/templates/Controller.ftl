package ${package_name}.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
* 描述：${table_name}模型
* @author ${author}
* @date ${date}
*/
public class ${table_name}Entity {

<#if model_column?exists>
    <#list model_column as model>
    /**
    *${model.columnComment!}
    */
        <#if (model.columnType = 'int' || model.columnType = 'tinyint'|| model.columnType = 'smallint'|| model.columnType = 'mediumint')>
    private Integer ${model.changeColumnName?uncap_first};
        </#if>
        <#if (model.columnType = 'bit' )>
    private Boolean ${model.changeColumnName?uncap_first};
        </#if>
        <#if (model.columnType = 'bigint' )>
    private Long ${model.changeColumnName?uncap_first};
        </#if>
        <#if (model.columnType = 'float' )>
    private Float ${model.changeColumnName?uncap_first};
        </#if>
        <#if (model.columnType = 'double' )>
    private Double ${model.changeColumnName?uncap_first};
        </#if>
        <#if (model.columnType = 'decimal' )>
    private BigDecimal ${model.changeColumnName?uncap_first};
        </#if>

        <#if (model.columnType = 'varchar' || model.columnType = 'text'|| model.columnType = 'char')>
    private String ${model.changeColumnName?uncap_first};
        </#if>
        <#if model.columnType = 'timestamp' >
    private Date ${model.changeColumnName?uncap_first};
        </#if>
        <#if (model.columnType = 'date' || model.columnType = 'datetime') >
    private Date ${model.changeColumnName?uncap_first};
        </#if>
    </#list>
</#if>

<#if model_column?exists>
    <#list model_column as model>
        <#if (model.columnType = 'int' || model.columnType = 'tinyint'|| model.columnType = 'smallint'|| model.columnType = 'mediumint')>
    public Integer get${model.changeColumnName}() {
        return this.${model.changeColumnName?uncap_first};
    }

    public void set${model.changeColumnName}(Integer ${model.changeColumnName?uncap_first}) {
        this.${model.changeColumnName?uncap_first} = ${model.changeColumnName?uncap_first};
    }

        </#if>
        <#if (model.columnType = 'bit')>
    public Boolean get${model.changeColumnName}() {
        return this.${model.changeColumnName?uncap_first};
    }

    public void set${model.changeColumnName}(Boolean ${model.changeColumnName?uncap_first}) {
        this.${model.changeColumnName?uncap_first} = ${model.changeColumnName?uncap_first};
    }
        </#if>
        <#if (model.columnType = 'bigint')>
    public Long get${model.changeColumnName}() {
        return this.${model.changeColumnName?uncap_first};
    }

    public void set${model.changeColumnName}(Long ${model.changeColumnName?uncap_first}) {
        this.${model.changeColumnName?uncap_first} = ${model.changeColumnName?uncap_first};
    }
        </#if>
        <#if (model.columnType = 'float')>
    public Float get${model.changeColumnName}() {
        return this.${model.changeColumnName?uncap_first};
    }

    public void set${model.changeColumnName}(Float ${model.changeColumnName?uncap_first}) {
        this.${model.changeColumnName?uncap_first} = ${model.changeColumnName?uncap_first};
    }
        </#if>
        <#if (model.columnType = 'double')>
    public Double get${model.changeColumnName}() {
        return this.${model.changeColumnName?uncap_first};
    }

    public void set${model.changeColumnName}(Double ${model.changeColumnName?uncap_first}) {
        this.${model.changeColumnName?uncap_first} = ${model.changeColumnName?uncap_first};
    }
        </#if>
        <#if (model.columnType = 'decimal')>
    public BigDecimal get${model.changeColumnName}() {
        return this.${model.changeColumnName?uncap_first};
    }

    public void set${model.changeColumnName}(BigDecimal ${model.changeColumnName?uncap_first}) {
        this.${model.changeColumnName?uncap_first} = ${model.changeColumnName?uncap_first};
    }
        </#if>

        <#if (model.columnType = 'varchar' || model.columnType = 'text'|| model.columnType = 'char')>
    public String get${model.changeColumnName}() {
        return this.${model.changeColumnName?uncap_first};
    }

    public void set${model.changeColumnName}(String ${model.changeColumnName?uncap_first}) {
        this.${model.changeColumnName?uncap_first} = ${model.changeColumnName?uncap_first};
    }

        </#if>
        <#if model.columnType = 'timestamp' >
    public Date get${model.changeColumnName}() {
        return this.${model.changeColumnName?uncap_first};
    }

    public void set${model.changeColumnName}(Date ${model.changeColumnName?uncap_first}) {
        this.${model.changeColumnName?uncap_first} = ${model.changeColumnName?uncap_first};
    }

        </#if>
        <#if (model.columnType = 'date' || model.columnType = 'datetime') >
    public Date get${model.changeColumnName}() {
        return this.${model.changeColumnName?uncap_first};
    }

    public void set${model.changeColumnName}(Date ${model.changeColumnName?uncap_first}) {
        this.${model.changeColumnName?uncap_first} = ${model.changeColumnName?uncap_first};
    }
        </#if>
    </#list>
</#if>

}