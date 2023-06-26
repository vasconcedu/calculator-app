package com.example.mycalculator

import java.util.LinkedList

class MyCalculator {

    private lateinit var list: LinkedList<String>

    var displayValue: String = ""
        private set

    fun clear() {
        displayValue = ""
    }

    fun type(s: String) {
        var s = s
        if (s == "/" || s == "*" || s == "+" || s == "-") {
            // This both makes the expression easier
            // for reading and facilitates parsing
            s = " $s "
        }
        displayValue += s
    }

    private fun parseExpression() {
        list = LinkedList()
        list.addAll(displayValue.split(" "))

        // TODO perform expression validity checks
    }

    private fun operate(first: Float, second: Float, operator: String): Float {
        return when (operator) {
            "/" -> first / second
            "*" -> first * second
            "-" -> first - second
            else -> first + second // +
        }
    }

    fun calculate() {
        parseExpression()

        try {

            var index: Int
            val operators = arrayOf("/", "*", "-", "+")

            // Perform operations
            for (operator in operators) {

                index = list.indexOf(operator)

                while (index != -1) {
                    // Operands
                    var first = list[index - 1]
                    var second = list[index + 1]

                    // Result
                    var result = operate(first.toFloat(), second.toFloat(), operator)

                    // Update at index - 1
                    list[index - 1] = result.toString()

                    // Remove at index + 1
                    // and ONLY THEN at index + 1
                    list.removeAt(index + 1)
                    list.removeAt(index)

                    // Get index of next / operator
                    index = list.indexOf(operator)
                }
            }

            displayValue = list.first

        } catch (e: Exception) {
            displayValue = "Invalid expression!"
        }
    }
}