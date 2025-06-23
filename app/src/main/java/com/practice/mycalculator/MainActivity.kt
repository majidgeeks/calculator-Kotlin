package com.practice.mycalculator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.practice.mycalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var binding : ActivityMainBinding ? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
         binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
       calculate()
    }
   private fun calculate(){
        binding?.apply {
                val firstInput = textInput1
                val secondInput = textInput2
                val resultView = txtResult
                    fun performCal(operator : String){
                        val firstNumber = firstInput.text.toString().toIntOrNull()
                        val secondNumber = secondInput.text.toString().toIntOrNull()
                        if(firstNumber == null){
                            firstInput.error = getText(R.string.str_first_input_error)
                            return
                        }else if(secondNumber == null){
                            secondInput.error = getText(R.string.str_second_input_error)
                            return
                        }
                        val result = when (operator){
                            "+" -> firstNumber + secondNumber
                            "-" -> firstNumber - secondNumber
                            "X" -> firstNumber * secondNumber
                            "/" -> {
                                if(secondNumber == 0){
                                    resultView.text = getString(R.string.cannot_divide_by_0)
                                    return
                                }else{
                                    firstNumber / secondNumber
                                }
                            }
                            else -> 0
                        }
                        resultView.text = result.toString()
                        firstInput.text = null
                        secondInput.text = null
                    }
                    btnAdd.setOnClickListener{performCal("+")}
                    btnMinus.setOnClickListener{performCal("-")}
                    btnDiv.setOnClickListener { performCal("/") }
                    btnMultiply.setOnClickListener { performCal("X") }
        }
    }
}