package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var textResult: TextView

    var state: Int = 1
    var op: Int = 0
    var op1: Int = 0
    var op2: Int = 0
    var operatorSymbol: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textResult = findViewById(R.id.display)

        findViewById<Button>(R.id.button_0).setOnClickListener(this)
        findViewById<Button>(R.id.button_1).setOnClickListener(this)
        findViewById<Button>(R.id.button_2).setOnClickListener(this)
        findViewById<Button>(R.id.button_3).setOnClickListener(this)
        findViewById<Button>(R.id.button_4).setOnClickListener(this)
        findViewById<Button>(R.id.button_5).setOnClickListener(this)
        findViewById<Button>(R.id.button_6).setOnClickListener(this)
        findViewById<Button>(R.id.button_7).setOnClickListener(this)
        findViewById<Button>(R.id.button_8).setOnClickListener(this)
        findViewById<Button>(R.id.button_9).setOnClickListener(this)

        findViewById<Button>(R.id.button_plus).setOnClickListener(this)
        findViewById<Button>(R.id.button_minus).setOnClickListener(this)
        findViewById<Button>(R.id.button_multiply).setOnClickListener(this)
        findViewById<Button>(R.id.button_divide).setOnClickListener(this)

        findViewById<Button>(R.id.button_equals).setOnClickListener(this)
        findViewById<Button>(R.id.button_c).setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val id = view?.id
        when (id) {
            R.id.button_0 -> calcDigit(0)
            R.id.button_1 -> calcDigit(1)
            R.id.button_2 -> calcDigit(2)
            R.id.button_3 -> calcDigit(3)
            R.id.button_4 -> calcDigit(4)
            R.id.button_5 -> calcDigit(5)
            R.id.button_6 -> calcDigit(6)
            R.id.button_7 -> calcDigit(7)
            R.id.button_8 -> calcDigit(8)
            R.id.button_9 -> calcDigit(9)

            R.id.button_plus -> {
                operatorSymbol = "+"
                setOperator(1)
            }
            R.id.button_minus -> {
                operatorSymbol = "-"
                setOperator(2)
            }
            R.id.button_multiply -> {
                operatorSymbol = "x"
                setOperator(3)
            }
            R.id.button_divide -> {
                operatorSymbol = "/"
                setOperator(4)
            }

            R.id.button_equals -> {
                var result = 0
                when (op) {
                    1 -> result = op1 + op2
                    2 -> result = op1 - op2
                    3 -> result = op1 * op2
                    4 -> if (op2 != 0) result = op1 / op2 else textResult.text = "Error"
                }
                textResult.text = "$op1 $operatorSymbol $op2 = $result"
                op1 = result;
                op2 = 0;
            }

            R.id.button_c -> reset()  // Nút "C" để reset

        }
    }

    fun calcDigit(c: Int) {

        if (state == 1) {
            op1 = op1 * 10 + c
            textResult.text = "$op1"
        } else {
            op2 = op2 * 10 + c
            textResult.text = "$op1 $operatorSymbol $op2"
        }

    }

    fun setOperator(operation: Int) {
        op = operation
        op2 = 0
        state = 2
        textResult.text = "$op1 $operatorSymbol"
    }

    fun reset() {
        state = 1
        op1 = 0
        op2 = 0
        op = 0
        operatorSymbol = ""
        textResult.text = "0"
    }
}
