package com.example.treinomascara

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.example.treinomascara.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.inputCelular.addTextChangedListener(object: TextWatcher{

            val inputCelular = binding.inputCelular

            var isUpdating = false

            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(isUpdating){
                    isUpdating = false
                    return
                }

                val hasMask = s.toString().indexOf('(') > -1 || s.toString().indexOf(')') > -1 ||
                        s.toString().indexOf(' ') > -1 ||
                        s.toString().indexOf('-') > -1

                var str = s.toString().filterNot { it == '(' || it == ')' || it == '-' || it == ' ' }

                if(count > before){
                    if(str.length > 7){
                        str = "(${str.substring(0, 2)}) ${str.substring(2, 7)}-${str.substring(7)}"
                    } else if(str.length > 3){
                        str = "(${str.substring(0, 2)}) ${str.substring(2)}"
                    } else if(str.length > 2){
                        str = "(${str.substring(0, 2)}) ${str.substring(2)}"
                    } else if(str.length > 1){
                        str = "(${str.substring(0)})"
                    }

                    isUpdating = true
                    inputCelular.setText(str)
                    inputCelular.setSelection(inputCelular.text?.length ?: 0)

                } else if (before > count){
                    if(str.length > 7){
                        str = "(${str.substring(0, 2)}) ${str.substring(2, 7)}-${str.substring(7)}"
                    } else if(str.length in 3..7){
                        str = "(${str.substring(0, 2)}) ${str.substring(2, str.length)}"
                    } else if(str.length in 1..2){
                        str = "(${str.substring(0, str.length)}"
                    } else if(str.length <= 1){
                        str = ""
                    }

                    isUpdating = true
                    inputCelular.setText(str)
                    inputCelular.setSelection(inputCelular.text?.length ?: 0)

                } else {
                    isUpdating = true
                    inputCelular.setText(str)
                    inputCelular.setSelection(Math.max(0,
                        Math.min(if (hasMask) start - before else start, str.length)))
                }
            }
        })
    }
}