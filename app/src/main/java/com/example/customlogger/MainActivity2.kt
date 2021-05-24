package com.example.customlogger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.jayslogger.Logger

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        Logger.debug("AC_DEBUG_LOG", "ACTIVITY2")
        Logger.debug("AC1_DEBUG_LOG", "ACTIVITY2")
        Logger.debug("AC4_DEBUG_LOG", "ACTIVITY2")
        Logger.debug("AC_DEBUG_LOG", "ACTIVITY2")
        Logger.debug("AC1_DEBUG_LOG", "ACTIVITY2")
        Logger.debug("AC4_DEBUG_LOG", "ACTIVITY2")
        Logger.writeLogToRemoteStorage(this)


        var hiw: TextView = findViewById(R.id.hiworld)
        hiw.setOnClickListener{
            Logger.stopSession(this)
        }
    }
}