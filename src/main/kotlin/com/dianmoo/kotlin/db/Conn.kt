package com.dianmoo.kotlin.db

import org.springframework.core.io.support.PropertiesLoaderUtils
import java.sql.*
import java.sql.DriverManager
import java.util.*


class Conn {

    // JDBC 驱动名及数据库 URL
//    val JDBC_DRIVER = "com.mysql.jdbc.Driver"
//    val DB_URL = "jdbc:mysql://localhost:8889/dbtest"

    // 数据库的用户名与密码，需要根据自己的设置
//    val USER = "root"
//    val PASS = "123456"
    val properties = PropertiesLoaderUtils.loadAllProperties("config.properties")
    val JDBC_DRIVER = properties.getProperty("JDBC_DRIVER")
    val DB_URL = properties.getProperty("DB_URL")
    val USER = properties.getProperty("DB_USER")
    val PASS = properties.getProperty("DB_PASSWORD")

    fun getConnection(): Connection {
        val props = Properties()
        props.setProperty("user", USER);
        props.setProperty("password", PASS);
        props.setProperty("remarks", "true"); //设置可以获取remarks信息
        props.setProperty("useInformationSchema", "true");//设置可以获取tables remarks信息

        Class.forName(JDBC_DRIVER)
        return DriverManager.getConnection(DB_URL, props)
    }

}