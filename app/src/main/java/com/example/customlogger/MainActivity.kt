package com.example.customlogger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.jayslogger.Logger

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Logger(this).debug("DEBUG-LOG", "This is debug message")
        Logger(this).info("INFO-LOG", "This is info message")
        Logger(this).error("ERROR-LOG", "This is error message")
        Logger(this).writeLogToRemoteStorage()
    }
}