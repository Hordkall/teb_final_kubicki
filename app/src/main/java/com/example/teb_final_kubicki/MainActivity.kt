package com.example.teb_final_kubicki

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

val NUMBER_ACTIVITY_REQUEST_CODE = 111
val FIRST_NUMBER_KEY = "FIRST_NUMBER"
val SECOND_NUMBER_KEY = "SECOND_NUMBER"
val RESULT_KEY = "RESULT"

class MainActivity : AppCompatActivity() {

    lateinit var firstEditText: EditText
    lateinit var secondEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        firstEditText = findViewById(R.id.fst_num_txt)
        secondEditText = findViewById(R.id.snd_num_txt)
        val buttonZatwierdz = findViewById<Button>(R.id.buttonZatwierdz)
        buttonZatwierdz.setOnClickListener {

            val firstNumber = firstEditText.text.toString().toInt()
            val secondNumber = secondEditText.text.toString().toInt()


            val intent = Intent(this, Second::class.java)
            val numberBundle = Bundle()
            numberBundle.putInt(FIRST_NUMBER_KEY, firstNumber)
            numberBundle.putInt(SECOND_NUMBER_KEY, secondNumber)
            intent.putExtras(numberBundle)
            startActivityForResult(intent, NUMBER_ACTIVITY_REQUEST_CODE);
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode != NUMBER_ACTIVITY_REQUEST_CODE) {
            return
        }
        val resultTxt: TextView = findViewById(R.id.resultTxt)
        val result = data?.extras?.getInt(RESULT_KEY) ?: "ERROR"
        resultTxt.text = result.toString()
    }
}
