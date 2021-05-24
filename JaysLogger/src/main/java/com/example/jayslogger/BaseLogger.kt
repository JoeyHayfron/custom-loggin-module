package com.example.jayslogger

import android.content.Context

interface BaseLogger {
    fun startSession(
        environment: Environment = Environment.DEBUG,
        userId: String = "",
        sessionId: String = (1..100000).random().toString(),
    )

    fun stopSession(context: Context)

    fun doLog(tag: String, message: String, level: Level)

    fun debug(tag: String, message: String)

    fun info(tag: String, message: String)

    fun error(tag: String, message: String)

    fun writeLogToFile(content: String, context: Context): String

    fun writeLogToRemoteStorage(context: Context)
}
