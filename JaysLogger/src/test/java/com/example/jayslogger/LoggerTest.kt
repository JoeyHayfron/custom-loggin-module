package com.example.jayslogger

import android.content.Context
import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
import java.io.File
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class LoggerTest : TestCase() {

    var context: Context = mock(Context::class.java)

    @Test
    fun testStartLoggingSession() {
        Logger.startSession(Environment.DEBUG, "11920", "332200")
        assertEquals(Logger.loggerEnvironment, Environment.DEBUG)
        assertEquals(Logger.loggerUserID, "11920")
        assertEquals(Logger.loggerSessionID, "332200")
        Logger.stopSession(context)
    }

    @Test
    fun testStopLoggingSession() {
        Logger.startSession(Environment.DEBUG, "11920", "332200")
        Logger.stopSession(context)
        assertEquals(Logger.loggerEnvironment, Environment.DEBUG)
        assertEquals(Logger.loggerUserID, "")
        assertEquals(Logger.loggerSessionID, "")
    }

    @Test
    fun testRunDebugLogging() {
        Logger.startSession(Environment.DEBUG,
            (1..10000).random().toString(),
            (1..100000).random().toString())
        Logger.debug("DEBUG_TAG", "DEBUG_LOG_MESSAGE")
        assertEquals(Logger.loggerLogString.trim(),
            "JaysLogger: Session = ${Logger.loggerSessionID}, " +
                    "UserID = ${Logger.loggerUserID}, " +
                    "Date = ${Date()}, " +
                    "Environment = ${Environment.DEBUG}, " +
                    "${Level.DEBUG}: DEBUG_TAG -> DEBUG_LOG_MESSAGE \n".trim())
        Logger.stopSession(context)
    }

    @Test
    fun testRunInfoLogging() {
        Logger.startSession(Environment.DEBUG,
            (1..10000).random().toString(),
            (1..100000).random().toString())
        Logger.info("INFO_TAG", "INFO_LOG_MESSAGE")
        assertEquals(Logger.loggerLogString.trim(),
            "JaysLogger: Session = ${Logger.loggerSessionID}, " +
                    "UserID = ${Logger.loggerUserID}, " +
                    "Date = ${Date()}, " +
                    "Environment = ${Environment.DEBUG}, " +
                    "${Level.INFO}: INFO_TAG -> INFO_LOG_MESSAGE \n".trim())
        Logger.stopSession(context)
    }

    @Test
    fun testRunErrorLogging() {
        Logger.startSession(Environment.DEBUG,
            (1..10000).random().toString(),
            (1..100000).random().toString())
        Logger.error("ERROR_TAG", "ERROR_LOG_MESSAGE")
        assertEquals(Logger.loggerLogString.trim(),
            "JaysLogger: Session = ${Logger.loggerSessionID}, " +
                    "UserID = ${Logger.loggerUserID}, " +
                    "Date = ${Date()}, " +
                    "Environment = ${Environment.DEBUG}, " +
                    "${Level.ERROR}: ERROR_TAG -> ERROR_LOG_MESSAGE \n".trim())
        Logger.stopSession(context)
    }

    @Test
    fun testWriteLogsToFile() {
        Logger.startSession(Environment.DEBUG,
            (1..10000).random().toString(),
            (1..100000).random().toString())
        Logger.debug("Test Debug", "Test Debug Message")
        Logger.info("Test Info", "Test Info Message")
        Logger.error("Test Error", "Test Error Message")
        val filePath = Logger.writeLogToFile(Logger.loggerLogString, context)
        val fileContent = File(filePath).readText(Charsets.UTF_8)
        assertEquals(filePath.split("\\")[filePath.split("\\").size - 1],
            "${Logger.loggerSessionID}-${Logger.loggerUserID}.txt")
        assertEquals(fileContent.trim(), Logger.loggerLogString.trim())
        Logger.stopSession(context)
    }
}