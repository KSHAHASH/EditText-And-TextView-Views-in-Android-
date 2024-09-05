package com.bignerdranch.android.edittexts

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

   private lateinit var editText: EditText
   private lateinit var button: Button
   private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        //initialize the variables by referencing with their corresponding ids
        editText = findViewById(R.id.edit_text)
        button = findViewById(R.id.button)
        textView = findViewById(R.id.textView2)


        button.setOnClickListener {
            var userName: String = editText.text.toString()
            textView.setText(userName)
            //textView.text = userName ---> property access syntax
        }
    }
}