package com.example.lapsya.showme

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun hello_btn_click(v: View) {
        val input_text: EditText = findViewById(R.id.edit_text_name_input)
        Toast.makeText(this, input_text.getText(), Toast.LENGTH_LONG).show()
    }

}
