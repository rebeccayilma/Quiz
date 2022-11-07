package com.rebecca.quiz

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val radioGroup : RadioGroup = findViewById(R.id.q1)
        val ch1:CheckBox = findViewById(R.id.ch1)
        val ch2:CheckBox = findViewById(R.id.ch2)
        val ch3:CheckBox = findViewById(R.id.ch3)

        val submitBtn : Button = findViewById(R.id.button)
        val resetBtn : Button = findViewById(R.id.button2)

        submitBtn.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(this)
            val currentTime = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
            val formattedTime = currentTime.format(formatter)

            val selectedOption : Int = radioGroup!!.checkedRadioButtonId

            val radioButton : RadioButton = findViewById( selectedOption)
            var num :Int = 0
            if (radioButton.text == "True"){
                num+=50
            }
            if(ch1.isChecked || ch3.isChecked){
                num+=50
            }

            val msg : String ="Congratulations! You submitted on $formattedTime , You Achieved $num%";
            dialogBuilder.setMessage(msg).setCancelable(true)

            val alertDialog = dialogBuilder.create()
            alertDialog.setTitle("Quiz Result")
            alertDialog.show()
        }

        resetBtn.setOnClickListener {
            ch1.isChecked = false
            ch2.isChecked = false
            ch3.isChecked = false

            radioGroup.clearCheck()
        }
    }
}