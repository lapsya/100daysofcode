package com.example.lapsya.showme

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var hello_btn: Button = findViewById(R.id.hello_button)
    }

    fun hello_btn_click(v: View) {
        Toast.makeText(this, "You've pressed me!", Toast.LENGTH_LONG).show()
    }

}
