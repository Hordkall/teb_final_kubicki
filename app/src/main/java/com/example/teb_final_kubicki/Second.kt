package com.example.teb_final_kubicki

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Second : AppCompatActivity() {

    var fstNum = 0
    var sndNum = 0

    lateinit var btnA: Button
    lateinit var btnB: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        initializeButtons()

        getNumbersFromBundle()

        setButtonsListeners()
    }

    private fun getNumbersFromBundle() {
        fstNum = intent.extras?.getInt(FIRST_NUMBER_KEY) ?: 0
        sndNum = intent.extras?.getInt(SECOND_NUMBER_KEY) ?: 0
    }

    private fun initializeButtons() {
        btnA = findViewById(R.id.buttonA)
        btnB = findViewById(R.id.buttonB)
    }

    private fun setButtonsListeners() {
        btnA.setOnClickListener {
            val intent = Intent()
            val dataBundle = Bundle()
            dataBundle.putInt(RESULT_KEY, getSumOfNumbersDividableByThreeInRange(fstNum, sndNum))
            intent.putExtras(dataBundle)
            setResult(NUMBER_ACTIVITY_REQUEST_CODE, intent)
            finish()
        }

        btnB.setOnClickListener {
            val intent = Intent()
            val dataBundle = Bundle()
            dataBundle.putInt(RESULT_KEY, getSmallestPrimeGreaterThanSum(fstNum, sndNum))
            intent.putExtras(dataBundle)
            setResult(NUMBER_ACTIVITY_REQUEST_CODE, intent)
            finish()
        }
    }
}

private fun getSumOfNumbersDividableByThreeInRange(fstNum: Int, sndNum: Int): Int {
    var acc = 0
    for (i in fstNum..sndNum) {
        acc += if (i%3==0) i else 0
    }
    return acc
}

private fun getSmallestPrimeGreaterThanSum(fstNum: Int, sndNum: Int): Int {
    val findPrimeAbove = fstNum + sndNum
    var acc = findPrimeAbove + 1
    while (!isPrime(acc)) {
        acc++
    }
    return acc
}

private fun isPrime(num: Int): Boolean {
    for(i in 2..num/2) {
        if (num%2==0){
            return false
        }
    }
    return true
}
