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


abstract class JaysLogger(var sessionOrUser: String, var context: Context) {

    var logString: String = "Nothing to log."

    private fun doLog(tag: String, message: String, level: Level) {
        this.logString =
            "JaysLogger: Session/User = $sessionOrUser, Date = ${Date()}, Environment = ${BuildConfig.BUILD_TYPE}, $level: $tag -> $message"
        println(logString)
        writeLogToFile(logString)
    }

    fun debug(tag: String, message: String) {
        doLog(tag, message, Level.DEBUG)
    }

    fun info(tag: String, message: String) {
        doLog(tag, message, Level.INFO)
    }

    fun error(tag: String, message: String) {
        doLog(tag, message, Level.ERROR)
    }

    private fun writeLogToFile(content: String) {
        val dir: File = File(context.filesDir, "logs-dir")
        if (!dir.exists()) {
            dir.mkdir()
        }
        try {
            val gpxfile = File(dir, "jays-logger.txt")
            val writer = FileWriter(gpxfile, true)
            writer.append(content+ "\n")
            writer.flush()
            writer.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun writeLogToRemoteStorage() {
        val storage = Firebase.storage
        var storageRef: StorageReference = storage.reference
        val gpxfile = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Files.readAllBytes(Paths.get(context.filesDir.path + "/logs-dir/jays-logger.txt"))
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        val logs = storageRef.child("logs/jays-logs.txt")
        logs.putBytes(gpxfile)

    }
}

enum class Level {
    DEBUG, INFO, ERROR
}
