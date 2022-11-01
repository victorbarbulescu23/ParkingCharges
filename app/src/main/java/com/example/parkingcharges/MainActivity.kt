package com.example.parkingcharges

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    lateinit var lotGroup: Spinner
    lateinit var hoursInput: EditText
    lateinit var chargesButton: Button
    lateinit var resultOutput: TextView

    val costPerHour: Double = 1.50
    var numHours: Int = 0
    var lotChoice: String = ""
    var totalCharges: Double = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lotGroup = findViewById(R.id.lot_dropdown)
        hoursInput = findViewById(R.id.hours_input)
        chargesButton = findViewById(R.id.calculate_button)
        resultOutput = findViewById(R.id.result_output)

        chargesButton.setOnClickListener {
            if (checkData()) {
                //Getting text from user
                numHours = hoursInput.text.toString().toInt()
                totalCharges = costPerHour * numHours
                lotChoice = lotGroup.selectedItem.toString()

                //Format totalCharges to fit a money format
                val currency = DecimalFormat("$##,###.00")
                val totalChargesFormatted = currency.format(totalCharges)

                val result =
                    "Cost for parking in $lotChoice for $numHours is $totalChargesFormatted"
                resultOutput.text = result
            }

        }


    }

    //CheckData method validates the inputted data to ensure the app won't crash during unexpected events such as an invalid input
    private fun checkData(): Boolean {
        if (hoursInput.text.toString().isEmpty() || hoursInput.text.toString().toInt() <= 0){
            hoursInput.error = "Invalid Hour Input"
            hoursInput.requestFocus()
            return false
        }

        return true
    }


}