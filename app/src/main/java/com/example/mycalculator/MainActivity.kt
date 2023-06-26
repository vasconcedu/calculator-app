package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var calculator: MyCalculator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Instantiate calculator
        calculator = MyCalculator()

        // Update display value
        updateDisplay()

        // Register button listeners
        registerClearListener()
        registerDigitListeners()
        registerOperationListeners()
        registerEqualsListener()
    }

    private fun updateDisplay() {
        val display = findViewById<TextView>(R.id.display)
        display.text = calculator.displayValue
    }

    private fun registerClearListener() {
        findViewById<Button>(R.id.button_clear)
            .setOnClickListener {
                calculator.clear()
                updateDisplay()
            }
    }

    private fun registerDigitListeners() {
        val digitButtonMap = mutableMapOf<Int, String>()
        digitButtonMap[R.id.button_9] = resources.getString(R.string._9)
        digitButtonMap[R.id.button_8] = resources.getString(R.string._8)
        digitButtonMap[R.id.button_7] = resources.getString(R.string._7)
        digitButtonMap[R.id.button_6] = resources.getString(R.string._6)
        digitButtonMap[R.id.button_5] = resources.getString(R.string._5)
        digitButtonMap[R.id.button_4] = resources.getString(R.string._4)
        digitButtonMap[R.id.button_3] = resources.getString(R.string._3)
        digitButtonMap[R.id.button_2] = resources.getString(R.string._2)
        digitButtonMap[R.id.button_1] = resources.getString(R.string._1)
        digitButtonMap[R.id.button_0] = resources.getString(R.string._0)
        digitButtonMap[R.id.button_decimal] = resources.getString(R.string.decimal)

        for (digitButton in digitButtonMap) {
            findViewById<Button>(digitButton.key)
                .setOnClickListener {
                    calculator.type(digitButton.value)
                    updateDisplay()
                }
        }
    }

    private fun registerOperationListeners() {
        val operatorButtonMap = mutableMapOf<Int, String>()
        operatorButtonMap[R.id.button_over] = resources.getString(R.string.over)
        operatorButtonMap[R.id.button_times] = resources.getString(R.string.times)
        operatorButtonMap[R.id.button_minus] = resources.getString(R.string.minus)
        operatorButtonMap[R.id.button_plus] = resources.getString(R.string.plus)

        for (operatorButton in operatorButtonMap) {
            findViewById<Button>(operatorButton.key)
                .setOnClickListener {
                    calculator.type(operatorButton.value)
                    updateDisplay()
                }
        }
    }

    private fun registerEqualsListener() {
        findViewById<Button>(R.id.button_equals)
        .setOnClickListener {
            calculator.calculate()
            updateDisplay()
        }
    }
}