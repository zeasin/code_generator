package com.dianmoo.kotlin.utils

import org.springframework.util.StringUtils

/**
 * 首字母大写
 */
fun upperCase(str: String): String {
    val strArray: List<String> = str.split("_");

    if (strArray.size == 1) {
        return str.substring(0, 1).toUpperCase() + str.substring(1)
    } else {
        var newStr: String = ""
        for (s in strArray) {
            newStr += s.substring(0, 1).toUpperCase() + s.substring(1)
        }
        return newStr;
    }
}

/**
 * 首字母大写，并且替换_
 */
fun replaceUnderLineAndUpperCase(str: String): String {
    val sb = StringBuffer()
    sb.append(str)
    var count = sb.indexOf("_")
    while (count != 0) {
        val num = sb.indexOf("_", count)
        count = num + 1
        if (num != -1) {
            val ss = sb[count]
            val ia = (ss.toInt() - 32).toChar()
            sb.replace(count, count + 1, ia + "")
        }
    }
    val result = sb.toString().replace("_".toRegex(), "")
    return StringUtils.capitalize(result)
}

/**
 * 转换SQL数据类型到JAVA
 */
fun caseDataType(sqlType: String): String {
    val t = sqlType.toLowerCase()

    if (t == "varchar" || t == "text" || t == "char") return "String"
    else if (t == "int" || t == "tinyint" || t == "smallint" || t == "mediumint") return "Integer"
    else if (t == "bit") return "Boolean"
    else if (t == "bigint") return "Long"
    else if (t == "float") return "Float"
    else if (t == "double") return "Double"
    else if (t == "decimal") return "BigDecimal"
    else if (t == "timestamp") return "Integer"
    else if (t == "date" || t == "datetime") return "Date"
    return ""
}