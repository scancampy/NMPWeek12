package com.jitusolution.week12

import android.app.Activity
import android.content.Intent
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TemplateAdapter(var templates: ArrayList<String>, var activity: Activity): RecyclerView.Adapter<TemplateAdapter.TemplateViewHolder>() {
    class TemplateViewHolder(var v: View, var activity:Activity):RecyclerView.ViewHolder(v)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TemplateViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var v = inflater.inflate(R.layout.template_card, parent,false)
        return TemplateViewHolder(v, activity)
    }

    override fun onBindViewHolder(holder: TemplateViewHolder, position: Int) {
        val txtTemplate = holder.v.findViewById<TextView>(R.id.txtTemplate)
        txtTemplate.text = templates[position]

        val btnPick = holder.v.findViewById<Button>(R.id.btnPick)
        btnPick.setOnClickListener {
            val intent = Intent()
            intent.putExtra(MessageTemplateActivity.TEMPLATE_MESSAGE,templates[position])
            holder.activity.setResult(Activity.RESULT_OK, intent)
            holder.activity.finish()
        }
    }

    override fun getItemCount(): Int {
        return templates.size
    }
}
