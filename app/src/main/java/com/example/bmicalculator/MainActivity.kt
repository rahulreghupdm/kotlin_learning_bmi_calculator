package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val heightText = findViewById<EditText>(R.id.etHeight)
        val weightText = findViewById<EditText>(R.id.etWeight)
        val button = findViewById<Button>(R.id.btnCalculate)
        button.setOnClickListener {
            val height= heightText.text.toString()
            val weight = weightText.text.toString()
            if(validatedInput(weight,height)) {

                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))

                // get result with two decimals
                val bmi2Digits = String.format("%.2f", bmi).toFloat()
                displayBmi(bmi2Digits)
            }
        }
    }
    private fun validatedInput(weight:String?,height:String?):Boolean{
        when {
            weight.isNullOrEmpty() ->{
                Toast.makeText(this,"Weight is Empty",Toast.LENGTH_LONG).show()
                return false
            }
            height.isNullOrEmpty() ->{
                Toast.makeText(this,"Height is Empty",Toast.LENGTH_LONG).show()
                return false
            }
            else ->{
                return  true
            }

        }
    }
    private  fun displayBmi(bmi:Float) {
        val resultIndex = findViewById<TextView>(R.id.resultFirst)
        val  resultDescription = findViewById<TextView>(R.id.resultSecond)
        val info = findViewById<TextView>(R.id.resulltThird)
        resultIndex.text = bmi.toString()
        info.text = "Your bmi is okay"


        var resultText = ""
        var color = 0

        when {
            bmi<18.50 ->{
                resultText = "underweight"
                color =R.color.under_weight
            }
            bmi in 18.50..24.99 ->{
                resultText = "Healthy"
                color = R.color.normal
            }
            bmi in 25.00..29.99 -> {
                resultText = "over weight"
                color = R.color.over_weight
            }
            bmi > 29.99 ->{
                resultText = "Obese"
                color = R.color.obese
            }
        }
        resultDescription.setTextColor(ContextCompat.getColor(this,color))
        resultDescription.text = resultText
    }
}