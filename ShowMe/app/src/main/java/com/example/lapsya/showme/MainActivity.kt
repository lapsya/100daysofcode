package com.example.lapsya.showme

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    var hello_btn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        hello_btn  = findViewById(R.id.hello_button) as Button
    }

    hello_btn.setOnClickListener = {
        Toast.makeText(this, "You've pressed me!", Toast.LENGTH_LONG).show()
    }

}
