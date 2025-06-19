package com.practice.mycalculator

import android.os.Bundle
import android.text.TextUtils
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
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
    fun calculate(){
        binding?.apply {
                var firstInput = textInput1
                var secondInput = textInput2
                var resultView = txtResult
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
                                if(secondNumber === 0){
                                    resultView.text = "Cannot divide by 0"
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