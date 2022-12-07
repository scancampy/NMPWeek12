package com.jitusolution.week12

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    companion object {
        const val REQUEST_SELECT_CONTACT = 1
        const val REQUEST_SELECT_TEMPLATE = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnSend = findViewById<Button>(R.id.btnSend)
        val txtMessage = findViewById<EditText>(R.id.txtMessage)
        val txtInputLayoutHP = findViewById<TextInputLayout>(R.id.txtInputLayoutHP)
        val btnPickTemplate = findViewById<Button>(R.id.btnPickTemplate)

        btnPickTemplate.setOnClickListener {
            val intent = Intent(this,MessageTemplateActivity::class.java)
            startActivityForResult(intent, REQUEST_SELECT_TEMPLATE)
        }

        btnSend.setOnClickListener {
            val sendIntent = Intent(Intent.ACTION_SEND)
            sendIntent.putExtra(Intent.EXTRA_TEXT,txtMessage.text.toString())
            sendIntent.type = "text/plain"
            startActivity(Intent.createChooser(sendIntent,"Send My Text"))
        }

        txtInputLayoutHP.setEndIconOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE
            startActivityForResult(intent, REQUEST_SELECT_CONTACT)
        }

        val templates:ArrayList<String> =
            arrayListOf("Let’s meet up!", "Have you worked on the project?",
                "Movie time?", "Busy, do not disturb",
                "Why you leave me?!", "Please pay me a visit. Urgent!",
                "Please call me back")

        var lm: LinearLayoutManager = LinearLayoutManager(this)
        val recViewTemplate = findViewById<RecyclerView>(R.id.recViewTemplate)
        recViewTemplate.layoutManager = lm
        recViewTemplate.setHasFixedSize(true)
        recViewTemplate.adapter = TemplateAdapter(templates, this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK) {
            if(requestCode == REQUEST_SELECT_CONTACT) {
                val contactUri = data?.data
                val projection = arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER)
                val cursor = contactUri?.let { contentResolver.query(it, projection, null,null, null) }
                if(cursor != null && cursor.moveToFirst()) {
                    val hp = cursor.getString(0)
                    val txtHP = findViewById<EditText>(R.id.txtHP)
                    txtHP.setText(hp)
                } else if(requestCode == REQUEST_SELECT_TEMPLATE) {
                    val message =
                        data?.getStringExtra(MessageTemplateActivity.TEMPLATE_MESSAGE)
                    val txtMessage = findViewById<EditText>(R.id.txtMessage)
                    txtMessage.setText(message)
                }
            }
        }
    }
}