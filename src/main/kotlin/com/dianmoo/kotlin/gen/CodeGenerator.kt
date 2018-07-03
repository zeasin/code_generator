package com.dianmoo.kotlin.gen

import com.dianmoo.kotlin.db.Column
import com.dianmoo.kotlin.db.Table
import com.dianmoo.kotlin.utils.caseDataType
import com.dianmoo.kotlin.utils.replaceUnderLineAndUpperCase

import freemarker.template.Template
import org.springframework.core.io.support.PropertiesLoaderUtils
import java.io.BufferedWriter
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.sql.ResultSet
import java.util.*
import java.text.SimpleDateFormat


class CodeGenerator {
    /**
     * 生成实体
     */
    fun generateEntity(resultSet: ResultSet, table: Table) {
        var tableName = table.tableName
        if (table.tableAliasName != null && table.tableAliasName != "")
            tableName = table.tableAliasName

        val changeTableName = replaceUnderLineAndUpperCase(tableName)

        //字段列表
        val columnList: MutableList<Column> = ArrayList<Column>()
        println("-----------打印表字段信息-----------")
        while (resultSet.next()) {

            var column: Column = Column()
            column.columnName = resultSet.getString("COLUMN_NAME")
            column.columnType = resultSet.getString("TYPE_NAME")
            column.columnComment = resultSet.getString("REMARKS")

            column.changeColumnType = caseDataType(column.columnType)
            if (column.columnComment != null && column.columnComment != "") {
                //拆分备注
                val r7 = Regex("\\$\\{(.*)\\}")

                if (r7.find(column.columnComment)?.value != null) {
                    column.columnAliasName = r7.find(column.columnComment)?.value!!.replace("\${", "").replace("}", "")

                }
                column.columnComment = column.columnComment.replace(r7, "")

            }

            if (column.columnAliasName != null && column.columnAliasName != "") {
                column.columnName = column.columnAliasName
            }

            column.changeColumnName = replaceUnderLineAndUpperCase(column.columnName)

            println(column.columnName + "    " + column.columnComment + "    " + column.columnAliasName)

//            column.columnAliasName =

            columnList.add(column)
        }

        //读取配置文件
        val properties = PropertiesLoaderUtils.loadAllProperties("config.properties")

        var template: Template = FreeMarkerTemplateUtils().getTemplate("Entity.ftl")
        val dataMap = HashMap<String, Any>()

        val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")//设置日期格式

        //模板数据赋值
        dataMap.put("package_name", properties.getProperty("package_name"))
        dataMap.put("author", properties.getProperty("author"))
        dataMap.put("date", df.format(Date()))
        dataMap.put("table_name", changeTableName)
        dataMap.put("model_column", columnList);
        dataMap.put("table_comment", table.tableComment);


        val path = properties.getProperty("OUTPUT_PATH")
        var fileName = changeTableName + "Entity.java"
        val file = File(path + fileName)
        val fos = FileOutputStream(file)

        val out = BufferedWriter(OutputStreamWriter(fos, "utf-8"), 10240)
        template.process(dataMap, out)

        fos.close()
        out.close()

        println(fileName + "  Generate Success")

        var templateRowMapper: Template = FreeMarkerTemplateUtils().getTemplate("EntityRowMapper.ftl")
        val fileName1 = changeTableName + "RowMapper.java"
        val file1 = File(path + fileName1)
        val fos1 = FileOutputStream(file1)
        val out1 = BufferedWriter(OutputStreamWriter(fos1, "utf-8"), 10240)
        templateRowMapper.process(dataMap, out1)
        println(fileName + "RowMapper Generate Success")
    }
}