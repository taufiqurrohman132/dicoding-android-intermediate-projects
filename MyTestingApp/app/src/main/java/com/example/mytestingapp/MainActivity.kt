package com.example.mytestingapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var btnSetValue: Button
    private lateinit var tvText: TextView

    private var names = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvText = findViewById(R.id.tv_text)
        btnSetValue = findViewById(R.id.btn_set_value)

        btnSetValue.setOnClickListener(this)

        names.add("sandika")
        names.add("dika")
        names.add("dola")

    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_set_value){
            Log.d("MainActivity", names.toString())
            val name = StringBuilder()
            for (i in 0..2){
                name.append(names[i]).append("\n")
            }
            tvText.text = name.toString()
        }
    }
}