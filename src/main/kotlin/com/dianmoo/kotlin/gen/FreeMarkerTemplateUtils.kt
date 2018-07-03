package com.dianmoo.kotlin.gen

import freemarker.cache.ClassTemplateLoader
import freemarker.cache.NullCacheStorage
import freemarker.template.Configuration
import freemarker.template.Template
import freemarker.template.TemplateExceptionHandler

import java.io.IOException

/**
 * Created by Ay on 2016/7/27.
 */
class FreeMarkerTemplateUtils {
    private val CONFIGURATION = Configuration(Configuration.VERSION_2_3_23)

    init {
        //这里比较重要，用来指定加载模板所在的路径
        CONFIGURATION.templateLoader = ClassTemplateLoader(FreeMarkerTemplateUtils::class.java, "/templates")
        CONFIGURATION.defaultEncoding = "UTF-8"
        CONFIGURATION.templateExceptionHandler = TemplateExceptionHandler.RETHROW_HANDLER
        CONFIGURATION.cacheStorage = NullCacheStorage.INSTANCE
    }

    fun getTemplate(templateName: String): Template {
        try {
            return CONFIGURATION.getTemplate(templateName)
        } catch (e: IOException) {
            throw e
        }

    }

    fun clearCache() {
        CONFIGURATION.clearTemplateCache()
    }
}