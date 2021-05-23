package com.example.jayslogger

import android.content.Context

class Logger(ctx: Context, var sessOrUser: String = (1..100000).random().toString()): JaysLogger(sessOrUser, ctx) {
}