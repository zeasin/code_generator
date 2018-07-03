package com.dianmoo.kotlin

import com.dianmoo.kotlin.db.Column
import com.dianmoo.kotlin.db.Conn
import com.dianmoo.kotlin.gen.FreeMarkerTemplateUtils
import freemarker.template.Template
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.sql.Statement
import java.sql.ResultSet
import java.io.OutputStreamWriter
import java.io.BufferedWriter
import java.io.Writer
import java.util.HashMap
import java.io.FileOutputStream
import java.io.File










// JDBC 驱动名及数据库 URL
val JDBC_DRIVER = "com.mysql.jdbc.Driver"
val DB_URL = "jdbc:mysql://localhost:8889/dbtest"

// 数据库的用户名与密码，需要根据自己的设置
val USER = "root"
val PASS = "123456"


//fun main(args: Array<String>) {
//    println("Hello, World")
//}

fun main(args: Array<String>) {
    var conn: Connection? = null
    var stmt: Statement? = null
    try {
        // 注册 JDBC 驱动
//        Class.forName("com.mysql.jdbc.Driver")

        // 打开链接
        println("连接数据库...")
//        conn = DriverManager.getConnection( DB_URL, USER, PASS)
        conn = Conn().getConnection()


//        val connection = Conn().getConnection()
        val databaseMetaData = conn.getMetaData()
        val resultSet = databaseMetaData.getColumns(null, "%", "area", "%")
        val columnList: MutableList<Column> = ArrayList<Column>()

        while (resultSet.next()) {
//            println("列1"+resultSet.getString(0))
//            println("列2"+resultSet.getString(1))
//            println("列3"+resultSet.getString(2))
            var column: Column = Column()
            column.columnName = resultSet.getString("COLUMN_NAME")
            column.columnType = resultSet.getString("TYPE_NAME")
            column.columnComment = resultSet.getString("REMARKS")

            columnList.add(column)

            println(resultSet.getString("COLUMN_NAME"))
            println(resultSet.getString("TYPE_NAME"))
            println(resultSet.getString("REMARKS"))
        }

        println(columnList)

        var template : Template = FreeMarkerTemplateUtils().getTemplate("Controller.ftl")
        val dataMap = HashMap<String, Any>()
        dataMap.put("package_name","com.dianmoo.zrx")
        var path = "/Users/qiliping/Desktop/codeoutput/model.java"
        val file = File(path)
        val fos = FileOutputStream(file)

        val out = BufferedWriter(OutputStreamWriter(fos, "utf-8"), 10240)
        template.process(dataMap, out)

        println("OKKKKKKKKK")

        // 执行查询
//        println(" 实例化Statement对象...")
//        stmt = conn!!.createStatement()
//        val sql: String
//        sql = "SELECT id, name, code FROM area"
//        val rs = stmt!!.executeQuery(sql)
//
//        // 展开结果集数据库
//        while (rs.next()) {
//            // 通过字段检索
//            val id = rs.getInt("id")
//            val name = rs.getString("name")
//            val url = rs.getString("code")
//
//            // 输出数据
//            print("ID: $id")
//            print(", 站点名称: $name")
//            print(", 站点 URL: $url")
//            print("\n")
//        }
//        // 完成后关闭
//        rs.close()
//        stmt!!.close()
        conn!!.close()
    } catch (se: SQLException) {
        // 处理 JDBC 错误
        se.printStackTrace()
    } catch (e: Exception) {
        // 处理 Class.forName 错误
        e.printStackTrace()
    } finally {
        // 关闭资源
        try {
            if (stmt != null) stmt!!.close()
        } catch (se2: SQLException) {
        }
        // 什么都不做
        try {
            if (conn != null) conn!!.close()
        } catch (se: SQLException) {
            se.printStackTrace()
        }

    }
    println("Goodbye!")
}

