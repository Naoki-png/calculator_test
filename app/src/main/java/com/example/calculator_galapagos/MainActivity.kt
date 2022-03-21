package com.example.calculator_galapagos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import com.example.calculator_galapagos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var firstNumber: Float? = null
    private var selectedCalcType = CalcType.EQUAL
    private var lastPressedButtonType = ButtonType.NONE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        initButtons()
    }

    private fun initButtons() {
        // 数値ボタン
        initNumberButton(binding.zero)
        initNumberButton(binding.one)
        initNumberButton(binding.two)
        initNumberButton(binding.three)
        initNumberButton(binding.four)
        initNumberButton(binding.five)
        initNumberButton(binding.six)
        initNumberButton(binding.seven)
        initNumberButton(binding.eight)
        initNumberButton(binding.nine)

        // 計算ボタン
        initCalcButton(binding.plus)
        initCalcButton(binding.minus)
        initCalcButton(binding.multiply)
        initCalcButton(binding.divide)
        initCalcButton(binding.equal)

        // クリアボタン
        initAllClearButton()
    }

    private fun initNumberButton(numberButton: TextView) {
        numberButton.setOnClickListener {
            when (lastPressedButtonType) {
                ButtonType.NONE -> {
                    binding.output.text = (it as TextView).text
                }
                ButtonType.CALC -> {
                    firstNumber = binding.output.text.toString().toFloat()
                    binding.output.text = (it as TextView).text
                }
                ButtonType.NUMBER -> {
                    binding.output.apply {
                        text = StringBuilder().append(text).append((it as TextView).text)
                    }
                }
            }
            lastPressedButtonType = ButtonType.NUMBER
        }
    }

    private fun initCalcButton(calcButton: TextView) {
        calcButton.setOnClickListener {
            when (calcButton.text) {
                "+" -> selectedCalcType = CalcType.PLUS
                "-" -> selectedCalcType = CalcType.MINUS
                "*" -> selectedCalcType = CalcType.MULTIPLY
                "/" -> selectedCalcType = CalcType.DIVIDE
                "=" -> {
                    if (firstNumber == null ||
                        selectedCalcType == CalcType.EQUAL) {
                        return@setOnClickListener
                    }
                    calculate(selectedCalcType)
                }
            }

            lastPressedButtonType = ButtonType.CALC
        }
    }

    private fun calculate(calcType: CalcType) {
        var result = 0f
        val secondNumber = binding.output.text.toString().toFloat()
        when (calcType) {
            CalcType.PLUS -> result = firstNumber!! + secondNumber
            CalcType.MINUS -> result = firstNumber!! - secondNumber
            CalcType.MULTIPLY -> result = firstNumber!! * secondNumber
            CalcType.DIVIDE -> result = firstNumber!! / secondNumber
            else -> {
                // 処理なし
            }
        }
        binding.output.text = if (result.rem(1) == 0f) result.toInt().toString() else result.toString()
        clear()
        Toast.makeText(this, R.string.toast_finish, Toast.LENGTH_SHORT).show()
    }

    private fun initAllClearButton() {
        binding.allClear.setOnClickListener {
            binding.output.text = "0"
            clear()
            Toast.makeText(this, R.string.toast_clear, Toast.LENGTH_SHORT).show()
        }
    }

    private fun clear() {
        firstNumber = null
        selectedCalcType = CalcType.EQUAL
        lastPressedButtonType = ButtonType.NONE
    }

    private enum class ButtonType {
        CALC, NUMBER, NONE
    }

    private enum class CalcType {
        PLUS, MINUS, MULTIPLY, DIVIDE, EQUAL
    }
}
