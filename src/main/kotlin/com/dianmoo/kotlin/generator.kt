package com.dianmoo.kotlin

import com.dianmoo.kotlin.db.Conn
import com.dianmoo.kotlin.db.Table
import com.dianmoo.kotlin.gen.CodeGenerator
import com.dianmoo.kotlin.utils.replaceUnderLineAndUpperCase
import java.sql.Connection
import java.sql.SQLException
import java.sql.ResultSet


fun main(args: Array<String>) {
    var conn: Connection? = null

    try {
        println("连接数据库...")

        conn = Conn().getConnection()

        //读取库信息
        val databaseMetaData = conn.getMetaData()
        var tableList: MutableList<Table> = mutableListOf()


        //返回某个数据库的表名
        //参数解析: 第1和第2个都是数据库的名字(2个，是为兼容不同数据库), 第3个参数是查询表名的过滤模式(null为不过滤即查所有，"%a%"为表名中包含字母'a'),最后一个参数是表类型如"TABLE"、"VIEW"等(这些值可查看API中getTableTypes()方法)
        val rs = databaseMetaData.getTables("dbtest", "dbtest", null, arrayOf("TABLE", "VIEW"))
        while (rs.next()) {
            val name = rs.getString("TABLE_NAME") //字符串参数的具体取值参看API中getTables()
            val name2 = rs.getString("TABLE_TYPE")
            val name3 = rs.getString("REMARKS")
            var table: Table = Table()
            table.tableName = name
            table.tableType = name2;
            val r7 = Regex("\\$\\{(.*)\\}")
            table.tableComment = name3.replace(r7, "")
            if (r7.find(name3)?.value != null) {
                table.tableAliasName = r7.find(name3)?.value!!.replace("\${", "").replace("}", "")
            }

            println(name + "," + name2 + "," + table.tableComment)
            println(table.tableAliasName)
            tableList.add(table)
        }
        println("-------------获取表完成-------------")

        for (t in tableList) {
            println("开始生成表：" + t.tableName)
            val resultSet = databaseMetaData.getColumns(null, "%", t.tableName, "%")


            //生成实体
            CodeGenerator().generateEntity(resultSet, t)
        }



        conn!!.close()


    } catch (se: SQLException) {
        // 处理 JDBC 错误
        se.printStackTrace()
    } catch (e: Exception) {
        // 处理 Class.forName 错误
        e.printStackTrace()
    } finally {
        // 关闭资源
        // 什么都不做
        try {
            if (conn != null) conn!!.close()
        } catch (se: SQLException) {
            se.printStackTrace()
        }
    }
}