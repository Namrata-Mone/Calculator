package com.namrata.calculator.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.mozilla.javascript.Context

class CalculatorViewModel:ViewModel() {
    private val _equationText=MutableLiveData("")
    val equationText:LiveData<String> = _equationText

    private val _resultText=MutableLiveData("0")
    val resultText:LiveData<String> = _resultText


    fun onButtonClick(btn:String){
        Log.i("Button",btn)

        _equationText.value?.let{
            when(btn){
                "AC" -> {
                    _equationText.value = ""
                    _resultText.value = "0"
                }
                "C" ->{
                    if(it.isNotEmpty()){
                        _equationText.value = it.substring(0,it.length-1)
                    }
                }
                "=" ->{
                    _equationText.value = _resultText.value
                }
                else ->{
                    if(it=="0"){
                        _equationText.value = btn
                    }else{
                        _equationText.value = it + btn
                    }
                    try{
                        _resultText.value=calculateResult(_equationText.value.toString())
                    }catch (_:Exception){ }
                }
            }

        }
    }
    private fun calculateResult(equation:String):String{
        val context: Context = Context.enter()
        context.optimizationLevel=-1
        val scriptable=context.initStandardObjects()
        var finalResult=context.evaluateString(scriptable,equation,"JavaScript",1,null).toString()
        if(finalResult.endsWith(".0")){
            finalResult=finalResult.replace(".0","")
        }
        return finalResult
    }
}
