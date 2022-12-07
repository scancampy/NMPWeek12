package com.jitusolution.week12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MessageTemplateActivity : AppCompatActivity() {
    companion object {
        const val TEMPLATE_MESSAGE = "TEMPLATE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_template)
    }
}