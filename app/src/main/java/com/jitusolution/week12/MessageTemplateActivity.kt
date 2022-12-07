package com.jitusolution.week12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MessageTemplateActivity : AppCompatActivity() {
    companion object {
        const val TEMPLATE_MESSAGE = "TEMPLATE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_template)

        val templates:ArrayList<String> =
            arrayListOf("Let’s meet up!", "Have you worked on the project?",
                "Movie time?", "Busy, do not disturb",
                "Why you leave me?!", "Please pay me a visit. Urgent!",
                "Please call me back")

        var lm = LinearLayoutManager(this)
        val recViewTemplate = findViewById<RecyclerView>(R.id.recViewTemplate)
        recViewTemplate.layoutManager = lm
        recViewTemplate.setHasFixedSize(true)
        recViewTemplate.adapter = TemplateAdapter(templates, this)
    }
}