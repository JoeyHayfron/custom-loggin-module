package com.example.jayslogger

import android.content.Context
import android.os.Build
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import java.io.File
import java.io.FileWriter
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

object Logger : BaseLogger {

    var loggerSessionID = ""
    var loggerUserID = ""
    var loggerLogString = ""
    var loggerEnvironment: Environment = Environment.DEBUG

    override fun startSession(environment: Environment, userId: String, sessionId: String) {
        loggerUserID = userId
        loggerEnvironment = environment
        loggerSessionID = sessionId
    }

    override fun stopSession(context: Context) {
        writeLogToFile(loggerLogString, context)
        loggerSessionID = ""
        loggerUserID = ""
        loggerLogString = ""
    }

    override fun doLog(tag: String, message: String, level: Level) {
        val log =
            "JaysLogger: Session = $loggerSessionID, " +
                    "UserID = $loggerUserID, " +
                    "Date = ${Date()}, " +
                    "Environment = $loggerEnvironment, " +
                    "$level: $tag -> $message \n"
        loggerLogString += log
        if (loggerEnvironment != Environment.PRODUCTION) {
            println(log)
        }
    }

    override fun debug(tag: String, message: String) {
        doLog(tag, message, Level.DEBUG)
    }

    override fun info(tag: String, message: String) {
        doLog(tag, message, Level.INFO)
    }

    override fun error(tag: String, message: String) {
        doLog(tag, message, Level.ERROR)
    }

    override fun writeLogToFile(content: String, context: Context) : String {
        val dir: File = File(context.filesDir, "logs-dir")
        if (!dir.exists()) {
            dir.mkdir()
        }
        try {
            val newFile = File(dir, "$loggerSessionID-${loggerUserID}.txt")
            val writer = FileWriter(newFile, true)
            writer.append(content + "\n")
            writer.flush()
            writer.close()
            return newFile.absolutePath
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }

    override fun writeLogToRemoteStorage(context: Context) {
        val storage = Firebase.storage
        val storageRef: StorageReference = storage.reference
        val logsFile = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Files.readAllBytes(Paths.get(context.filesDir.path + "/logs-dir/$loggerSessionID-${loggerUserID}.txt"))
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        val logs = storageRef.child("logs/$loggerSessionID-${loggerUserID}.txt")
        logs.putBytes(logsFile)
    }
}

enum class Level {
    DEBUG, INFO, ERROR
}

enum class Environment {
    DEBUG, STAGING, PRODUCTION
}