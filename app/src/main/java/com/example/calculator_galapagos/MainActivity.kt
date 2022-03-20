package com.example.calculator_galapagos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import com.example.calculator_galapagos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
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
    }

    private fun initCalcButton(calcButton: TextView) {
    }

    private fun initAllClearButton() {
    }

    private enum class ButtonType {
        CALC, NUMBER, NONE
    }

    private enum class CalcType {
        PLUS, MINUS, MULTIPLY, DIVIDE, EQUAL
    }
}
