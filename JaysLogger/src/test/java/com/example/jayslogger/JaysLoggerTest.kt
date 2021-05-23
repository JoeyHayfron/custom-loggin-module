package com.example.jayslogger

import android.content.Context
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class JaysLoggerTest : TestCase(){

    //Mock Context
    var context: Context? = mock(Context::class.java)

    //Instance of Logger Class
    private lateinit var logger: Logger

    @Before
    fun setup(){
        logger = Logger(context!!)
    }

    @Test
    fun loggingWithDebugResultsInDebugLoggingMessage(){
        logger.debug("TEST_DEBUG_LOG", "TEST_DEBUG_MESSAGE")
        assertEquals(logger.logString, "JaysLogger: Session/User = ${logger.sessOrUser}, Date = ${Date()}, Environment = ${BuildConfig.BUILD_TYPE}, DEBUG: TEST_DEBUG_LOG -> TEST_DEBUG_MESSAGE")
    }

    @Test
    fun loggingWithInfoResultsInInfoLoggingMessage(){
        logger.info("TEST_INFO_LOG", "TEST_INFO_MESSAGE")
        assertEquals(logger.logString, "JaysLogger: Session/User = ${logger.sessOrUser}, Date = ${Date()}, Environment = ${BuildConfig.BUILD_TYPE}, INFO: TEST_INFO_LOG -> TEST_INFO_MESSAGE")
    }

    @Test
    fun loggingWithErrorResultsInErrorLoggingMessage(){
        logger.error("TEST_ERROR_LOG", "TEST_ERROR_MESSAGE")
        assertEquals(logger.logString, "JaysLogger: Session/User = ${logger.sessOrUser}, Date = ${Date()}, Environment = ${BuildConfig.BUILD_TYPE}, ERROR: TEST_ERROR_LOG -> TEST_ERROR_MESSAGE")
    }
}