package com.example.customlogger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.example.jayslogger.Environment
import com.example.jayslogger.Logger

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Logger.startSession(Environment.STAGING)

        Logger.debug("DEBUG_LOG", "MESSAGE FOR DEBUG")
        Logger.info("INFO_LOG", "MESSAGE FOR DEBUG")
        Logger.error("ERROR_LOG", "MESSAGE FOR DEBUG")
        Logger.debug("DEBUG_LOG", "MESSAGE FOR DEBUG")
        Logger.info("INFO_LOG", "MESSAGE FOR DEBUG")
        Logger.error("ERROR_LOG", "MESSAGE FOR DEBUG")
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}